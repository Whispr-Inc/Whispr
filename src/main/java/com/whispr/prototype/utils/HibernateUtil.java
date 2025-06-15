package com.whispr.prototype.utils;

import com.whispr.prototype.entity.BaseEntity;
import org.hibernate.proxy.HibernateProxy;

public final class HibernateUtil {

    private HibernateUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns the effective persistence class of the given entity.
     * If the entity is a Hibernate proxy, it retrieves the persistent class from the proxy.
     * Otherwise, it returns the class of the entity itself.
     *
     * @param entity the entity whose persistence class is to be determined
     * @return the effective persistence class of the entity
     */
    public static Class<?> getPersistanceClass(BaseEntity entity) {
        return entity instanceof HibernateProxy
            ? ((HibernateProxy) entity).getHibernateLazyInitializer().getPersistentClass()
            : entity.getClass();
    }
}
