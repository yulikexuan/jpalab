//: com.yulikexuan.japlab.mappings.domain.OrderItem.java


package com.yulikexuan.japlab.mappings.domain;


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
public class OrderItem {

    @Id
    private Long id;

    private Long version;

    private String article;

    private int quantity;

}///:~