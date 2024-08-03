package com.github.supercoding.respository.airplane.airlineTicket;

import com.github.supercoding.respository.airplane.flight.FlightEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "ticketId")
@Table(name = "airline_ticket")
public class AirlineTicketEntity {
	@Id
	@Column(name = "ticket_id")
	private Integer ticketId;
	@Column(name = "ticket_type", length = 5, columnDefinition = "CHECK (ticket_type in ('편도', '왕복')) ")
	private String ticketType;
	@Column(name = "departure_loc", length = 20)
	private String departureLocation;
	@Column(name = "arrival_loc", length = 20)
	private String arrivalLocation;

	@Column(name = "departure_at", nullable = false)
	private LocalDateTime departureAt;
	@Column(name = "return_at", nullable = false)
	private LocalDateTime returnAt;

	@Column(name = "tax")
	private Double tax;

	@Column(name = "total_price")
	private Double totalPrice;

	@OneToMany(mappedBy = "airlineTicketEntity")
	private List<FlightEntity> flightList;


//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (!(o instanceof AirlineTicketEntity that)) return false;
//		return Objects.equals(ticketId, that.ticketId);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hashCode(ticketId);
//	}
}
