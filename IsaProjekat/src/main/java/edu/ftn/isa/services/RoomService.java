package edu.ftn.isa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.CreateRoomDTO;
import edu.ftn.isa.dto.EditRoomDTO;
import edu.ftn.isa.dto.PriceOfMonthDTO;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.PriceOfRoom;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.PriceOfRoomRepository;
import edu.ftn.isa.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private HotelServicesRepository hotelServicesRepo;
	
	@Autowired
	private PriceOfRoomRepository priceOfRoomRepo;
	
	@Autowired
	private HotelReservationRepository hotelResRepo;
	
	@Transactional
	public List<Room> getRoomsForAdmin(User user) {
		Hotel hotel = hotelRepo.findByAdmin(user);
		List<Room> rooms = roomRepo.findByHotel(hotel);
		return rooms;
	}
	public List<Room> getRoomsForAdmin(Long id) {
		Optional<Hotel> temp = hotelRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		List<Room> rooms = roomRepo.findByHotel(temp.get());
		return rooms;
	}
	public Room saveRoom(CreateRoomDTO roomDto, User user) throws ParseException {
		Hotel hotel = hotelRepo.findByAdmin(user);
		Room room = new Room();
		room.setHotel(hotel);
		room.setBalcony(roomDto.isBalcony());
		room.setDescription(roomDto.getDescription());
		room.setNumOfBeds(roomDto.getNumOfBeds());
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
		for(int i=0; i<roomDto.getServices().size(); ++i) {
			HotelServiceModel hs = hotelServicesRepo.
								retrieveByNameAndHotel(roomDto.getServices().get(i), 
														hotel.getId());
			services.add(hs);
		}
		room.setHotelServices(services);
		roomRepo.save(room);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		for(int i=0; i<roomDto.getMonthPrices().size(); ++i) {
			PriceOfMonthDTO p = roomDto.getMonthPrices().get(i);
			Date from = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm").
						parse(yearInString + p.getFrom());
			Date to = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm").
						parse(yearInString + p.getTo());
			PriceOfRoom price = new PriceOfRoom();
			price.setActiveFrom(from);
			price.setActiveTo(to);
			price.setPrice(p.getPrice());
			price.setRoom(room);
			priceOfRoomRepo.save(price);
		}
		return room;
	}
	public Room saveRoom(CreateRoomDTO roomDto, Long id) throws ParseException {
		Optional<Hotel> temp = hotelRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		
		Room room = new Room();
		room.setHotel(temp.get());
		room.setBalcony(roomDto.isBalcony());
		room.setDescription(roomDto.getDescription());
		room.setNumOfBeds(roomDto.getNumOfBeds());
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
		for(int i=0; i<roomDto.getServices().size(); ++i) {
			HotelServiceModel hs = hotelServicesRepo.
								retrieveByNameAndHotel(roomDto.getServices().get(i), 
														temp.get().getId());
			services.add(hs);
		}
		room.setHotelServices(services);
		
		roomRepo.save(room);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		for(int i=0; i<roomDto.getMonthPrices().size(); ++i) {
			PriceOfMonthDTO p = roomDto.getMonthPrices().get(i);
			Date from = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm").
						parse(yearInString + p.getFrom());
			Date to = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm").
						parse(yearInString + p.getTo());
			PriceOfRoom price = new PriceOfRoom();
			price.setActiveFrom(from);
			price.setActiveTo(to);
			price.setPrice(p.getPrice());
			price.setRoom(room);
			priceOfRoomRepo.save(price);
		}
		return room;
		
	}
	@Transactional
	public Room getRoomById(Long id) {
		Optional<Room> optRoom = roomRepo.findById(id);
		if(!optRoom.isPresent())
			return null;
		return optRoom.get();
	}

	@Transactional
	public Room editRoom(EditRoomDTO roomDto) throws ParseException {
		Optional<Room> optRoom = roomRepo.findById(roomDto.getId());
		if(!optRoom.isPresent())
			return null;
		Room room = optRoom.get();
		List<HotelReservation> reservations = hotelResRepo.checkIfHotelRoomReserved(room);
		if(!reservations.isEmpty())
			return null;
		room.setBalcony(roomDto.isBalcony());
		room.setDescription(roomDto.getDescription());
		room.setNumOfBeds(roomDto.getNumOfBeds());
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
		for(int i=0; i<roomDto.getHotelServices().size(); ++i) {
			HotelServiceModel hs = hotelServicesRepo.
								retrieveByNameAndHotel(roomDto.getHotelServices().get(i).getName(), 
														room.getHotel().getId());
			services.add(hs);
		}
		room.setHotelServices(services);
		Calendar now = Calendar.getInstance();
		for(int i=0; i<roomDto.getPrices().size(); ++i) {
			PriceOfMonthDTO p = roomDto.getPrices().get(i);
			Date from = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm").
						parse(p.getFrom());
			Date to = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm").
						parse(p.getTo());
			Optional<PriceOfRoom> optPrice = priceOfRoomRepo.findById(p.getId());
			if(!optPrice.isPresent())
				return null;
			PriceOfRoom price = optPrice.get();
			price.setActiveFrom(from);
			price.setActiveTo(to);
			price.setPrice(p.getPrice());
			price.setRoom(room);
			priceOfRoomRepo.save(price);
		}
		System.out.println("Odavle");
		roomRepo.save(room);
		System.out.println("Dovle");
		return room;
	}
	
	@Transactional
	public boolean deleteRoom(Long id) {
		Optional<Room> optRoom = roomRepo.findById(id);
		if(!optRoom.isPresent())
			return false;
		Room room = optRoom.get();
		List<HotelReservation> reservations = hotelResRepo.checkIfHotelRoomReserved(room);
		if(!reservations.isEmpty())
			return false;
		roomRepo.delete(room);
		return true;
	}
	

	
	
}
