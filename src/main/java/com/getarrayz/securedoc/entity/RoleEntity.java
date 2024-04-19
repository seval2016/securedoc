package com.getarrayz.securedoc.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.getarrayz.securedoc.enumaration.Authority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
@JsonInclude(NON_DEFAULT)
public class RoleEntity {

    private String name;
    private Authority authorities;
}
