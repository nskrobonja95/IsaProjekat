package edu.ftn.isa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ftn.isa.dto.FlightDTO;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;

@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private DestinationRepository destRepo;
	
	@Autowired
	private FlightSeatRepository flightSeatRepo;
	
	@Transactional
	public Flight saveFlight(Flight flight) {
		flightRepo.save(flight);
		return flight;
	}
	
	@Transactional
	public List<Flight> getFlightsOfAvio(User admin) {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		List<Flight> flights = flightRepo.findByAvioCompany(avio);
		return flights;
	}
	
	@Transactional
	public List<Flight> getFlightsOfAvio(Long avioId) {
		Optional<AvioCompany> temp = avioRepo.findById(avioId);
		if(!temp.isPresent()) {
			return null;
		}
		
		List<Flight> flights = flightRepo.findByAvioCompany(temp.get());
		return flights;
	}
	@Transactional
	public Flight createFlight(User admin, FlightDTO flightData) throws ParseException {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		Destination from = destRepo.findByNameAndDeleted(flightData.getFrom(), false);
		Destination to = destRepo.findByNameAndDeleted(flightData.getTo(), false);
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightData.getDepart());
		Date landing = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightData.getLand());
		Flight flight = new Flight();
		flight.setAvioCompany(avio);
		flight.setBaggageOver20Price(flightData.getPriceForBaggageOver14kg());
		flight.setEconomicClassPrice(flightData.getEconomicPrice());
		flight.setBussinessClassPrice(flightData.getBusinessPrice());
		flight.setFrom(from);
		flight.setToDest(to);
		flight.setTakeoff(takeoff);
		flight.setLanding(landing);
		flight.setConfigurationType(flightData.getConfigType());
		flight.setNumOfRows(flightData.getNumOfRows());
		flight.setDiscount(flightData.getDiscount());
		Flight savedFlight = flightRepo.save(flight);
		saveSeats(flight, flightData.getSeats());
		return flight;
	}
	public Flight createFlight(Long avioId, FlightDTO flightData) throws ParseException {
		Optional<AvioCompany> temp = avioRepo.findById(avioId);
		if(!temp.isPresent()) {
			return null;
		}
		AvioCompany avio = temp.get();
		Destination from = destRepo.findByNameAndDeleted(flightData.getFrom(), false);
		Destination to = destRepo.findByNameAndDeleted(flightData.getTo(), false);
		Date takeoff = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightData.getDepart());
		Date landing = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(flightData.getLand());
		Flight flight = new Flight();
		flight.setAvioCompany(avio);
		flight.setBaggageOver20Price(flightData.getPriceForBaggageOver14kg());
		flight.setEconomicClassPrice(flightData.getEconomicPrice());
		flight.setBussinessClassPrice(flightData.getBusinessPrice());
		flight.setFrom(from);
		flight.setToDest(to);
		flight.setTakeoff(takeoff);
		flight.setLanding(landing);
		flight.setConfigurationType(flightData.getConfigType());
		flight.setNumOfRows(flightData.getNumOfRows());
		flight.setDiscount(flightData.getDiscount());
		Flight savedFlight = flightRepo.save(flight);
		saveSeats(flight, flightData.getSeats());
		return flight;
	}
	private void saveSeats(Flight flight, List<SeatDTO> seats) {
		int seatNumber = 0;
		for(int i=0; i<seats.size(); ++i) {
			FlightSeat fs = new FlightSeat();
			fs.setAvailable(true);
			fs.setFastReservation(seats.get(i).isFastRes());
			fs.setFlight(flight);
			fs.setColNo(seats.get(i).getColNum());
			fs.setRowNo(seats.get(i).getRowNum());
			fs.setSeatNumber(++seatNumber);
			if(seats.get(i).getFlightClass().equals("business"))
				fs.setFlightClass(FlightClass.Business);
			else
				fs.setFlightClass(FlightClass.Economic);
			flightSeatRepo.save(fs);
		}
	}

	

	
	
}
