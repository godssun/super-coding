package com.github.supercoding.respository.airplane.flight;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightJpaRepository extends JpaRepository<FlightEntity, Integer> {


}