//: com.yulikexuan.jpalab.mappings.domain.BidirectionalMany2ManyTest.java


package com.yulikexuan.jpalab.mappings.domain;


import com.google.common.collect.ImmutableList;
import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Many2Many Association Mapping - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BidirectionalMany2ManyTest extends AbstractTestCase {

    private static UUID courseId_1;

    @Test
    @Order(1)
    void test_Given_Courses_And_Students_Then_Associate_Them_To_Each_Other() {

        // Given
        String[] courseNames = {"Core Java", "Java JPA"};
        List<UUID> courseIds = this.createCourses(courseNames);

        List<ImmutablePair<String, String>> studentNames = List.of(
                ImmutablePair.of("Mike", "Lee"),
                ImmutablePair.of("Tommee", "Smith"));
        List<UUID> studentIds = this.createStudents(studentNames);

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Course course_1 = this.entityManager.find(Course.class, courseIds.get(0));
        Course course_2 = this.entityManager.find(Course.class, courseIds.get(1));

        Student student_1 = this.entityManager.find(Student.class, studentIds.get(0));
        Student student_2 = this.entityManager.find(Student.class, studentIds.get(1));

        course_1.addStudent(student_1);
        course_1.addStudent(student_2);
        course_2.addStudent(student_1);

        transaction.commit();

        courseId_1 = course_1.getId();

        // When
        Set<Student> studentsOfCourse_1 = course_1.getStudents();
        Set<Student> studentsOfCourse_2 = course_2.getStudents();

        Set<Course> coursesOfStudent_1 = student_1.getCourses();
        Set<Course> coursesOfStudent_2 = student_2.getCourses();

        // Then
        assertThat(studentsOfCourse_1).contains(student_1, student_2);
        assertThat(studentsOfCourse_2).containsOnly(student_1);
        assertThat(coursesOfStudent_1).contains(course_1, course_2);
        assertThat(coursesOfStudent_2).containsOnly(course_1);
    }

    @Test
    @Order(2)
    void test_And_Update_Many2Many_Association_Between_Courses_And_Students() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Course course = this.entityManager.find(Course.class, courseId_1);

        // When
        Set<Student> students = course.getStudents();

        List<String> firstNames = students.stream()
                .map(Student::getFirstName)
                .collect(ImmutableList.toImmutableList());

        transaction.commit();

        // Then
        assertThat(firstNames).contains("Mike", "Tommee");
    }

    private List<UUID> createCourses(String... courseNames) {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        List<UUID> ids = Arrays.stream(courseNames)
                .map(n -> {
                    Course course = new Course();
                    course.setName(n);
                    this.entityManager.persist(course);
                    return course.getId();
                })
                .collect(ImmutableList.toImmutableList());

        transaction.commit();

        return ids;
    }

    private List<UUID> createStudents(List<ImmutablePair<String, String>> names) {

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        List<UUID> ids = names.stream()
                .map(pair -> {
                    Student student = new Student();
                    student.setFirstName(pair.getLeft());
                    student.setLastName(pair.getRight());
                    student.setState(StudentStatus.ENROLLED);
                    this.entityManager.persist(student);
                    return student.getId();
                })
                .collect(ImmutableList.toImmutableList());

        transaction.commit();

        return ids;
    }

}///:~