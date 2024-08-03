package com.github.supercoding.respository.airplane.users;

import com.github.supercoding.respository.airplane.passenger.PassengerEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "userId")
@Table(name = "users")
public class UserEntity {

	@Id @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "user_name", length = 20)
	private String userName;

	@Column(name = "like_travel_place", length = 30)
	private String likeTravelPlace;

	@Column(name = "phone_num", length = 30)
	private String phoneNum;

	@OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
	private List<PassengerEntity> passengers;


//	@Override
//	public boolean equals(Object o) {
//		if (this == o) {
//			return true;
//		}
//		if (!(o instanceof UserEntity)) {
//			return false;
//		}
//
//		UserEntity that = (UserEntity) o;
//
//		return userId.equals(that.userId);
//	}
//
//	@Override
//	public int hashCode() {
//		return userId.hashCode();
//	}
}
