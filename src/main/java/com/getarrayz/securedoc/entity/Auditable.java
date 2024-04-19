package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.getarrayz.securedoc.domain.RequestContext;
import com.getarrayz.securedoc.exception.ApiException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@MappedSuperclass//Bu anotasyonu kullanarak, bir sınıfın tüm alt sınıflarına ait ortak alanları ve davranışları tanımlayabiliriz
@EntityListeners(AuditingEntityListener.class)//Burada AuditingEntityListener.class, varlık dinleyicisinin türünü belirtir. Bu özel durumda, AuditingEntityListener sınıfı, temel CRUD işlemleri için denetim izlerinin tutulmasını sağlar. Örneğin, bir varlık oluşturulduğunda veya güncellendiğinde, bu olayları dinler ve ilgili alanlara otomatik olarak oluşturma ve güncelleme tarihlerini ekler.
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) //JSON serileştirme ve deserializasyon işlemlerinde "createdAt" ve "updatedAt" alanlarını dikkate almaz.allowGetters = true parametresi, belirtilen alanların get yöntemlerinin kullanılmasına izin verir. Bu, ilgili alanlar için get yöntemleri tanımlanmışsa, yalnızca bu get yöntemleri çağrıldığında belirtilen alanların yoksayılacağı anlamına gelir.belirli alanların gizlenmesi, dikkate alınmaması gerektiği durumlarda, hassas bilgilerin veya istemciye gönderilmesini istemediğiniz diğer verilerin gizlenmesi için kullanılabilirler.
public abstract class Auditable {

    @Id
    @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "primary_key_seq")
    @Column(name = "id",updatable = false)
    private Long id;

    private String referanceId=new AlternativeJdkIdGenerator().generateId().toString();

    @NotNull
    private Long createdBy;

    @NotNull
    private Long updatedBy;

    @NotNull
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updated_at",nullable = false,updatable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    public void beforePersist() {
        var userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("cannot persist entity without user ID in Request Context for this thread"); }
            setCreatedAt(now());
            setCreatedBy(userId);
            setUpdatedBy(userId);
            setUpdatedAt(now());

    }
        @PreUpdate
        public void beforeUpdate(){
            var userId=RequestContext.getUserId();
            if(userId == null){
                throw new ApiException("cannot update entity without user ID in Request Context for this thread");}
                setUpdatedAt(now());
                setUpdatedBy(userId);
            }
}
