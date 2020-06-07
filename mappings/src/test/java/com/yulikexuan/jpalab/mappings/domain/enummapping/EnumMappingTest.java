//: com.yulikexuan.jpalab.mappings.domain.enummapping.EnumMappingTest.java


package com.yulikexuan.jpalab.mappings.domain.enummapping;


import com.yulikexuan.japlab.AbstractTestCase;
import com.yulikexuan.jpalab.mappings.domain.Student;
import com.yulikexuan.jpalab.mappings.domain.StudentStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;


@Slf4j
@DisplayName("Test Enum Mapping - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EnumMappingTest extends AbstractTestCase {

    @Test
    void test_Given_Student_When_Persisting_Then_Mapping_Status_Enum_With_String() {

        // Given
        Student student = Student.builder()
                .firstName("Peter")
                .lastName("Doe")
                .state(StudentStatus.ENROLLED)
                .build();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        this.entityManager.persist(student);

        // Then
        transaction.commit();
    }

}///:~