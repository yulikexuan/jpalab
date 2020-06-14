//: com.yulikexuan.jpalab.mappings.domain.Course.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.UUID;


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

    private String name;

    private OffsetDateTime startDate;

    private OffsetTime beginLecture;

    private OffsetDateTime examTime;

    @Null
    @Version
    private long version;

    @Null
    @CreationTimestamp
    private OffsetDateTime creationTime;

    @Null
    @UpdateTimestamp
    private OffsetDateTime lastModifiedTime;

}///:~