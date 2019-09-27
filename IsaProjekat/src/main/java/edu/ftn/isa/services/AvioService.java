package edu.ftn.isa.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.AddAirlineDTO;
import edu.ftn.isa.dto.AddAvioResponseDTO;
import edu.ftn.isa.dto.BasicAvioInfoDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.UserRepository;

@Service
public class AvioService {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void saveAvio(AvioCompany avio) {
		avioRepo.save(avio);
	}
	
	@Transactional
	public AvioCompany getAvioByAdmin(User user) {
		AvioCompany avio = avioRepo.findByAdmin(user);
		return avio;
	}
	@Transactional
	public AvioCompany getAvioById(Long id) {
		Optional<AvioCompany> temp = avioRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		
		return temp.get();
	}
	@Transactional
	public List<AvioCompany> getAviosByAdmin(User user) {
		List<AvioCompany> avios = avioRepo.findByAviosByAdmin(user);
		return avios;
	}
	
	@Transactional
	public AvioCompany updateAvio(BasicAvioInfoDTO avioDto, User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		avio.setName(avioDto.getName());
		avio.setAddress(avioDto.getAddress());
		avio.setPromo(avioDto.getPromo());
		AvioCompany avioToModify = avioRepo.save(avio);
		return avioToModify;
	}
	@Transactional
	public AvioCompany updateAvio(BasicAvioInfoDTO avioDto, Long id) {
		Optional<AvioCompany> temp = avioRepo.findById(id);
		if(!temp.isPresent()) {
			return null;
		}
		AvioCompany avioToModify = temp.get();
		avioToModify.setName(avioDto.getName());
		avioToModify.setAddress(avioDto.getAddress());
		avioToModify.setPromo(avioDto.getPromo());
		avioRepo.save(avioToModify);
		return avioToModify;
	}
	@Transactional
	public AvioCompany addDestinationToAvio(String name, User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		Destination dest = destRepo.findByNameAndDeleted(name, false);
		avio.getDestinations().add(dest);
		avio = avioRepo.save(avio);
		return avio;
	}
	@Transactional
	public AvioCompany addDestinationToAvio(String name, Long avioId) {
		Optional<AvioCompany> temp = avioRepo.findById(avioId);
		if(!temp.isPresent()) {
			return null;
		}
		AvioCompany avio = temp.get();
		Destination dest = destRepo.findByNameAndDeleted(name, false);
		avio.getDestinations().add(dest);
		avioRepo.save(avio);
		return avio;
	}
	
	@Transactional
	public AvioCompany removeDestinationFromAvio(Long destId, User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		Destination theDest = destRepo.findById(destId).get();
		List<FlightReservation> reservations = flightResRepo.findAvioCompanyAndDestination(avio, theDest);
		if(reservations.isEmpty()) {
			for(Destination dest : avio.getDestinations()) {
				if(dest == theDest) {
					avio.getDestinations().remove(dest);
					break;
				}
			}
		}
		avioRepo.save(avio);
		return avio;
	}
	
	public AvioCompany removeDestinationFromAvio(Long destId, Long avioId) {
		Optional<AvioCompany> temp = avioRepo.findById(avioId);
		if(!temp.isPresent()) {
			return null;
		}
		Destination theDest = destRepo.findById(destId).get();
		AvioCompany avio = temp.get();
		List<FlightReservation> reservations = flightResRepo.findAvioCompanyAndDestination(avio, theDest);
		if(reservations.isEmpty()) {
			for(Destination dest : avio.getDestinations()) {
				if(dest == theDest) {
					avio.getDestinations().remove(dest);
					break;
				}
				
			}
			avioRepo.save(avio);
			return avio;
		}else {
			return null;
		}
		
	}
	
	public int addAvio(AddAirlineDTO airlineDto) {
		AvioCompany avio = new AvioCompany();
		int response;
		User user;
		if(airlineDto.getExistingAdminId() == null && airlineDto.getEmail() == null) {
			response = 1;
			return response;
		}
		if(airlineDto.getExistingAdminId() == null) {
			if(userRepo.findByUsername(airlineDto.getUsername()) != null) {
				response = 2;
				return response;
			}
			if(userRepo.findByEmail(airlineDto.getEmail()) != null) {
				response = 2;
				return response;
			}
			user = new User();
			user.setEmail(airlineDto.getEmail());
			user.setUsername(airlineDto.getUsername());
			user.setEnabled(true);
			user.setRole(Role.AvioAdmin);
			user.setPassword(passwordEncoder.encode(airlineDto.getPassword()));
			user.setPasswordChanged(false);
			userRepo.save(user);
		}else {
			Optional<User> temp = userRepo.findById(airlineDto.getExistingAdminId());
			if(!temp.isPresent()) {
				response = 3;
				return response;
			}
			user = temp.get();
		}
		
		avio.setAdmin(user);
		avio.setName(airlineDto.getName());
		avio.setPromo(airlineDto.getPromo());
		avio.setAddress(airlineDto.getAddress());
		avio.setAverageRate(0);
		avioRepo.save(avio);
		
		response = 0;
		return response;
	}

	

	


	

	
	
}
