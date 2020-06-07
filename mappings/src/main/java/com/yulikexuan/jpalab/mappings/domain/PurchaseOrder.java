//: com.yulikexuan.japlab.mappings.domain.PurchaseOrder.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;


@Data
//@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Long version;

    private Double amount;

    private String customerName;

}///:~