//: com.yulikexuan.jpalab.mappings.domain.Course.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Objects;
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
@ToString
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

    private OffsetDateTime startDate;

    private OffsetTime beginLecture;

    private OffsetDateTime examTime;

    @ManyToOne
    private Professor professor;

    public void setProfessor(Professor professor) {

        // Remove old association from old professor
        if (Objects.nonNull(this.professor)) {
            this.professor.removeCourse(this);
        }

        // add new association to new professor
        if (Objects.nonNull(professor)) {
            professor.addCourse(this);
        }

        // Finish setting professor
        this.professor = professor;
    }

    public boolean isTaughtBy(Professor professor) {
        return Objects.isNull(professor) ?
                false : professor.equals(this.professor);
    }

}///:~