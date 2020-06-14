//: com.yulikexuan.japlab.mappings.domain.OrderItem.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Data
//@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Version
    private Long version;

    private String article;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

}///:~