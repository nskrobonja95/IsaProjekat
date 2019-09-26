package edu.ftn.isa.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ftn.isa.constants.DestinationConstants;
import edu.ftn.isa.dto.DestinationDTO;
import edu.ftn.isa.model.Destination;
import edu.ftn.isa.repositories.AvioRepository;
import edu.ftn.isa.repositories.DestinationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDestinationService {

	@Mock
	private Destination dest;
	
	@Mock
	private DestinationRepository destRepo;
	
	@Mock
	private AvioRepository avioRepo;
	
	@InjectMocks
	private DestinationService destService;
	
	@Test
	public void testAddDestination() {
		
		List<Destination> dests = new ArrayList<Destination>();
		Destination toSave = new Destination();
		toSave.setName(DestinationConstants.NAME);
		toSave.setDeleted(false);
		
		DestinationDTO dto = new DestinationDTO();
		dto.setName(DestinationConstants.NAME);
		Destination dest = new Destination();
		dest.setName(DestinationConstants.NAME);
		dest.setDeleted(false);
//		dest.setId(1L);
		
		when(destRepo.save(toSave)).thenReturn(dest);
		
		Destination d = destService.addDestination(dto);
		
		assertEquals(dest, d);
		
		verify(destRepo, times(1)).save(toSave);
        verifyNoMoreInteractions(destRepo);
	}
	
}
