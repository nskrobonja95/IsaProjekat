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
import edu.ftn.isa.payload.LoginPayload;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
public class TestAuthentication {

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
	public void testLogin() throws JsonProcessingException, Exception {
		LoginPayload payload = new LoginPayload();
		payload.setUsername("nikolaUser");
		payload.setPassword("klasikaK1");
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/auth/login")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(payload))).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void testLogin1() throws JsonProcessingException, Exception {
		LoginPayload payload = new LoginPayload();
		payload.setUsername(null);
		payload.setPassword(null);
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/auth/login")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(payload))).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}
	
	@Test
	public void testLogin2() throws JsonProcessingException, Exception {
		LoginPayload payload = new LoginPayload();
		payload.setUsername("");
		payload.setPassword("");
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/auth/login")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(payload))).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}
	
	@Test
	public void testLogin3() throws JsonProcessingException, Exception {
		LoginPayload payload = new LoginPayload();
		payload.setUsername("nikola95");
		payload.setPassword("nikola");
		MvcResult result = 
				mockMvc.perform(
						MockMvcRequestBuilders.post("/auth/login")
							.contentType(MediaType.APPLICATION_JSON)
							.content(mapper.mapToJson(payload))).andReturn();
		assertEquals(400, result.getResponse().getStatus());
	}
	
}
