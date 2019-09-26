package edu.ftn.isa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ftn.isa.dto.AvioStatisticsDTO;
import edu.ftn.isa.dto.FlightForStatsDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;

@Service
public class StatsService {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Transactional
	public AvioStatisticsDTO getAvioStats(User admin) throws ParseException {
		AvioCompany avio = avioRepo.findByAdmin(admin);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		todayDate = formatter.parse(formatter.format(todayDate));
		Calendar c = Calendar.getInstance();
		c.setTime(todayDate);
		c.add(Calendar.DATE, -7);
		Date weekEarlierDate = c.getTime();
		weekEarlierDate = formatter.parse(formatter.format(weekEarlierDate));
		c.add(Calendar.DATE, -23);
		Date monthEarlierDate = c.getTime();
		monthEarlierDate = formatter.parse(formatter.format(monthEarlierDate));
		
		AvioStatisticsDTO retVal = new AvioStatisticsDTO();
		retVal.setAvgAvioRate(avio.getAverageRate());
		retVal.setDailySoldTickets(avioRepo.getNumOfDailySoldTickets(todayDate));
		retVal.setWeeklySoldTickets(avioRepo.getNumOfSoldTicketsInInterval(weekEarlierDate, todayDate));
		retVal.setMonthlySoldTickets(avioRepo.getNumOfSoldTicketsInInterval(monthEarlierDate, todayDate));
		List<FlightForStatsDTO> flights = new ArrayList<FlightForStatsDTO>();
		List<Flight> flightList = flightRepo.findByAvioCompany(avio);
		for(int i=0; i<flightList.size(); ++i) {
			FlightForStatsDTO flight = new FlightForStatsDTO();
			flight.setAvgRate(flightRepo.getAverageRatingForFlight(flightList.get(i)));
			flight.setFrom(flightList.get(i).getFrom().getName());
			flight.setTo(flightList.get(i).getToDest().getName());
			flight.setDepart(formatter.format((flightList.get(i).getTakeoff())));
			flight.setLand((formatter.format((flightList.get(i).getLanding()))));
			flights.add(flight);
		}
		retVal.setFlights(flights);
		return retVal;
	}

	public AvioStatisticsDTO getAvioStats(Long avioId) throws ParseException {
		Optional<AvioCompany> temp = avioRepo.findById(avioId);
		if(!temp.isPresent()) {
			return null;
		}
		AvioCompany avio = temp.get();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		todayDate = formatter.parse(formatter.format(todayDate));
		Calendar c = Calendar.getInstance();
		c.setTime(todayDate);
		c.add(Calendar.DATE, -7);
		Date weekEarlierDate = c.getTime();
		weekEarlierDate = formatter.parse(formatter.format(weekEarlierDate));
		c.add(Calendar.DATE, -23);
		Date monthEarlierDate = c.getTime();
		monthEarlierDate = formatter.parse(formatter.format(monthEarlierDate));
		
		AvioStatisticsDTO retVal = new AvioStatisticsDTO();
		retVal.setAvgAvioRate(avio.getAverageRate());
		retVal.setDailySoldTickets(avioRepo.getNumOfDailySoldTickets(todayDate));
		retVal.setWeeklySoldTickets(avioRepo.getNumOfSoldTicketsInInterval(weekEarlierDate, todayDate));
		retVal.setMonthlySoldTickets(avioRepo.getNumOfSoldTicketsInInterval(monthEarlierDate, todayDate));
		List<FlightForStatsDTO> flights = new ArrayList<FlightForStatsDTO>();
		List<Flight> flightList = flightRepo.findByAvioCompany(avio);
		for(int i=0; i<flightList.size(); ++i) {
			FlightForStatsDTO flight = new FlightForStatsDTO();
			flight.setAvgRate(flightRepo.getAverageRatingForFlight(flightList.get(i)));
			flight.setFrom(flightList.get(i).getFrom().getName());
			flight.setTo(flightList.get(i).getToDest().getName());
			flight.setDepart(formatter.format((flightList.get(i).getTakeoff())));
			flight.setLand((formatter.format((flightList.get(i).getLanding()))));
			flights.add(flight);
		}
		retVal.setFlights(flights);
		return retVal;

	} 
	
}
