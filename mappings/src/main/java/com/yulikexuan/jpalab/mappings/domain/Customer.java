//: com.yulikexuan.jpalab.mappings.domain.Customer.java


package com.yulikexuan.jpalab.mappings.domain;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Customer extends BaseEntity {

    private String name;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<PurchaseOrder> purchaseOrders = Sets.newHashSet();

    public Set<PurchaseOrder> getPurchaseOrders() {
        return ImmutableSet.copyOf(this.purchaseOrders);
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder) {

        if (Objects.isNull(purchaseOrder)) {
            return;
        }

        if (Objects.isNull(this.purchaseOrders)) {
            this.purchaseOrders = Sets.newHashSet();
        }

        this.purchaseOrders.add(purchaseOrder);
    }

    @Override
    public boolean equals(Object o) {

        if (! (o instanceof Customer)) {
            return false;
        }

        final Customer other = (Customer) o;

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}///:~