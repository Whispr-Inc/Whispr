package com.whispr.prototype.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

/**
 * Base entity class that provides common properties and behavior for all entities.
 * It is annotated with {@link MappedSuperclass} to indicate that it is a base class
 * for other entities and should not be instantiated directly.
 * It also uses {@link AuditingEntityListener} to support auditing features.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
