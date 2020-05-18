//: com.yulikexuan.japlab.bootstrap.JpaBootstrap.java


package com.yulikexuan.japlab.bootstrap;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaBootstrap {

    public static final String PERSISTENCE_UNIT_NAME = "jpa-persistence-unit";

    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory(
                PERSISTENCE_UNIT_NAME);
    }

    @AfterAll
    public static void shutdown() {
        entityManagerFactory.close();
    }

    public EntityManager entityManager() {
        return entityManagerFactory.createEntityManager();
    }

}///:~