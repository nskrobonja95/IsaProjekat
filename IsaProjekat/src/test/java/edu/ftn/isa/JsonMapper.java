package edu.ftn.isa;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

	private ObjectMapper mapper;
	
	public ObjectMapper getMapper() {
		return mapper;
	}
	
	public JsonMapper() {
		mapper = new ObjectMapper();
	}
	
	public String mapToJson(Object o) throws JsonProcessingException {
		return mapper.writeValueAsString(o);
	}
	
	public <T> T mapFromJson(String json, Class<? extends T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, clazz);
	}
	
}
