package com.example.security.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;


@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityAudit extends BaseEntity {
    @CreatedDate
    protected Instant createdDate;

    @LastModifiedDate
    protected Instant lastModifiedDate;

    @CreatedBy
    protected Long createdBy;

    @LastModifiedBy
    protected Long lastModifiedBy;
}
