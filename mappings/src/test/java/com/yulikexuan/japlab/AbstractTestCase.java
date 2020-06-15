//: com.yulikexuan.japlab.AbstractTestCase.java


package com.yulikexuan.japlab;


import com.yulikexuan.japlab.bootstrap.JpaBootstrap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Random;


public abstract class AbstractTestCase extends JpaBootstrap {

    protected Random random;
    protected EntityManager entityManager;

    @BeforeEach
    protected void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.entityManager = this.entityManager();
    }

    @AfterEach
    protected void tearDown() {
        this.entityManager.close();
    }

    protected long getVersion() {
        return this.random
                .longs(1_000_000, 9_999_999)
                .findAny()
                .getAsLong();
    }

}///:~