package com.github.supercoding.service.mapper;

import com.github.supercoding.respository.airplane.airlineTicket.AirlineTicketEntity;
import com.github.supercoding.web.dto.airline.Ticket;
import com.sun.jdi.InterfaceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Mapper
public interface TicketMapper {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

	@Mapping(target = "depart", source = "departureLocation")
	@Mapping(target = "arrival", source = "arrivalLocation")
	@Mapping(target = "departureTime", source = "departureAt", qualifiedByName = "convert")
	@Mapping(target = "returnTime", source = "returnAt", qualifiedByName = "convert")
	Ticket airlineTicketToTicket(AirlineTicketEntity airlineTicketEntity);

	@Named("convert")
	static String localDateTimeToString(LocalDateTime localDateTime){
		if(localDateTime != null) return localDateTime.format(formatter);
		else return null;
	}
}
