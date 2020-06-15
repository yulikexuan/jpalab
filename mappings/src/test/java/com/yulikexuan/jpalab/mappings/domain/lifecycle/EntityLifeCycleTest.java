//: com.yulikexuan.jpalab.mappings.domain.lifecycle.EntityLifeCycleTest.java


package com.yulikexuan.jpalab.mappings.domain.lifecycle;


import com.yulikexuan.japlab.AbstractTestCase;
import com.yulikexuan.jpalab.mappings.domain.PurchaseOrder;
import com.yulikexuan.jpalab.mappings.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Entity Life Cycle - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EntityLifeCycleTest extends AbstractTestCase {

    private String originalCustomerName;
    private String newCustomerName;
    private double amount = 80.88;
    private double newAmount;

    private UUID orderId;
    private PurchaseOrder purchaseOrder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        this.originalCustomerName = "Berry Lin";
        this.newCustomerName = "Mike Lee";
        this.newAmount = 100.00;
        this.orderId = persistPurchaseOrder();
    }

    @Test
    @Order(1)
    void test_Given_New_Customer_Name_When_Update_Entity_Then_Update() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        PurchaseOrder existingPurchaseOrder = this.entityManager.find(
                PurchaseOrder.class, this.orderId);

        // When
        existingPurchaseOrder.setCustomerName(this.newCustomerName);

        // Then
        transaction.commit();
        assertThat(existingPurchaseOrder.getCustomerName())
                .isEqualTo(this.newCustomerName);
    }

    @Test
    @Order(2)
    void test_Detach_Persisted_PurchaseOrder() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        PurchaseOrder existingPurchaseOrder = this.entityManager.find(
                PurchaseOrder.class, this.orderId);

        this.entityManager.detach(existingPurchaseOrder);

        // When
        existingPurchaseOrder.setAmount(this.newAmount);

        // Then
        transaction.commit();
    }

    @Test
    @Order(3)
    void test_Reattach_Persisted_PurchaseOrder() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        PurchaseOrder existingPurchaseOrder = this.entityManager.find(
                PurchaseOrder.class, this.orderId);

        double actualAmount = existingPurchaseOrder.getAmount();

        this.entityManager.detach(existingPurchaseOrder);

        existingPurchaseOrder.setAmount(this.newAmount);

        // When
        this.entityManager.merge(existingPurchaseOrder);

        // Then
        transaction.commit();
        assertThat(existingPurchaseOrder.getAmount()).isEqualTo(this.newAmount);
    }

    @Test
    @Order(4)
    void test_Given_Existing_PurchaseOrder_Then_Remove_It() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        PurchaseOrder existingPurchaseOrder = this.entityManager.find(
                PurchaseOrder.class, this.orderId);

        // When
        this.entityManager.remove(existingPurchaseOrder);

        existingPurchaseOrder = this.entityManager.find(
                PurchaseOrder.class, existingPurchaseOrder.getId());

        // Then
        assertThat(existingPurchaseOrder).isNull();
        transaction.commit();
    }

    private UUID persistPurchaseOrder() {

        ZoneOffset defaultZoneOffset = OffsetDateTime.now().getOffset();
        LocalDateTime localOrderTime = LocalDateTime.of(
                2020, Month.APRIL, 1,
                9, 30, 59, 0);
        OffsetDateTime orderTime = OffsetDateTime.of(localOrderTime,
                defaultZoneOffset);

        this.purchaseOrder = PurchaseOrder.builder()
                .amount(amount)
                .customerName(this.originalCustomerName)
                .status(Status.ORDERED)
                .orderTime(orderTime)
                .build();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        this.entityManager.persist(purchaseOrder);

        transaction.commit();

        return purchaseOrder.getId();
    }

}///:~