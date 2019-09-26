package edu.ftn.isa.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.AddAirlineDTO;
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
	public AvioCompany updateAvio(BasicAvioInfoDTO avioDto, User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		avio.setName(avioDto.getName());
		avio.setAddress(avioDto.getAddress());
		avio.setPromo(avioDto.getPromo());
		AvioCompany avioToModify = avioRepo.save(avio);
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

	public AvioCompany addAvio(AddAirlineDTO airlineDto) {
		AvioCompany avio = new AvioCompany();
		User user = new User();
		user.setEmail(airlineDto.getEmail());
		user.setUsername(airlineDto.getUsername());
		user.setEnabled(true);
		user.setRole(Role.AvioAdmin);
		user.setPassword(passwordEncoder.encode(airlineDto.getPassword()));
		user.setPasswordChanged(false);
		userRepo.save(user);
		avio.setAdmin(user);
		avio.setName(airlineDto.getName());
		avio.setPromo(airlineDto.getPromo());
		avio.setAddress(airlineDto.getAddress());
		avioRepo.save(avio);
		return avio;
	}
	
}
