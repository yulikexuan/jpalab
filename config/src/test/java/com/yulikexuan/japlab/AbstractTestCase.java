//: com.yulikexuan.japlab.AbstractTestCase.java


package com.yulikexuan.japlab;


import com.yulikexuan.japlab.bootstrap.JpaBootstrap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;


public abstract class AbstractTestCase extends JpaBootstrap {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected EntityManager entityManager;

    @BeforeEach
    void setUp() {
        this.entityManager = this.entityManager();
    }

    @AfterEach
    void tearDown() {
        this.entityManager.close();
    }

}///:~