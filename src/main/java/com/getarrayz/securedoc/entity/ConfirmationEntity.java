package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="confirmations")
@JsonInclude(NON_DEFAULT)
public class ConfirmationEntity extends Auditable {
    private String key;
    @OneToOne(targetEntity=UserEntity.class, fetch = EAGER)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    private UserEntity userEntity;

    public ConfirmationEntity(UserEntity userEntity){
        this.userEntity=userEntity;
        this.key= UUID.randomUUID().toString(); // ifadesi, bir nesne oluşturulduğunda o nesnenin key adındaki alanına rastgele bir UUID değeri atanmasını sağlar. Bu genellikle benzersiz kimlik tanımlayıcıları oluşturmak için kullanılır, özellikle veritabanı kayıtlarını takip etmek veya nesneler arasındaki ilişkileri yönetmek için kullanışlıdır.
    }
}
