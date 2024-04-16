package com.getarrayz.securedoc.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass //Bu anotasyonu kullanarak, bir sınıfın tüm alt sınıflarına ait ortak alanları ve davranışları tanımlayabiliriz
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
}
