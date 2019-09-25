package edu.ftn.isa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.AddHotelDTO;
import edu.ftn.isa.dto.BasicHotelInfoDTO;
import edu.ftn.isa.dto.FastRoomReservationDTO;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.RoomRepository;
import edu.ftn.isa.repositories.UserRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private HotelReservationRepository hotelResRepo;
	
	@Transactional
	public void saveHotel(Hotel hotel) {
		hotelRepo.save(hotel);
	}

	@Transactional
	public Hotel addHotel(AddHotelDTO HotelDto) {
		Hotel hotel = new Hotel();
		User user = new User();
		user.setEmail(HotelDto.getEmail());
		user.setUsername(HotelDto.getUsername());
		user.setEnabled(true);
		user.setRole(Role.HotelAdmin);
		user.setPassword(passwordEncoder.encode(HotelDto.getPassword()));
		user.setPasswordChanged(false);
		userRepo.save(user);
		hotel.setAdmin(user);
		hotel.setName(HotelDto.getName());
		hotel.setPromo(HotelDto.getPromo());
		hotel.setAddress(HotelDto.getAddress());
		Destination dest = destRepo.findByNameAndDeleted(HotelDto.getDestination(), false);
		hotel.setDestination(dest);
		hotelRepo.save(hotel);
		return hotel;
	}

	@Transactional
	public Hotel findHotelByAdmin(User user) {
		Hotel retVal = hotelRepo.findByAdmin(user);
		return retVal;
	}

	@Transactional
	public List<Hotel> findHotelsByAdmin(User user) {
		List<Hotel> hotels = hotelRepo.findHotelsByAdmin(user);
		return hotels;
	}
	@Transactional
	public Hotel findHotelById(Long id) {
		Optional<Hotel> temp = hotelRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		Hotel hotel = temp.get();
		return hotel;
	}
	@Transactional
	public Hotel updateBasicHotelInfo(User user, BasicHotelInfoDTO hotelDto) {
		Hotel retVal = hotelRepo.findByAdmin(user);
		retVal.setName(hotelDto.getName());
		retVal.setAddress(hotelDto.getAddress());
		retVal.setPromo(hotelDto.getPromo());
		hotelRepo.save(retVal);
		return retVal;
	}

	public Integer createFastReservation(Long id, FastRoomReservationDTO dto) throws ParseException {
		Optional<Room> optRoom = roomRepo.findById(id);
		if(!optRoom.isPresent()) {
			return -1;
		}
		Room room = optRoom.get();
		Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getArrivalDate());
		Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDepartingDate());
		
		if(!hotelResRepo.checkIfRoomIsAvailableInPeriod(checkInDate, checkOutDate, room).isEmpty())
			return -1;
		
		HotelReservation res = new HotelReservation();
		res.setRoom(room);
		res.setRating(0);
		res.setDiscount(dto.getDiscount());
		res.setFastReservation(true);
		res.setArrivalDate(checkInDate);
		res.setDepartingDate(checkOutDate);
		res.setCanceled(false);
		res.setStatus(ReservationStatus.PENDING);
		hotelResRepo.save(res);
		return room.getHotel().getId().intValue();
	}
	
}
