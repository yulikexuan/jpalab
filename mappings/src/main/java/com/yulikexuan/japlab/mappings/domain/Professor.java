//: com.yulikexuan.japlab.mappings.domain.Professor.java


package com.yulikexuan.japlab.mappings.domain;


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

	private Long version;

	private String firstName;

	private String lastName;

}
