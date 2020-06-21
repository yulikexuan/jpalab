//: com.yulikexuan.jpalab.mappings.domain.BidirectionalManyToOneMappingTest.java


package com.yulikexuan.jpalab.mappings.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.time.*;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Bidirectional Many to One Association - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BidirectionalManyToOneMappingTest extends AbstractTestCase {

    private static ZoneOffset defaultZoneOffset;

    @BeforeAll
    static void beforeAll() {
        defaultZoneOffset = OffsetDateTime.now().getOffset();
    }

    @BeforeEach
    protected void setUp() {
        super.setUp();
    }

    @Test
    void test_Given_Professor_And_Course_Then_Verify_Many_To_One_Association() {

        // Given
        LocalDateTime startDateTime =
                LocalDateTime.of(2020, Month.SEPTEMBER, 1,
                        0, 0, 0);
        OffsetDateTime startDate = OffsetDateTime.of(
                startDateTime, defaultZoneOffset);
        OffsetTime lectureTime = OffsetTime.of(LocalTime.of(10, 0, 0),
                defaultZoneOffset);
        LocalDateTime examTime = LocalDateTime.of(
                2020, Month.DECEMBER, 20,
                9, 0, 0);
        OffsetDateTime examDateTime = OffsetDateTime.of(examTime, defaultZoneOffset);

        EntityTransaction transaction = this.entityManager.getTransaction();

        UUID courseId = this.createCourse("Effective Java", startDate,
                lectureTime, examDateTime);
        UUID professorId = this.createProfessor("Jane", "Doe");

        transaction.begin();

        Course course = this.entityManager.find(Course.class, courseId);
        Professor professor = this.entityManager.find(Professor.class, professorId);
        assertThat(professor.getCourses()).isNotNull();

        // When
        course.setProfessor(professor);

        // Then
        transaction.commit();
        assertThat(professor.hasCourse(course)).isTrue();
        assertThat(course.isTaughtBy(professor));
    }

    @Test
    void test_Given_A_Professor_With_Courses_Then_Print_All_Course_Details() {

        // Given
        UUID profId = this.prepareProfessorWithCourse();
        this.closeEntityManager();

        this.entityManager = this.entityManager();
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Professor professor = this.entityManager.find(Professor.class, profId);

        professor.getCourses().stream()
                .forEach(course -> System.out.printf(">>>>>>> Has course %s%n",
                        course.getName()));

        transaction.commit();

        assertThat(professor.getNumberOfCourses()).isEqualTo(2);
    }

    private UUID prepareProfessorWithCourse() {

        UUID professorId = this.createProfessor("Donald", "Trump");

        OffsetDateTime startDate = OffsetDateTime.of(
                LocalDateTime.of(2020, Month.SEPTEMBER, 1,
                        0, 0, 0),
                defaultZoneOffset);
        OffsetTime lectureTime = OffsetTime.of(
                LocalTime.of(10, 0, 0), defaultZoneOffset);
        OffsetDateTime examDateTime = OffsetDateTime.of(
                LocalDateTime.of(
                        2020, Month.DECEMBER, 20,
                        9, 0, 0),
                defaultZoneOffset);

        UUID courseId_1 = this.createCourse("Effective Java 3e",
                startDate, lectureTime, examDateTime);

        OffsetDateTime startDate2 = OffsetDateTime.of(
                LocalDateTime.of(2020, Month.SEPTEMBER, 1,
                        0, 0, 0),
                defaultZoneOffset);
        OffsetTime lectureTime2 = OffsetTime.of(
                LocalTime.of(19, 0, 0), defaultZoneOffset);
        OffsetDateTime examDateTime2 = OffsetDateTime.of(
                LocalDateTime.of(
                        2020, Month.DECEMBER, 22,
                        9, 0, 0),
                defaultZoneOffset);

        UUID courseId_2 = this.createCourse("Modern Java",
                startDate2, lectureTime2, examDateTime2);

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Professor prof = this.entityManager.find(Professor.class, professorId);
        Course course_1 = this.entityManager.find(Course.class, courseId_1);
        Course course_2 = this.entityManager.find(Course.class, courseId_2);
        course_1.setProfessor(prof);
        course_2.setProfessor(prof);

        assertThat(prof.getNumberOfCourses()).isEqualTo(2);

        transaction.commit();

        return professorId;
    }

    private UUID createCourse(String courseName, OffsetDateTime startDate,
                              OffsetTime beginLecture, OffsetDateTime examTime) {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Course course = Course.builder()
                .name(courseName)
                .startDate(startDate)
                .beginLecture(beginLecture)
                .examTime(examTime)
                .build();

        this.entityManager.persist(course);

        transaction.commit();

        return course.getId();
    }

    private UUID createProfessor(String firstName, String lastName) {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Professor professor =  Professor.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        this.entityManager.persist(professor);

        transaction.commit();

        return professor.getId();
    }

}///:~