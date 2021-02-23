package com.example.security.user;

import com.example.security.base.BaseEntityAudit;
import com.example.security.role.RoleModel;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class UserModel extends BaseEntityAudit {
    @NotNull
    private String name;

   // @NotNull
    private String email;

    //@NotNull
    private String password;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleModel role;
}