package com.whispr.domain.utils;

import com.whispr.domain.entity.BaseEntity;
import org.hibernate.proxy.HibernateProxy;

public class PersistenceUtils {

    private PersistenceUtils() {
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
