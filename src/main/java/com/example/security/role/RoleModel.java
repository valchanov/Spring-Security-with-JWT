package com.example.security.role;

import com.example.security.base.BaseEntityAudit;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "roles")
public class RoleModel extends BaseEntityAudit {
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole name;

    public RoleModel() {
    }
}
