package edu.ftn.isa.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;

@Service
public class DestinationService {

	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private AvioRepository avioRepo;
	
	@Transactional
	public void saveDestination(Destination dest) {
		destRepo.save(dest);
	}
	
	@Transactional
	public Destination addDestination(DestinationDTO destDto) {
		Destination dest = new Destination();
		dest.setName(destDto.getName());
		dest.setDeleted(true);
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
	
}
