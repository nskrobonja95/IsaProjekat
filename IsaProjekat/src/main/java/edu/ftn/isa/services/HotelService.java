package edu.ftn.isa.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.AddHotelDTO;
import edu.ftn.isa.dto.BasicHotelInfoDTO;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.RentACarRepository;
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
	public Hotel updateBasicHotelInfo(User user, BasicHotelInfoDTO hotelDto) {
		Hotel retVal = hotelRepo.findByAdmin(user);
		retVal.setName(hotelDto.getName());
		retVal.setAddress(hotelDto.getAddress());
		retVal.setPromo(hotelDto.getPromo());
		hotelRepo.save(retVal);
		return retVal;
	}
	
}
