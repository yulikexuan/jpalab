//: com.yulikexuan.jpalab.mappings.domain.CustomerPurchaseOrderTest.java


package com.yulikexuan.jpalab.mappings.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Test Bidirectional Many to One Association - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CustomerPurchaseOrderTest extends AbstractTestCase {

    private static UUID customerId;

    @Test
    @Order(1)
    void test_Creating_Association_Between_Customer_And_PurchaseOrder() {

        // Given
        customerId = createCustomer();
        UUID purchaseOrderId_1 = createPurchaseOrder();
        UUID purchaseOrderId_2 = createPurchaseOrder();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Customer customer = this.entityManager.find(Customer.class, customerId);
        PurchaseOrder purchaseOrder_1 = this.entityManager.find(PurchaseOrder.class,
                purchaseOrderId_1);
        PurchaseOrder purchaseOrder_2 = this.entityManager.find(PurchaseOrder.class,
                purchaseOrderId_2);

        // When
        purchaseOrder_1.setCustomer(customer);
        purchaseOrder_2.setCustomer(customer);
        customer.addPurchaseOrder(purchaseOrder_1);
        customer.addPurchaseOrder(purchaseOrder_2);

        // Then
        transaction.commit();
    }

    @Test
    @Order(2)
    void test_Association_Between_Customer_PurchaseOrder() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Customer customer = this.entityManager.find(Customer.class, customerId);

        // When
        Set<PurchaseOrder> purchaseOrders = customer.getPurchaseOrders();

        // Then
        assertThat(purchaseOrders).hasSize(2);

        transaction.commit();
    }

    private UUID createCustomer() {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Customer customer = Customer.builder()
                .name("Yul")
                .phoneNumber("1234567890")
                .build();

        this.entityManager.persist(customer);

        UUID id = customer.getId();

        transaction.commit();

        return id;
    }

    private UUID createPurchaseOrder() {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .stuff(RandomStringUtils.randomAlphanumeric(30))
                .orderTime(OffsetDateTime.now())
                .build();

        this.entityManager.persist(purchaseOrder);

        UUID id = purchaseOrder.getId();

        transaction.commit();

        return id;
    }

}///:~

