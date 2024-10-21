package com.leadconsult.task.model;

import com.leadconsult.task.constant.CourseType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="courses")
@Builder
@AllArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id", nullable = false)
	private Long courseId;

	@NotNull
	@Column(unique = true, nullable = false)
	private String name;

	private CourseType type;

}
