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

	/*
	 * mappedBy attribute needs to reference the name of the attribute that owns
	 * the association, that's Course::professor
	 */
	@OneToMany(mappedBy = "professor")
	private List<Course> courses = new ArrayList<>();

	public void setCourses(List<Course> courses) {

		if (Objects.isNull(this.courses)) {
			this.courses = Lists.newArrayList();
		}

		if (Objects.nonNull(courses)) {
			this.courses.clear();
			this.courses.addAll(courses);
		}
	}

	public List<Course> getCourses() {
		if (Objects.isNull(this.courses)) {
			this.courses = Lists.newArrayList();
		}
		return ImmutableList.copyOf(this.courses);
	}

	public int getNumberOfCourses() {
		return this.courses.size();
	}

	public void addCourse(Course course) {

		if (Objects.isNull(this.courses)) {
			this.courses = Lists.newArrayList();
		}

		if (Objects.nonNull(course)) {
			this.courses.remove(course);
			this.courses.add(course);
		}
	}

	public void removeCourse(Course course) {

		if (Objects.isNull(this.courses)) {
			this.courses = Lists.newArrayList();
		}

		this.courses.remove(course);
	}

	public boolean hasCourse(Course course) {
		return Objects.isNull(course) ? false : Objects.isNull(this.courses) ?
				false : this.courses.contains(course);
	}

}///:!