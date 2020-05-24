//: com.yulikexuan.japlab.mappings.domain.SimpleMappingTest.java


package com.yulikexuan.japlab.mappings.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.util.Map;
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
                        .id(Long.valueOf(System.currentTimeMillis()))
                        .firstName("Jane")
                        .lastName("Doe")
                        .build(),
                PurchaseOrder.class.getSimpleName(),
                () -> PurchaseOrder.builder()
                        .id(Long.valueOf(System.currentTimeMillis() + 1))
                        .customerName("John")
                        .amount(1234.56)
                        .build(),
                OrderItem.class.getSimpleName(),
                () -> OrderItem.builder()
                        .id(Long.valueOf(System.currentTimeMillis() + 2))
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
        this.entityManager.persist(entitySuppliers.get(
                Professor.class.getSimpleName()).get());
        this.entityManager.persist(entitySuppliers.get(
                PurchaseOrder.class.getSimpleName()).get());
        this.entityManager.persist(entitySuppliers.get(
                OrderItem.class.getSimpleName()).get());

        transaction.commit();
    }

}///:~