package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.HotelReservation;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long>{

}
