package com.github.supercoding.respository.airplane.passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerJpaRepository extends JpaRepository<PassengerEntity, Integer> {

	Optional<PassengerEntity> findPassengerEntityByUserEntityUserId(Integer userId);

}
