package edu.ftn.isa.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ftn.isa.constants.UserConstants;
import edu.ftn.isa.model.Friends;
import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import edu.ftn.isa.payload.SignupPayload;
import edu.ftn.isa.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

	@Mock
	private UserRepository userRepo;
	
//	@Mock
//	private User user;
	
	@Mock
	private EmailService emailService;
	
	@Mock
	private PasswordEncoder passEncoder;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void testFindByUsername() {
	
		User user = new User();
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(passEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(true);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		when(userRepo.findByUsername(UserConstants.NEW_USERNAME)).thenReturn(user);
		User retUser = userService.findByUsername(UserConstants.NEW_USERNAME);
		assertEquals(UserConstants.NEW_EMAIL, retUser.getEmail());

		verify(userRepo, times(1)).findByUsername(UserConstants.NEW_USERNAME);
        verifyNoMoreInteractions(userRepo);
	}
	
	@Test
	public void testRegister() {
	
		SignupPayload sp = new SignupPayload();
		sp.setName(UserConstants.NEW_FIRST_NAME);
		sp.setLastname(UserConstants.NEW_LAST_NAME);
		sp.setPhoneNumber(UserConstants.NEW_PHONE);
		sp.setEmail(UserConstants.NEW_EMAIL);
		sp.setCity(UserConstants.NEW_CITY);
		sp.setPassword(UserConstants.NEW_PASSWORD);
		sp.setUsername(UserConstants.NEW_USERNAME);
		
		User user = new User();
		user.setUsername(UserConstants.NEW_USERNAME);
		user.setCity(UserConstants.NEW_CITY);
		user.setPhoneNumber(UserConstants.NEW_PHONE);
		user.setName(UserConstants.NEW_FIRST_NAME);
		user.setLastname(UserConstants.NEW_LAST_NAME);
		user.setEmail(UserConstants.NEW_EMAIL);
		user.setPassword(passEncoder.encode(UserConstants.NEW_PASSWORD));
		user.setEnabled(false);
		user.setPasswordChanged(false);
		user.setRole(Role.User);
		
		when(userRepo.findByUsername(UserConstants.NEW_USERNAME)).thenReturn(null);
		when(userRepo.findByEmail(UserConstants.NEW_EMAIL)).thenReturn(null);
		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userService.register(sp);
		assertEquals(true, flag);

		verify(userRepo, times(1)).findByUsername(UserConstants.NEW_USERNAME);
		verify(userRepo, times(1)).findByEmail(UserConstants.NEW_EMAIL);
//		user.setPassword(null);
//		user.setVerificationToken(UUID.randomUUID().toString());;
		verify(userRepo, times(1)).save(user);
        verifyNoMoreInteractions(userRepo);
	}
	
}
