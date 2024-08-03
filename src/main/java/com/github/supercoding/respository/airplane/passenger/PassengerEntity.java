package com.github.supercoding.respository.airplane.passenger;

import com.github.supercoding.respository.airplane.reservation.ReservationEntity;
import com.github.supercoding.respository.airplane.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "passengerId")
@Builder
@Entity
@Table(name = "passenger")
public class PassengerEntity {

	@Id
	@Column(name = "passenger_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer passengerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@Column(name = "passport_num", length = 50)
	private String passportNum;

	@OneToMany(mappedBy = "passengerEntity")
	private List<ReservationEntity> reservations;
}
