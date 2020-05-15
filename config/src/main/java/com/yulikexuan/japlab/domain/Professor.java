//: com.yulikexuan.japlab.domain.Professor.java


package com.yulikexuan.japlab.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Professor {
	@Id
	private Long id;
	private String firstName;
	private String lastName;
}
