
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
import edu.ftn.isa.dto.GrossIntervalDTO;
import edu.ftn.isa.dto.HotelStatisticsDTO;
import edu.ftn.isa.dto.RoomForStatsDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.Hotel;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.HotelRepository;
import edu.ftn.isa.repositories.RoomRepository;

@Service
public class StatsService {

	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private FlightReservationRepository flightResRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private RoomRepository roomRepo;
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
		retVal.setDailySoldTickets(avioRepo.getNumOfDailySoldTickets(todayDate, avio));
		retVal.setWeeklySoldTickets(avioRepo.getNumOfSoldTicketsInInterval(weekEarlierDate, todayDate, avio));
		retVal.setMonthlySoldTickets(avioRepo.getNumOfSoldTicketsInInterval(monthEarlierDate, todayDate, avio));
		List<FlightForStatsDTO> flights = new ArrayList<FlightForStatsDTO>();
		List<Flight> flightList = flightRepo.findByAvioCompany(avio);
		for(int i=0; i<flightList.size(); ++i) {
			FlightForStatsDTO flight = new FlightForStatsDTO();
			if(flightRepo.getAverageRatingForFlight(flightList.get(i)) == null){
				flight.setAvgRate((float) 0);
			}else {
				flight.setAvgRate(flightRepo.getAverageRatingForFlight(flightList.get(i)));
			}
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

	public int getGrossForInterval(Long avioId, GrossIntervalDTO grossObj) throws ParseException {
		Optional<AvioCompany> temp = avioRepo.findById(avioId);
		if(!temp.isPresent()) {
			return 1;
		}
		AvioCompany avio = temp.get();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date fromGrossIntervalDate = formatter.parse(grossObj.getFromGrossInterval());
		Date toGrossIntervalDate = formatter.parse(grossObj.getToGrossInterval());
		Double grossResult = flightResRepo.getGrossInPeriodForAvio(avio, fromGrossIntervalDate, toGrossIntervalDate);
		if(grossResult == null) return 2;
		return grossResult.intValue();
	}
	@Transactional
	public HotelStatisticsDTO getHotelStats(Long hotelId) throws ParseException {
		Optional<Hotel> temp = hotelRepo.findById(hotelId);
		if(!temp.isPresent()) {
			return null;
		}
		Hotel hotel = temp.get();
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
		
		HotelStatisticsDTO retVal = new HotelStatisticsDTO();
		
		retVal.setAvgHotelRate(hotel.getAverageRate());
		retVal.setDailyAttendance(hotelRepo.getNumOfDailyVisitors(todayDate, hotel));
		retVal.setWeeklyAttendance(hotelRepo.getNumOfVisitorsInInterval(weekEarlierDate, todayDate, hotel));
		retVal.setMonthlyAttendance(hotelRepo.getNumOfVisitorsInInterval(monthEarlierDate, todayDate, hotel));
		
		List<RoomForStatsDTO> rooms = new ArrayList<RoomForStatsDTO>();
		List<Room> roomList = roomRepo.findByHotel(hotel);
		for(int i=0; i<roomList.size(); ++i) {
			RoomForStatsDTO room = new RoomForStatsDTO();
			if(roomRepo.getAverageRatingForRoom(roomList.get(i)) == null) {
				room.setAverageRate((float) 0);
			}else {
				room.setAverageRate(roomRepo.getAverageRatingForRoom(roomList.get(i)));
			}
			
			room.setRoomName(roomList.get(i).getDescription());
			rooms.add(room);
		}
		retVal.setRooms(rooms);
		return retVal;
	} 
}