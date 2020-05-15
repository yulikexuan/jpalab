//: com.yulikexuan.japlab.domain.JpaConfigTest.java


package com.yulikexuan.japlab.domain;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("CompletableFuture Test - ")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JpaConfigTest {

	static final String PERSISTENCE_UNIT_NAME = "jpa-persistence-unit";

	Logger log = Logger.getLogger(this.getClass().getName());

	private static EntityManagerFactory entityManagerFactory;

	@BeforeAll
	public static void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory(
				PERSISTENCE_UNIT_NAME);
	}

	@AfterAll
	public static void close() {
		entityManagerFactory.close();
	}

	@Test
	public void createProfessor() {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		em.close();
	}

}///:~