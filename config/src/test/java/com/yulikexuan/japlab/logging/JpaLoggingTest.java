//: com.yulikexuan.japlab.logging.JpaLoggingTest.java


package com.yulikexuan.japlab.logging;


import com.yulikexuan.japlab.AbstractTestCase;
import com.yulikexuan.japlab.domain.Professor;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import static org.assertj.core.api.Assertions.assertThat;


//@ExtendWith(MockitoExtension.class)
@DisplayName("Test JPA Logging - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JpaLoggingTest extends AbstractTestCase  {

    @Test
    @Order(1)
    public void test_Given_Transaction_Then_Save_A_New_Entity() {

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

    @Test
    @Order(2)
    void test_Given_Transaction_Then_Query_Existing_Entity() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        String queryString = "SELECT p FROM Professor p WHERE p.firstName = :fname";
        TypedQuery<Professor> query = this.entityManager.createQuery(queryString,
                Professor.class);
        query.setParameter("fname", "Jane");

        // When
        Professor professor = query.getSingleResult();
        transaction.commit();

        // Then
        assertThat(professor.getFirstName()).isEqualTo("Jane");
        assertThat(professor.getLastName()).isEqualTo("Doe");
    }

    @Test
    @Order(3)
    void test_Given_Transaction_Then_Update_Existing_Entity() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        String queryString = "SELECT p FROM Professor p WHERE p.firstName = :fname";
        TypedQuery<Professor> query = this.entityManager.createQuery(queryString,
                Professor.class);
        query.setParameter("fname", "Jane");

        Professor professor = query.getSingleResult();

        // When
        professor.setFirstName("Steve");

        transaction.commit();

        // Then
        assertThat(professor.getFirstName()).isEqualTo("Steve");
        assertThat(professor.getLastName()).isEqualTo("Doe");
    }

    @Test
    @Order(4)
    void test_Given_Transaction_Then_Delete_Existing_Entity() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        String queryString = "SELECT p FROM Professor p WHERE p.firstName = :fname";
        TypedQuery<Professor> query = this.entityManager.createQuery(queryString,
                Professor.class);
        query.setParameter("fname", "Steve");

        Professor professor = query.getSingleResult();

        // When
        this.entityManager.remove(professor);

        transaction.commit();

        // Then
        assertThat(professor.getFirstName()).isEqualTo("Steve");
        assertThat(professor.getLastName()).isEqualTo("Doe");
    }

}///:~