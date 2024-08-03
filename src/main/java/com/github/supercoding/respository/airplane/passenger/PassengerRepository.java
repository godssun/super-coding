package com.github.supercoding.respository.airplane.passenger;

import java.util.Optional;

public interface PassengerRepository {
	Optional<PassengerEntity> findPassengerByUserId(Integer userId);
}
