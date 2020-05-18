//: com.yulikexuan.japlab.domain.JpaConfigTest.java


package com.yulikexuan.japlab.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityTransaction;


@DisplayName("Test JPA Configuration - ")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JpaConfigTest extends AbstractTestCase {

	@Test
	public void test_Given_EntityManager_Then_Run_Transaction() {

		// Given
		EntityTransaction transaction = this.entityManager.getTransaction();

		// When & Then
		transaction.begin();
		transaction.commit();
	}

}///:~