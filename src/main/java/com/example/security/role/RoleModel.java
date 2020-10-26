package com.example.security.role;

import com.example.security.base.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "roles")
public class RoleModel extends BaseEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole name;

    public RoleModel(UserRole name){
        this.name = name;
    }

    public RoleModel() {
    }
}
