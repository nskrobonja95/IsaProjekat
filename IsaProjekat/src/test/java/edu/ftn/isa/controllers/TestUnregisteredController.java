package edu.ftn.isa.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ftn.isa.JsonMapper;
import edu.ftn.isa.dto.MultiCitySearchDTO;
import edu.ftn.isa.dto.RoundTripSearchDTO;
import edu.ftn.isa.dto.SearchAvailableRoomsForHotelDTO;
import edu.ftn.isa.dto.SearchHotelRequestDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.FlightClass;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.services.StatsService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestUnregisteredController {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	private StatsService statsService;
	
	@Autowired
	private AvioRepository avioRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	private JsonMapper mapper;
	
	private AvioCompany avio;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = new JsonMapper();
		avio = avioRepo.findById(1L).get();
	}

	@Transactional
	@Rollback(true)
	@Test
	public void testGetDestById() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/app/getAllDestinationsById/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testSearchRound() throws JsonProcessingException, Exception {
		RoundTripSearchDTO dto = new RoundTripSearchDTO();
		dto.setReturnDate("2019-09-28");
		dto.setDepartDate("2019-09-23");
		dto.setFlightClass(FlightClass.Economic);
		dto.setFrom("Zurich, Switzerland");
		dto.setTo("Beograd, Srbija");
		dto.setNumOfPpl(1);
		
		mockMvc.perform(post("/app/roundTripSearch")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.mapToJson(dto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.directFlights", hasSize(0)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testSearchOneWay() throws JsonProcessingException, Exception {
		RoundTripSearchDTO dto = new RoundTripSearchDTO();
		dto.setReturnDate("2019-09-28");
		dto.setDepartDate("2019-09-23");
		dto.setFlightClass(FlightClass.Economic);
		dto.setFrom("Zurich, Switzerland");
		dto.setTo("Beograd, Srbija");
		dto.setNumOfPpl(1);
		
		mockMvc.perform(post("/app/oneWaySearch")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.mapToJson(dto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testMultiSearch() throws JsonProcessingException, Exception {
		MultiCitySearchDTO dto = new MultiCitySearchDTO();
		dto.setDepartDate1("2019-09-28");
		dto.setMidDest("Budapest, Hungary");
		dto.setDepartDate2("2019-09-23");
		dto.setFlightClass(FlightClass.Economic);
		dto.setFrom("Zurich, Switzerland");
		dto.setTo("Beograd, Srbija");
		dto.setNumOfPpl(1);
		
		mockMvc.perform(post("/app/multiCitySearch")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.mapToJson(dto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.directFlights", hasSize(0)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testAvailableRooms() throws JsonProcessingException, Exception {
		SearchHotelRequestDTO dto = new SearchHotelRequestDTO();
		dto.setCheckOut("2019-09-28");
		dto.setDest("Budapest, Hungary");
		dto.setCheckIn("2019-09-23");
		
		mockMvc.perform(post("/app/searchAvailableRooms")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.mapToJson(dto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testSearcHotels() throws JsonProcessingException, Exception {
		SearchHotelRequestDTO dto = new SearchHotelRequestDTO();
		dto.setCheckOut("2019-09-28");
		dto.setDest("Budapest, Hungary");
		dto.setCheckIn("2019-09-23");
		
		mockMvc.perform(post("/app/searchHotels")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.mapToJson(dto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.hotels", hasSize(0)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testSearchRoomsForHotel() throws JsonProcessingException, Exception {
		SearchAvailableRoomsForHotelDTO dto = new SearchAvailableRoomsForHotelDTO();
		dto.setCheckOut("2019-09-28");
		dto.setHotel(1L);
		dto.setCheckIn("2019-09-23");
		
		mockMvc.perform(post("/app/searchAvailableRoomsForHotel")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.mapToJson(dto)))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(9)));
	}
	
}
