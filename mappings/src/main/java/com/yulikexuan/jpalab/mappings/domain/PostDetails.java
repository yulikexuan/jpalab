//: com.yulikexuan.jpalab.mappings.domain.PostDetails.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.UUID;


@Getter @Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class PostDetails {

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

    String destination;

    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

}///:~