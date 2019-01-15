package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByHotel(Hotel hotel);
	
}
