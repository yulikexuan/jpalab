//: com.yulikexuan.japlab.mappings.domain.PurchaseOrder.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.UUID;


@Data
// @Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Double amount;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private Status status;

    private OffsetDateTime orderTime;

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