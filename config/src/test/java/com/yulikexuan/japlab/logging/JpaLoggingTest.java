//: com.yulikexuan.japlab.logging.JpaLoggingTest.java


package com.yulikexuan.japlab.logging;


import com.yulikexuan.japlab.AbstractTestCase;
import com.yulikexuan.japlab.domain.Professor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityTransaction;

@DisplayName("Test JPA Logging - ")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JpaLoggingTest extends AbstractTestCase  {

    @Test
    public void test_Given_EntityManager_Then_Run_Transaction() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();

        // When & Then
        transaction.begin();

        // Persist a new Professor
        Professor prof = new Professor();
        prof.setId(1L);
        prof.setFirstName("Jane");
        prof.setLastName("Doe");
        this.entityManager.persist(prof);

        transaction.commit();

        logger.info(">>>>>>> Transaction for {} was committed.",
                this.getClass().getSimpleName());

        logger.info(">>>>>>> Logger's name is {}", this.logger.getName());
    }

}///:~