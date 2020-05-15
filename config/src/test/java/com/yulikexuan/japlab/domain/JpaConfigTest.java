//: com.yulikexuan.japlab.domain.JpaConfigTest.java


package com.yulikexuan.japlab.domain;


import com.yulikexuan.japlab.bootstrap.JpaBootstrap;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


@DisplayName("Test JPA Configuration - ")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JpaConfigTest extends JpaBootstrap {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManager entityManager;

	@BeforeEach
	void setUp() {
		this.entityManager = this.getEntityManager();
	}

	@AfterEach
	void tearDown() {
		this.entityManager.close();
	}

	@Test
	public void test_Given_EntityManager_Then_Run_Transaction() {

		// Given
		EntityTransaction transaction = this.entityManager.getTransaction();

		// When & Then
		transaction.begin();
		transaction.commit();
	}

}///:~