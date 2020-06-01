//: com.yulikexuan.japlab.mappings.domain.Professor.java


package com.yulikexuan.jpalab.mappings.domain;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Professor {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_generator")
	@SequenceGenerator(name = "professor_generator", sequenceName = "professor_seq")
	private Long id;

	private Long version;

	private String firstName;

	private String lastName;

}
