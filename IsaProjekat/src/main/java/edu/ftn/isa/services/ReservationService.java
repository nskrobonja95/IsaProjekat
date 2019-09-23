package edu.ftn.isa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ftn.isa.dto.FlightRatingDTO;
import edu.ftn.isa.dto.FlightReservationDTO;
import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.dto.SeatRowDTO;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightRate;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.HotelReservation;
import edu.ftn.isa.model.HotelServiceModel;
import edu.ftn.isa.model.ReservationStatus;
import edu.ftn.isa.model.Room;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRateRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.repositories.FlightReservationRepository;
import edu.ftn.isa.repositories.FlightSeatRepository;
import edu.ftn.isa.repositories.HotelReservationRepository;
import edu.ftn.isa.repositories.RoomRepository;

@Service
public class ReservationService {
	
	@PersistenceContext
	private EntityManager em;

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
	
	@Autowired
	private RoomRepository roomRepo;
	
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
	
	@Transactional
	public boolean reserveRoom(HotelReservationDTO reservationDto, User user) {
		
		Room room = em.find(Room.class, reservationDto.getRoomId(), LockModeType.PESSIMISTIC_WRITE);
		if(room == null) {
			return false;
		}
		HotelReservation reservation = new HotelReservation();
		reservation.setCanceled(false);
		reservation.setUser(user);
		reservation.setRoom(room);
		reservation.setArrivalDate(reservationDto.getArrivalDate());
		reservation.setDepartingDate(reservationDto.getDepartingDate());
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
		for(HotelServiceModel service : room.getHotelServices()) {
			if(reservationDto.getHotelServices().contains(service.getName()))
				services.add(service);
		}
		reservation.setServices(services);
		
		hotelResRepo.save(reservation);
		return true;
	}
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRES_NEW)
	@SuppressWarnings(value = { "unused" })
	public boolean reserveFlightSeat(FlightReservationDTO flightDto, User user) throws ParseException {
		//Flight flight = em.find(Flight.class, flightDto.getSeats().get(0).getFlight().getId(), LockModeType.PESSIMISTIC_FORCE_INCREMENT );
		FlightReservation reservation = new FlightReservation();
		reservation.setUser(user);
		reservation.setName(flightDto.getName());
		reservation.setLastname(flightDto.getLastname());
		reservation.setPassportNumber(flightDto.getPassportNumber());
		reservation.setRate(0);
		reservation.setStatus(ReservationStatus.APPROVED);
		List<FlightSeat> seats = new ArrayList<FlightSeat>();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date todayDate = new Date();
		Date reserveDate = formatter.parse(formatter.format(todayDate));
		reservation.setReserveDate(reserveDate);
		for(int i=0; i<flightDto.getSeats().size(); ++i) {
//			Optional<FlightSeat> optionalSeat = flightSeatRepo.findById(flightDto.getSeats().get(i).getId());
//			FlightSeat seat = optionalSeat.get();
			FlightSeat seat = em.find(FlightSeat.class, flightDto.getSeats().get(i).getId(), LockModeType.OPTIMISTIC);
			if(!seat.isAvailable())
				return false;
			if(seat == null)
				return false;
			seat.setAvailable(false);
//			if(seat.getVersion() != flightDto.getSeats().get(i).getVersion())
//				return false;
			seats.add(seat);
		}
		reservation.setFlightReservationSeats(seats);
		flightResRepo.save(reservation);
	
			
		return true;
	}
	
	@Transactional
	public List<SeatRowDTO> loadSeats(Long flightId) {
		Optional<Flight> optF = flightRepo.findById(flightId);
		if(!optF.isPresent()) {
			return null;
		}
		Flight f = optF.get();
		List<FlightSeat> seats = flightSeatRepo.findByFlight(f);
		int numOfCols = seats.size()/f.getNumOfRows();
		List<SeatRowDTO> seatRows = new ArrayList<SeatRowDTO>();
		SeatRowDTO seatRow = new SeatRowDTO();
		for(int j=0; j<seats.size(); ++j) {
			//FlightSeat seat = em.find(FlightSeat.class, seats.get(j).getId(), LockModeType.PESSIMISTIC_READ);
			SeatDTO seatDto = new SeatDTO();
			seatDto.setId(seats.get(j).getId());
			seatDto.setColNum(seats.get(j).getColNo());
			seatDto.setFastRes(seats.get(j).isFastReservation());
			seatDto.setFlightClass(seats.get(j).getFlightClass().toString());
			seatDto.setRowNum(seats.get(j).getRowNo());
			seatDto.setSeatNumber(seats.get(j).getSeatNumber());
			seatDto.setAvailable(seats.get(j).isAvailable());
			seatDto.setReserved(false);
			seatDto.setFlight(f);
//			seatDto.setVersion(seats.get(j).getVersion());
			seatRow.getSeats().add(seatDto);
			if(seats.get(j).getSeatNumber()%numOfCols == 0) {
				seatRows.add(seatRow);
				seatRow = new SeatRowDTO();
			}
		}
		return seatRows;
	}
	
}
