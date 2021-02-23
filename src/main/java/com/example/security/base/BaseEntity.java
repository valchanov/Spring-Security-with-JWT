package com.example.security.base;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;
}
