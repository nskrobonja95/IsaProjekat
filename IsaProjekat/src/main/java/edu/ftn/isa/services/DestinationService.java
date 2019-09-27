package edu.ftn.isa.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.HotelRepository;

@Service
public class DestinationService {

	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Transactional
	public void saveDestination(Destination dest) {
		destRepo.save(dest);
	}
	
	@Transactional
	public Destination addDestination(DestinationDTO destDto) {
		Destination dest = new Destination();
		dest.setName(destDto.getName());
		dest.setDeleted(false);
		destRepo.save(dest);
		return dest;
	}
	
	@Transactional
	public List<Destination> getDestinationsForAdmin(User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		List<AvioCompany> avios = new ArrayList<AvioCompany>();
		avios.add(avio);
		List<Destination> dests = destRepo.findByAvioCompanies(avios);
		return dests;
	}
	
	@Transactional
	public List<Destination> getRestOfDestinationsForAdmin(User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		List<AvioCompany> avios = new ArrayList<AvioCompany>();
		avios.add(avio);
		List<Destination> dests = destRepo.findRestOfDestinations(avio.getId());
		return dests;
	}
	@Transactional
	public List<Destination> getRestOfDestinationsForAdmin(Long id) {
		
		List<Destination> dests = destRepo.findRestOfDestinations(id);
		return dests;
	}

	public boolean deleteDest(Long id) {
		Destination d = destRepo.findById(id).get();
		List<Flight> flights = flightRepo.findByDestination(d);
		List<Hotel> hotels = hotelRepo.findByDestination(d);
		if(!flights.isEmpty() || !hotels.isEmpty())
			return false;
		destRepo.deleteById(id);
		return true;
	}

	public Destination editDestination(DestinationDTO destDto) {
		Destination dest = destRepo.findById(destDto.getId()).get();
		dest.setName(destDto.getName());
		dest.setDeleted(false);
		destRepo.save(dest);
		return dest;
	}
}
