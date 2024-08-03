package com.github.supercoding.web.controller;


import com.github.supercoding.respository.airplane.reservation.ReservationEntity;
import com.github.supercoding.service.AirReservationService;
import com.github.supercoding.service.exceptions.InvalidValueException;
import com.github.supercoding.service.exceptions.NotAcceptException;
import com.github.supercoding.service.exceptions.NotFoundException;
import com.github.supercoding.web.dto.airline.ReservationRequest;
import com.github.supercoding.web.dto.airline.ReservationResult;
import com.github.supercoding.web.dto.airline.Ticket;
import com.github.supercoding.web.dto.airline.TicketResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/air-reservation")
@Slf4j
public class AirReservationController {

	private AirReservationService airReservationService;

	public AirReservationController(AirReservationService airReservationService) {
		this.airReservationService = airReservationService;
	}


//	@GetMapping("/tickets")
//	public TicketResponse findAirlineTickets(@RequestParam("user-Id") Integer userId,
//	                                         @RequestParam("airline-ticket-type") String ticketType ){
//
//		try {
//			List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
//			return new TicketResponse(tickets);
//		} catch (InvalidValueException invalidValueException) {
//
//		}
//
//	}
//TODO: 나중에 지워라
	@GetMapping("/tickets")
	@ResponseStatus(HttpStatus.OK)
	public TicketResponse findAirlineTickets(@RequestParam("user-Id") Integer userId,
	                                         @RequestParam("airline-ticket-type") String ticketType ) {

			List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
			TicketResponse ticketResponse = new TicketResponse(tickets);
		return ticketResponse;

	}

	@PostMapping("/reservations")
	@ResponseStatus(HttpStatus.CREATED)
	public ReservationResult makeReservation(@RequestBody ReservationRequest reservationRequest){
			ReservationResult reservationResult = airReservationService.makeReservation(reservationRequest);
		return reservationResult;
	}

	@GetMapping("/users-sum-price")
	public Double findUserFlightSumPrice(@RequestParam("user-id") Integer userId) {
		Double sum = airReservationService.findUserFlightSumPrice(userId);
		return sum;
	}
}
