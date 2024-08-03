package com.github.supercoding.respository.airplane.airlineTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineTicketJpaRepository extends JpaRepository<AirlineTicketEntity, Integer> {

	List<AirlineTicketEntity> findAirlineTicketsByArrivalLocationAndTicketType(String likePlace, String ticketType);
}