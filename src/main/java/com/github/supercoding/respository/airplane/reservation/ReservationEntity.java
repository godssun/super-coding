package com.github.supercoding.respository.airplane.reservation;

import com.github.supercoding.respository.airplane.airlineTicket.AirlineTicketEntity;
import com.github.supercoding.respository.airplane.passenger.PassengerEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation")
public class ReservationEntity {
	@Id @Column(name = "reservation_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "passenger_id")
	private PassengerEntity passengerEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airline_ticket_id")
	private AirlineTicketEntity airlineTicketEntity;

	@Column(name = "reservation_status", length = 10)
	private String reservationStatus;

	@Column(name = "reserve_at")
	private LocalDateTime reserveAt;


	public ReservationEntity(PassengerEntity passengerEntity, AirlineTicketEntity airlineTicketEntity) {
		this.passengerEntity = passengerEntity;
		this.airlineTicketEntity = airlineTicketEntity;
		this.reservationStatus = "대기";
		this.reserveAt = LocalDateTime.now();
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ReservationEntity that)) return false;
		return Objects.equals(reservationId, that.reservationId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(reservationId);
	}
}
