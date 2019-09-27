package edu.ftn.isa.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.AddHotelServiceDTO;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.HotelServicesRepository;
import edu.ftn.isa.repositories.RoomRepository;

@Service
public class HotelServiceService {

	@Autowired
	private HotelServicesRepository hotelServicesRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RoomRepository roomRepo;

	@Transactional
	public List<HotelServiceModel> findByHotel(User user) {
		Hotel hotel = hotelRepo.findByAdmin(user);
		List<HotelServiceModel> hotelServices = hotelServicesRepo.findByHotel(hotel);
		return hotelServices;
	}

	@Transactional
	public HotelServiceModel saveService(User user, AddHotelServiceDTO serviceDto) {
		Hotel hotel = hotelRepo.findByAdmin(user);
		HotelServiceModel hotelService = new HotelServiceModel();
		hotelService.setHotel(hotel);
		hotelService.setName(serviceDto.getName());
		hotelService.setRate((double) serviceDto.getRate());
		hotelService.setCharge(serviceDto.getCharge());
		hotelServicesRepo.save(hotelService);
		return hotelService;
	}
	
	public HotelServiceModel saveService(Long id, AddHotelServiceDTO serviceDto) {
		Optional<Hotel> temp = hotelRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		HotelServiceModel hotelService = new HotelServiceModel();
		hotelService.setHotel(temp.get());
		hotelService.setName(serviceDto.getName());
		hotelService.setRate((double) serviceDto.getRate());
		hotelService.setCharge(serviceDto.getCharge());
		hotelServicesRepo.save(hotelService);
		return hotelService;
	}
	@Transactional
	public List<HotelServiceModel> removeService(Long id, User user) {
		Hotel hotel = hotelRepo.findByAdmin(user);
		hotelServicesRepo.deleteById(id);
		List<HotelServiceModel> services = hotelServicesRepo.findByHotel(hotel);
		return services;
	}
	public List<HotelServiceModel> removeService(Long serviceId, Long hotelId) {
		
		Optional<Hotel> temp = hotelRepo.findById(hotelId);
		if(!temp.isPresent()) {
			return null;
		}
		hotelServicesRepo.deleteById(serviceId);
		List<HotelServiceModel> services = hotelServicesRepo.findByHotel(temp.get());
		return services;
		
	}
	public List<HotelServiceModel> loadHotelServicesByRoomId(Long id) {
		Optional<Room> optRoom = roomRepo.findById(id);
		if(!optRoom.isPresent())
			return null;
		Room room = optRoom.get();
		List<HotelServiceModel> services = hotelServicesRepo.findByHotel(room.getHotel());
		return services;
	}

	public List<HotelServiceModel> findByHotel(Long id) {
		Optional<Hotel> temp = hotelRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		Hotel hotel = temp.get();
		List<HotelServiceModel> hotelServices = hotelServicesRepo.findByHotel(hotel);
		return hotelServices;
	}

	

	
	
	
	
}
