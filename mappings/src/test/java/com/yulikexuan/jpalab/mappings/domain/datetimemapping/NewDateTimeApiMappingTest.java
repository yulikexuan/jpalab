//: com.yulikexuan.jpalab.mappings.domain.datetimemapping.NewDateTimeApiMappingTest.java


package com.yulikexuan.jpalab.mappings.domain.datetimemapping;


import com.yulikexuan.japlab.AbstractTestCase;
import com.yulikexuan.jpalab.mappings.domain.Course;
import com.yulikexuan.jpalab.mappings.domain.PurchaseOrder;
import com.yulikexuan.jpalab.mappings.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.time.*;
import java.util.UUID;


@Slf4j
@DisplayName("New Date and Time API Mapping Test - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NewDateTimeApiMappingTest extends AbstractTestCase {

    private static ZoneOffset defaultZoneOffset;
    private static String courseId;
    private static String purchaseOrderId;

    private Course course;
    private PurchaseOrder purchaseOrder;

    @BeforeAll
    static void beforeAll() {
        defaultZoneOffset = OffsetDateTime.now().getOffset();
    }

    @Test
    @Order(1)
    void test_Given_Course_When_Persisting_Then_Mapping_DateTime_Properties_Without_Annotation() {

        // Given
        LocalDateTime startDateTime =
                LocalDateTime.of(2020, Month.SEPTEMBER, 1,
                        0, 0, 0);

        OffsetDateTime startDate = OffsetDateTime.of(startDateTime, defaultZoneOffset);

        OffsetTime lectureTime = OffsetTime.of(LocalTime.of(10, 0, 0),
                defaultZoneOffset);

        LocalDateTime examTime = LocalDateTime.of(
                2020, Month.DECEMBER, 20,
                9, 0, 0);

        OffsetDateTime examDateTime = OffsetDateTime.of(examTime, defaultZoneOffset);
        this.course = Course.builder()
                .name("Effective Java")
                .startDate(startDate)
                .beginLecture(lectureTime)
                .examTime(examDateTime)
                .build();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        this.entityManager.persist(course);
        courseId = course.getId().toString();

        // Then
        transaction.commit();
    }

    @Test
    @Order(2)
    void test_Given_UUID_When_Query_Then_Get_Course_Info() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        Course course = this.entityManager.find(Course.class,
                UUID.fromString(courseId));
        log.info(">>>>>>> You have course assigned: {}", course);

        // Then
        transaction.commit();
    }

    @Test
    @Order(3)
    void test_Given_PurchaseOrder_When_Persisting_Then_Mapping_DateTime_Properties_Without_Annotation() {

        // Given
        OffsetDateTime orderTime = OffsetDateTime.of(
                LocalDateTime.of(2020, Month.JUNE, 15,
                        9, 0, 0, 0),
                defaultZoneOffset);

        this.purchaseOrder = PurchaseOrder.builder()
                .amount(99.99)
                .customerName("Berry")
                .status(Status.ORDERED)
                .orderTime(orderTime)
                .build();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        this.entityManager.persist(this.purchaseOrder);
        purchaseOrderId = this.purchaseOrder.getId().toString();

        // Then
        transaction.commit();
    }

    @Test
    @Order(4)
    void test_Given_UUID_When_Query_Then_Get_PurchaseOrder_Info() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        PurchaseOrder purchaseOrder = this.entityManager.find(
                PurchaseOrder.class, UUID.fromString(this.purchaseOrderId));
        log.info(">>>>>>> You have purchase order assigned: {}", purchaseOrder);

        // Then
        transaction.commit();
    }

}///:~