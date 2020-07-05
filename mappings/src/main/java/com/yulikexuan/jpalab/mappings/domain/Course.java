//: com.yulikexuan.jpalab.mappings.domain.Course.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
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

    /*
     * Bidirectional one to one association
     *
     * If removing OneToOne annotation here, then applying unidirectional
     * one to one association
     */
    @OneToOne(mappedBy = "course")
    private Curriculum curriculum;

    private String name;

}///:~