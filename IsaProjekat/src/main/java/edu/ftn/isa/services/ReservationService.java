package edu.ftn.isa.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import edu.ftn.isa.dto.FlightReservationResponseDTO;
import edu.ftn.isa.dto.HotelReservationDTO;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.dto.SeatRowDTO;
import edu.ftn.isa.dto.UserHotelReservationDTO;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.model.FlightRate;
import edu.ftn.isa.model.FlightReservation;
import edu.ftn.isa.model.FlightSeat;
import edu.ftn.isa.model.Hotel;
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
import edu.ftn.isa.repositories.HotelRepository;
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
	
	@Autowired
	private HotelRepository hotelRepo;
	
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
		HotelReservation hr = res.getHotelReservation();
		if(hr != null) {
			List<FlightReservation> reservations = flightResRepo.findByHotelReservationAndStatus(res.getHotelReservation(), ReservationStatus.APPROVED);
			if(reservations == null || reservations.isEmpty()) {
				hr.setCanceled(true);
				hr.setStatus(ReservationStatus.CANCELED);
				hotelResRepo.save(hr);
			}
		}
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
		float avgRate = hotelRepo.getAverageRateForHotel(res.getRoom().getHotel());
		res.getRoom().getHotel().setAverageRate(avgRate);
		hotelRepo.save(res.getRoom().getHotel());
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
	public int reserveRoom(HotelReservationDTO reservationDto, User user) {
		
		Room room = em.find(Room.class, reservationDto.getRoomId(), LockModeType.PESSIMISTIC_WRITE);
		if(room == null) {
			return 0;
		}
		List<HotelReservation> reservations = hotelResRepo.checkIfHotelRoomReserved(room);
		if(!reservations.isEmpty())
			return 1;
		HotelReservation reservation = new HotelReservation();
		reservation.setCanceled(false);
		reservation.setUser(user);
		reservation.setRoom(room);
		reservation.setStatus(ReservationStatus.APPROVED);
		reservation.setArrivalDate(reservationDto.getArrivalDate());
		reservation.setDepartingDate(reservationDto.getDepartingDate());
		reservation.setRating(0);
		List<HotelServiceModel> services = new ArrayList<HotelServiceModel>();
		for(HotelServiceModel service : room.getHotelServices()) {
			if(reservationDto.getHotelServices().contains(service.getName()))
				services.add(service);
		}
		reservation.setServices(services);
		
		hotelResRepo.save(reservation);
		if(reservationDto.getFlightReservationIds() != null) {	
			for(int i=0; i<reservationDto.getFlightReservationIds().size(); ++i) {
				Optional<FlightReservation> optFlightRes = flightResRepo.findById(reservationDto.getFlightReservationIds().get(i).longValue());
				if(!optFlightRes.isPresent()) {
					return 0;
				}
				FlightReservation flightRes = optFlightRes.get();
				flightRes.setHotelReservation(reservation);
				flightResRepo.save(flightRes);
			}
		}
		return 2;
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
	
	@Transactional(readOnly=false, propagation = Propagation.REQUIRES_NEW)
	@SuppressWarnings(value = { "unused" })
	public FlightReservationResponseDTO reserveFlightSeat(List<FlightReservationDTO> flightDto, User user) throws ParseException {
		//Flight flight = em.find(Flight.class, flightDto.getSeats().get(0).getFlight().getId(), LockModeType.PESSIMISTIC_FORCE_INCREMENT );
		List<Integer> succussfulRetVal = new ArrayList<Integer>();
		FlightReservationResponseDTO dto = new FlightReservationResponseDTO();
		List<FlightReservation> reservationsToSave = new ArrayList<FlightReservation>();
		for(int j=0; j<flightDto.size(); ++j) {
			FlightReservation reservation = new FlightReservation();
			reservation.setUser(user);
			reservation.setName(flightDto.get(j).getName());
			reservation.setLastname(flightDto.get(j).getLastname());
			reservation.setPassportNumber(flightDto.get(j).getPassportNumber());
			reservation.setRate(0);
			reservation.setBaggageChecked(flightDto.get(j).getBaggageChecked());
			reservation.setStatus(ReservationStatus.APPROVED);
			List<FlightSeat> seats = new ArrayList<FlightSeat>();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date todayDate = new Date();
			Date reserveDate = formatter.parse(formatter.format(todayDate));
			reservation.setReserveDate(reserveDate);
			Double price = 0D;
			for(int i=0; i<flightDto.get(j).getSeats().size(); ++i) {
	//			Optional<FlightSeat> optionalSeat = flightSeatRepo.findById(flightDto.getSeats().get(i).getId());
	//			FlightSeat seat = optionalSeat.get();
				FlightSeat seat = em.find(FlightSeat.class, flightDto.get(j).getSeats().get(i).getId(), LockModeType.PESSIMISTIC_WRITE);
				if(!seat.isAvailable()) {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(seat.getSeatNumber());
					dto.setIds(temp);
					dto.setSuccesfullyReserved(false);
					return dto;
				}
				if(seat == null) {
					List<Integer> temp = new ArrayList<Integer>();
					dto.setIds(temp);
					return dto;
				}
				seat.setAvailable(false);
	//			if(seat.getVersion() != flightDto.getSeats().get(i).getVersion())
	//				return false;
				if(seat.getFlightClass().equals(FlightClass.Business)) {
					if(flightDto.get(j).getBaggageChecked()) {
						price = seat.getFlight().getBussinessClassPrice() + seat.getFlight().getBaggageOver20Price();
					}else {
						price = seat.getFlight().getBussinessClassPrice();
					}
				} else {
					if(flightDto.get(j).getBaggageChecked()) {
						price = seat.getFlight().getEconomicClassPrice() + seat.getFlight().getBaggageOver20Price();
					}else {
						price = seat.getFlight().getEconomicClassPrice();
					}
				}
				seats.add(seat);
			}
			reservation.setFlightReservationSeats(seats);
//<<<<<<< HEAD
			reservationsToSave.add(reservation);
//=======
//			reservation.setPrice(price);
//			FlightReservation res = flightResRepo.save(reservation);
//			dto.getIds().add(res.getId().intValue());
//>>>>>>> e48bfdad929e63e4ee1f3a8de00f29891978b2c4
		}
		List<FlightReservation> res = flightResRepo.saveAll(reservationsToSave);
		for(int i=0; i<res.size(); ++i)	
			dto.getIds().add(res.get(i).getId().intValue());
		dto.setSuccesfullyReserved(true);
		return dto;
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

	public List<UserHotelReservationDTO> retrieveFastReservationsForHotel(Long id) {
		Optional<Hotel> optHotel = hotelRepo.findById(id);
		if(!optHotel.isPresent())
			return null;
		Hotel hotel = optHotel.get();
		List<HotelReservation> reservations = hotelResRepo.findByHotelAndStatus(hotel, ReservationStatus.PENDING);
		List<UserHotelReservationDTO> retVal = new ArrayList<UserHotelReservationDTO>();
		for(HotelReservation reservation : reservations) {
			
			retVal.add(new UserHotelReservationDTO(reservation.getId(),
					reservation.getRoom(),
					reservation.getArrivalDate(),
					reservation.getDepartingDate(),
					reservation.getStatus().toString(),
					reservation.getRating(),
					reservation.getServices()
					));
		}
		return retVal;
	}

	public List<UserHotelReservationDTO> fastHotelReserve(Long id, User user) {
		HotelReservation res = em.find(HotelReservation.class, id, LockModeType.OPTIMISTIC);
		if(res.getStatus() != ReservationStatus.PENDING) {
			return null;
		}
		res.setUser(user);
		res.setStatus(ReservationStatus.APPROVED);
		hotelResRepo.save(res);
		List<HotelReservation> reservations = hotelResRepo.findByHotelAndStatus(res.getRoom().getHotel(), ReservationStatus.PENDING);
		List<UserHotelReservationDTO> retVal = new ArrayList<UserHotelReservationDTO>();
		for(HotelReservation reservation : reservations) {
			
			retVal.add(new UserHotelReservationDTO(reservation.getId(),
					reservation.getRoom(),
					reservation.getArrivalDate(),
					reservation.getDepartingDate(),
					reservation.getStatus().toString(),
					reservation.getRating(),
					reservation.getServices()
					));
		}
		return retVal;
	}
	
}
