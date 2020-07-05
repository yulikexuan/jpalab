//: com.yulikexuan.jpalab.mappings.domain.BidirectOneToOneTest.java


package com.yulikexuan.jpalab.mappings.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Bidirectional One to One Association Mapping - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BidirectOneToOneTest extends AbstractTestCase  {

    private static final String CURRICULUM_DESCRIPTION = "Learning Java Language";

    private static UUID currentCourseId;

    @Test
    @Order(1)
    void test_Given_A_Course_And_A_Curriculum_Then_Create_Bi_One_To_One_Association() {
        this.create_One_To_One_Association_Between_Course_And_Curriculum();
    }

    private void create_One_To_One_Association_Between_Course_And_Curriculum() {
        Course course = this.createCourse("Thinking in Java");
        UUID curriculumId = this.createCurriculum(CURRICULUM_DESCRIPTION, course);
        currentCourseId = course.getId();
    }

    @Test
    @Order(2)
    void test_One_To_One_Association_Between_Course_And_Curriculum() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Course course = this.entityManager.find(Course.class, currentCourseId);

        // When
        String curriculumDescription = course.getCurriculum().getDescription();

        // Then
        assertThat(curriculumDescription).isEqualTo(CURRICULUM_DESCRIPTION);

        transaction.commit();
    }

    private Course createCourse(String courseName) {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Course course = new Course();
        course.setName(courseName);

        this.entityManager.persist(course);

        transaction.commit();

        return course;
    }

    private UUID createCurriculum(String description, Course course) {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        course = this.entityManager.merge(course);
        Curriculum curriculum = new Curriculum();
        curriculum.setDescription(description);
        curriculum.setCourse(course);
        course.setCurriculum(curriculum);
        this.entityManager.persist(curriculum);

        transaction.commit();

        return curriculum.getId();
    }

}///:~