//: com.yulikexuan.jpalab.mappings.domain.Post.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;


@Getter @Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Post {

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

    private String title;

}///:~