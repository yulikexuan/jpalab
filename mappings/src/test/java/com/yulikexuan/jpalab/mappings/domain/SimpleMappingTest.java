//: com.yulikexuan.jpalab.mappings.domain.SimpleMappingTest.java


package com.yulikexuan.jpalab.mappings.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;


//@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName("Test Simple JPA Mappings - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SimpleMappingTest extends AbstractTestCase {

    private static Map<String, Supplier<?>> entitySuppliers;

    @BeforeAll
    static void beforeAll() {
        entitySuppliers = Map.of(
                Professor.class.getSimpleName(),
                () -> Professor.builder()
                        .firstName("Jane")
                        .lastName("Doe")
                        .build(),
                PurchaseOrder.class.getSimpleName(),
                () -> PurchaseOrder.builder()
                        .customerName("John")
                        .amount(1234.56)
                        .build(),
                OrderItem.class.getSimpleName(),
                () -> OrderItem.builder()
                        .article("iPhoneX")
                        .quantity(10)
                        .build());
    }

    @Test
    void test_Given_Entities_Then_Create_DB_Records() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();

        transaction.begin();

        // When
        Professor professor = (Professor) entitySuppliers.get(
                Professor.class.getSimpleName()).get();

        System.out.println(professor.getId());

        this.entityManager.persist(professor);
        this.entityManager.persist(entitySuppliers.get(
                PurchaseOrder.class.getSimpleName()).get());
        this.entityManager.persist(entitySuppliers.get(
                OrderItem.class.getSimpleName()).get());

        transaction.commit();
    }

}///:~