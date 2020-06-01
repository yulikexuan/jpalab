//: com.yulikexuan.jpalab.mappings.domain.generatedid.IdGeneratorTest.java


package com.yulikexuan.jpalab.mappings.domain.generatedid;


import com.yulikexuan.japlab.AbstractTestCase;
import com.yulikexuan.jpalab.mappings.domain.Professor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test ID Generators - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class IdGeneratorTest extends AbstractTestCase {

    @Test
    void test_Given_GeneratedValue_Strategy_When_Persisting_Then_Insert_New_Id() {

        // Given
        Supplier<Professor> professorSupplier = () -> Professor.builder()
                .firstName(RandomStringUtils.randomAlphabetic(12))
                .lastName(RandomStringUtils.randomAlphabetic(12))
                .version(this.getVersion())
                .build();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        IntStream.range(0, 10)
                .forEach(i -> this.entityManager.persist(professorSupplier.get()));

        // Then
        transaction.commit();
    }

}///:~