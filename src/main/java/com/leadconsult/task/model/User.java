package com.leadconsult.task.model;

import com.leadconsult.task.constant.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="users")
@Builder
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false,name = "user_id")
	private Long userId;

	@NotNull
	private String name;

	@NotNull
	private Short age;

	@NotNull
	private UserType userType;

	@ManyToMany
	@JoinTable(
			name = "user_group",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "group_id",table = "groups")
	)
	@ToString.Exclude
	private Set<Group> groups;

	@ManyToMany
	@JoinTable(
			name = "user_course",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	@ToString.Exclude
	private Set<Course>  courses;


//	@Override
//	public final boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null) return false;
//		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
//		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
//		if (thisEffectiveClass != oEffectiveClass) return false;
//		User user = (User) o;
//		return getUserId() != null && Objects.equals(getUserId(), user.getUserId());
//	}
//
//	@Override
//	public final int hashCode() {
//		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
//	}
}
