package edu.ftn.isa.controllers;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ftn.isa.JsonMapper;
import edu.ftn.isa.dto.MultiCitySearchDTO;
import edu.ftn.isa.dto.RoundTripFlights;
import edu.ftn.isa.dto.RoundTripSearchDTO;
import edu.ftn.isa.model.FlightClass;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestSearch {

private MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext context;
	
	private JsonMapper mapper;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mapper = new JsonMapper();
	}
	
	@Test
	public void testFlightsSearch() throws JsonProcessingException, Exception {
		RoundTripSearchDTO dto = new RoundTripSearchDTO();
		dto.setDepartDate("2019-04-30");
		dto.setFrom("Beograd, Srbija");
		dto.setFlightClass(FlightClass.Economic);
		dto.setNumOfPpl(2);
		dto.setReturnDate("2019-05-05");
		dto.setTo("Zurich, Switzerland");
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/app/roundTripSearch")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(dto)))
							.andReturn();
		RoundTripFlights retVal = mapper.mapFromJson(result.getResponse().getContentAsString(), RoundTripFlights.class);
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testFlightsOneWaySearch() throws JsonProcessingException, Exception {
		RoundTripSearchDTO dto = new RoundTripSearchDTO();
		dto.setDepartDate("2019-04-30");
		dto.setFrom("Beograd, Srbija");
		dto.setFlightClass(FlightClass.Economic);
		dto.setNumOfPpl(2);
		dto.setReturnDate("2019-05-05");
		dto.setTo("Zurich, Switzerland");
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/app/oneWaySearch")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(dto)))
							.andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testFlightsMultiCitySearch() throws JsonProcessingException, Exception {
		MultiCitySearchDTO dto = new MultiCitySearchDTO();
		dto.setDepartDate1("2019-04-30");
		dto.setDepartDate2("2019-05-05");
		dto.setFrom("Beograd, Srbija");
		dto.setMidDest("Zurich, Switzerland");
		dto.setFlightClass(FlightClass.Economic);
		dto.setNumOfPpl(2);
		dto.setTo("Beograd, Srbija");
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/app/multiCitySearch")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(dto)))
							.andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}

	
}
