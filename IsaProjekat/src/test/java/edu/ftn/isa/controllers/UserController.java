package edu.ftn.isa.controllers;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import edu.ftn.isa.JsonMapper;
import edu.ftn.isa.dto.AvioStatisticsDTO;
import edu.ftn.isa.dto.FlightForStatsDTO;
import edu.ftn.isa.dto.RoundTripFlights;
import edu.ftn.isa.dto.SeatDTO;
import edu.ftn.isa.dto.SeatRowDTO;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.model.Flight;
import edu.ftn.isa.payload.LoginPayload;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.services.StatsService;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class UserController {

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
	public void testGetHotelById() throws JsonProcessingException, Exception {
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.get("/app/hotels/1")
							.contentType(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetAllDestinations() throws JsonProcessingException, Exception {
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.get("/app/getAllDestinations")
							.contentType(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testLoadHotelServices() throws JsonProcessingException, Exception {
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.get("/app/loadHotelServices/1")
							.contentType(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetSeats() throws JsonProcessingException, Exception {
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.get("/app/getSeats/1")
							.contentType(MediaType.APPLICATION_JSON)).andReturn();
		List<SeatRowDTO> retVal = mapper.getMapper().readValue(result.getResponse().getContentAsString(), mapper.getMapper().getTypeFactory().constructCollectionType(List.class, SeatRowDTO.class));
		assertEquals(retVal.size(), flightRepo.findById(1L).get().getNumOfRows());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testSearchFriends() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/user/searchFriends/nev")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8")).andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[*].name").value("Neven"));
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetFlight() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/user/getFlight/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.id").value(1));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCancelFlight() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/user/cancelFlight/1"))
		.andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testRateAcc() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/user/rateAccomodation/1/3"))
		.andExpect(status().isOk());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testCancelAcc() throws JsonProcessingException, Exception {
		mockMvc.perform(put("/user/cancelAccomodation/1"))
		.andExpect(status().isOk());
	}
	
	
	
}
