package com.github.supercoding.respository.airplane.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Integer> {
	@Query("SELECT new com.github.supercoding.respository.airplane.reservation.FlightPriceAndCharge(f.flightPrice, f.charge) " +
			"FROM ReservationEntity r " +
			"JOIN r.passengerEntity p " +
			"JOIN r.airlineTicketEntity a " +
			"JOIN a.flightList f " +
			"WHERE p.userEntity.userId = :userId ")
	List<FlightPriceAndCharge> findFlightPriceAndCharge(Integer userId);
}