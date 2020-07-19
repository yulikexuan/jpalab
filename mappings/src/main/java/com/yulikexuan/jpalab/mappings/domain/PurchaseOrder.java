//: com.yulikexuan.jpalab.mappings.domain.PurchaseOrder.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class PurchaseOrder extends BaseEntity {

    private String stuff;
    private OffsetDateTime orderTime;

    @ManyToOne
    private Customer customer;

    @Override
    public boolean equals(Object o) {

        if (! (o instanceof PurchaseOrder)) {
            return false;
        }

        final PurchaseOrder other = (PurchaseOrder) o;

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}///:~