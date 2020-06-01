//: com.yulikexuan.japlab.mappings.domain.PurchaseOrder.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class PurchaseOrder {

    @Id
    private Long id;

    private Long version;

    private Double amount;

    private String customerName;

}///:~