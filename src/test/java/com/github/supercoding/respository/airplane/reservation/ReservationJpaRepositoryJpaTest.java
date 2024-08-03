package com.github.supercoding.respository.airplane.reservation;

import com.github.supercoding.respository.airplane.airlineTicket.AirlineTicketEntity;
import com.github.supercoding.respository.airplane.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercoding.respository.airplane.passenger.PassengerEntity;
import com.github.supercoding.respository.airplane.passenger.PassengerJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest // slice test => Dao Lay / Jpa 사용하고 있는 Slice Test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class ReservationJpaRepositoryJpaTest {

//    @Autowired
//    private AirReservationService airReservationService;

	@Autowired
	private ReservationJpaRepository reservationJpaRepository;

	@Autowired
	private PassengerJpaRepository passengerJpaRepository;

	@Autowired
	private AirlineTicketJpaRepository airlineTicketJpaRepository;

	@DisplayName("ReservationRepository로 항공편 가격과 수수료 검색")
	@Test
	void FindFlightPriceAndCharge() {
		// given
		Integer userId = 10;

		// when
		List<FlightPriceAndCharge> flightPriceAndCharges = reservationJpaRepository.findFlightPriceAndCharge(userId);

		// then
		log.info("결과: " + flightPriceAndCharges);
	}

	@DisplayName("Reservation 예약 진행")
	@Test
	void saveReservation(){
		// given
		Integer userId = 10;
		Integer ticketId = 5;

		PassengerEntity passengerEntity = passengerJpaRepository.findPassengerEntityByUserEntityUserId(userId).get();
		AirlineTicketEntity airlineTicketEntity = airlineTicketJpaRepository.findById(5).get();

		// when
		ReservationEntity reservationEntity = new ReservationEntity(passengerEntity, airlineTicketEntity);
		ReservationEntity res = reservationJpaRepository.save(reservationEntity);

		// then
		log.info("결과: " + res);
		assertEquals(res.getPassengerEntity(), passengerEntity);
		assertEquals(res.getAirlineTicketEntity(), airlineTicketEntity);

	}
}