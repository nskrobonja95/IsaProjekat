package edu.ftn.isa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ftn.isa.dto.FlightRatingDTO;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightRate;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRateRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;

@Service
public class ReservationService {

	@Autowired
	FlightReservationRepository flightResRepo;
	
	@Autowired
	FlightSeatRepository flightSeatRepo;
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	FlightRateRepository flightRateRepo;
	
	@Autowired
	AvioRepository avioRepo;
	
	@Autowired
	HotelReservationRepository hotelResRepo;
	
	@Transactional
	public boolean cancelReservation(Long resid) {
		Optional<FlightReservation> optRes = flightResRepo.findById(resid);
		if(!optRes.isPresent())
			return false;
		FlightReservation res = optRes.get();
		res.setStatus(ReservationStatus.CANCELED);
		for(int i=0; i<res.getFlightReservationSeats().size(); ++i) {
			res.getFlightReservationSeats().get(i).setAvailable(true);
		}
		flightResRepo.save(res);
		return true;
	}
	
	@Transactional
	public boolean rateFlight(FlightRatingDTO data, User user) {
		Optional<FlightReservation> optRes = flightResRepo.findById(data.getFlightReservationId());
		Optional<Flight> optFlight = flightRepo.findById(data.getFlightId());
		if(!optRes.isPresent() || !optFlight.isPresent())
			return false;
		
		FlightReservation res = optRes.get();
		Flight flight = optFlight.get();
		Optional<FlightRate> optRate = flightRateRepo.findByFlightAndFlightReservation(flight, res);
		FlightRate rate = new FlightRate();
		if(optRate.isPresent()) {
			rate = optRate.get();
		}
		rate.setFlight(flight);
		rate.setFlightReservation(res);
		rate.setUser(user);
		rate.setRate(data.getRate());
		flightRateRepo.save(rate);
		float avgRate = flightRateRepo.getAverageRateForAvioCompany(flight.getAvioCompany());
		flight.getAvioCompany().setAverageRate(avgRate);
		avioRepo.save(flight.getAvioCompany());
		return true;
	}
	
	@Transactional
	public boolean rateAcc(Long resId, Integer rate) {
		Optional<HotelReservation> optRes = hotelResRepo.findById(resId);
		if(!optRes.isPresent())
			return false;
		HotelReservation res = optRes.get();
		res.setRating(rate);
		hotelResRepo.save(res);
		return true;
	}
	
	@Transactional
	public boolean cancelAccReservation(Long resid) {
		Optional<HotelReservation> optRes = hotelResRepo.findById(resid);
		if(!optRes.isPresent())
			return false;
		HotelReservation res = optRes.get();
		res.setStatus(ReservationStatus.CANCELED);
		res.setCanceled(true);
		hotelResRepo.save(res);
		return true;
	}
	
	@Transactional
	public List<HotelReservation> retrieveUserHotelReservations(User user) {
		List<HotelReservation> retVal = hotelResRepo.findByUser(user);
		return retVal;
	}
	
}
