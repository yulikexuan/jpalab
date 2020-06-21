//: com.yulikexuan.japlab.mappings.domain.Professor.java


package com.yulikexuan.jpalab.mappings.domain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Professor {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Null
	@Version
	private long version;

	@Null
	@CreationTimestamp
	private OffsetDateTime creationTime;

	@Null
	@UpdateTimestamp
	private OffsetDateTime lastModifiedTime;

	private String firstName;

	private String lastName;

}///:!