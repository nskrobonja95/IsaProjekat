package edu.ftn.isa.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ftn.isa.JsonMapper;
import edu.ftn.isa.model.AvioCompany;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.FlightRepository;
import edu.ftn.isa.services.StatsService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestHotelAdminController {

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
	public void testGetHotel() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/hoteladmin/getHotel/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.id").value(1));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetRooms() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/hoteladmin/getRooms/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(8)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testLoadHotelServicesByRoomId() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/hoteladmin/loadHotelServicesByRoomId/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(3)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetServices() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/hoteladmin/getServices/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(3)));
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetRoom() throws JsonProcessingException, Exception {
		mockMvc.perform(get("/hoteladmin/getRoom/1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.id").value(1));
	}

	
}
