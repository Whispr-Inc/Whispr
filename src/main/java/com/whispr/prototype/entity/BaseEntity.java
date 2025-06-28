package com.whispr.prototype.entity;

import com.whispr.prototype.utils.HibernateUtil;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

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

    public abstract UUID getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof BaseEntity that)) return false;

        Class<?> oEffectiveClass = HibernateUtil.getPersistanceClass(that);
        Class<?> thisEffectiveClass = HibernateUtil.getPersistanceClass(this);
        if (thisEffectiveClass != oEffectiveClass) return false;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return HibernateUtil.getPersistanceClass(this).hashCode();
    }

    @Override
    public String toString() {
        return HibernateUtil.getPersistanceClass(this).getSimpleName() + " [" + "id=" + getId() + ']';
    }
}
