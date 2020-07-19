//: com.yulikexuan.jpalab.mappings.domain.Customer.java


package com.yulikexuan.jpalab.mappings.domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Customer extends BaseEntity {

    private String name;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrders = Lists.newArrayList();

    public List<PurchaseOrder> getPurchaseOrders() {
        return ImmutableList.copyOf(this.purchaseOrders);
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {

        if (Objects.isNull(purchaseOrder)) {
            return;
        }

        if (Objects.isNull(this.purchaseOrders)) {
            this.purchaseOrders = Lists.newArrayList();
        }

        this.purchaseOrders.add(purchaseOrder);
    }

}///:~