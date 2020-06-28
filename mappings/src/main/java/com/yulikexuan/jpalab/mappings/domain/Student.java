//: com.yulikexuan.jpalab.mappings.domain.Student.java


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


@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Version
    private long version;

    @Null
    @CreationTimestamp
    private OffsetDateTime creationTime;

    @Null
    @UpdateTimestamp
    private OffsetDateTime lastModifiedTime;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private StudentStatus state;

    @ManyToMany
    //    @JoinTable(name = "enrollments",
    //            joinColumns = @JoinColumn(name = "c_id"),
    //            inverseJoinColumns = @JoinColumn(name = "s_id"))
    private Set<Course> courses = Sets.newHashSet();

    public Set<Course> getCourses() {
        return ImmutableSet.copyOf(this.courses);
    }

    public void addCourse(Course course) {

        if (Objects.nonNull(course)) {

            if (Objects.isNull(this.courses)) {
                this.courses = Sets.newHashSet();
            }

            this.courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        if (Objects.nonNull(this.courses)) {
            this.courses.remove(course);
        }
    }

    public boolean hasCourse(Course course) {
        return Objects.isNull(this.courses) ? false :
                Objects.isNull(course) ? false :
                        this.courses.contains(course);
    }

    @Override
    public boolean equals(Object o) {

        if ((Objects.isNull(o)) || (! (o instanceof Student))) {
            return false;
        }

        final Student other = (Student) o;

        return other.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}///:~