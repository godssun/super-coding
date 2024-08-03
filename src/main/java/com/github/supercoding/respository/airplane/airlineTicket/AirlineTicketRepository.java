package com.github.supercoding.respository.airplane.airlineTicket;

import java.util.List;
import java.util.Optional;

public interface AirlineTicketRepository {
	List<AirlineTicketEntity> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType);

	List<AirlineTicketAndFlightInfo> findAllAirLineTicketAndFlightInfo(Integer airlineTicketId);



}
