//: com.yulikexuan.jpalab.mappings.domain.Student.java


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
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private StudentStatus state;

    @Version
    private long version;

}///:~