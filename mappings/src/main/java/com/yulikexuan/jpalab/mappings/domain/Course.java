//: com.yulikexuan.jpalab.mappings.domain.Course.java


package com.yulikexuan.jpalab.mappings.domain;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


/*
 * Course entity is the owning side of the association between Course and Professor
 * Because course table has a column professor_id but professor table have nothing
 * from course table
 *
 * The owning side should define the association
 * The referencing side, Professor, just references the other side of the
 * association
 */
@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Null
    @Version
    private long version;

    @Null
    @CreationTimestamp
    private OffsetDateTime creationTime;

    @Null
    @UpdateTimestamp
    private OffsetDateTime lastModifiedTime;

    private String name;

    @ManyToMany
//    @JoinTable(name = "enrollments",
//            joinColumns = @JoinColumn(name = "c_id"),
//            inverseJoinColumns = @JoinColumn(name = "s_id"))
    private Set<Student> students = Sets.newHashSet();

    public Set<Student> getStudents() {
        return ImmutableSet.copyOf(this.students);
    }

    public void addStudent(Student student) {

        if (Objects.nonNull(student)) {

            if (Objects.isNull(this.students)) {
                this.students = Sets.newHashSet();
            }

            this.students.add(student);

            if (!student.hasCourse(this)) {
                student.addCourse(this);
            }
        }
    }

    public void removeStudent(Student student) {
        if (Objects.nonNull(this.students)) {
            this.students.remove(student);
            if ((Objects.nonNull(student)) && student.hasCourse(this)) {
                student.removeCourse(this);
            }
        }
    }

    public boolean hasStudent(Student student) {
        return Objects.isNull(this.students) ? false :
                Objects.isNull(student) ? false :
                        this.students.contains(student);
    }

    @Override
    public boolean equals(Object o) {

        if ((Objects.isNull(o)) || (! (o instanceof Course))) {
            return false;
        }

        final Course other = (Course) o;

        return other.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}///:~