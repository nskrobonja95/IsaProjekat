package edu.ftn.isa.controllers.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ftn.isa.dto.UserDTO;
import edu.ftn.isa.model.Friends;
import edu.ftn.isa.model.FriendsId;
import edu.ftn.isa.model.User;
import edu.ftn.isa.repositories.FriendsRepository;
import edu.ftn.isa.repositories.UserRepository;
import edu.ftn.isa.services.AuthService;
import edu.ftn.isa.util.CustomErrorType;

@RequestMapping("/friend")
@RestController
public class FriendsController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private FriendsRepository friendRepo;
	
	@RequestMapping(value = "/friendsList", method = RequestMethod.GET)
	public ResponseEntity<?> getFriendsList(HttpServletRequest request) {
		String username = authService.extractAuthUsernameFromRequest(request);
		User user = userRepo.findByUsername(username);
		List<UserDTO> usersDTO = new ArrayList<>();
		for(Friends friends : user.getPersons()){
			if(friends.getFriendshipDate()){
				usersDTO.add(UserDTO.parseUsertoDTO(friends.getFriends()));
			}
		}
		for(Friends friends : user.getFriends()){
			if(friends.getFriendshipDate()){
				usersDTO.add(UserDTO.parseUsertoDTO(friends.getPersons()));
			}
		}
		System.out.println("Printing frineds**********"+usersDTO);
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);

	}
	
	@RequestMapping(value="/addFriend/{friendsId}", method=RequestMethod.POST)
	public ResponseEntity<?> addFriend(@PathVariable("friendsId") long friendsId, HttpServletRequest request){
		
		String username = authService.extractAuthUsernameFromRequest(request);
		User user = userRepo.findByUsername(username);
		User friend = userRepo.findById(friendsId);
		if(friend == null){
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + friendsId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if(user == null){
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + user.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}
		if(user.getId() == friend.getId()){
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + user.getId() + ", same username."),
					HttpStatus.CONFLICT);
		}
		for(Friends friends : user.getPersons()){
			
			if(friends.getFriends().getId() == friend.getId()){
				return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + user.getId() + ", already have friends with username"
						+ friend.getUsername()+ "."),
						HttpStatus.CONFLICT);
			}
			
		}
		for(Friends friends : user.getFriends()){

			if(friends.getPersons().getId() == friend.getId()){
				return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + user.getId() + ", already have friends with username"
						+ friend.getUsername()+ "."),
						HttpStatus.CONFLICT);
			}
		}
		
		FriendsId key = new FriendsId(user.getId(), friend.getId());
		Friends friends = new Friends(key, friend,user, false);
		friendRepo.save(friends);
		return new ResponseEntity<UserDTO>(UserDTO.parseUsertoDTO(friend), HttpStatus.OK);
	}
	
	//--------------------Refuse frend's request----------------------------------------
		@RequestMapping(value="/refuse/{friendsId}", method=RequestMethod.DELETE)
		public ResponseEntity<?> refuseFriend(@PathVariable("friendsId") long friendsId, HttpServletRequest request){
			String username = authService.extractAuthUsernameFromRequest(request);
			User user = userRepo.findByUsername(username);
			User friend = userRepo.findById(friendsId);
			Friends friends = new Friends(new FriendsId(friend.getId(),user.getId()),user, friend);
			
			if (user == null || friends == null) {
				return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + friends.getId() + " not found."),
						HttpStatus.NOT_FOUND);
			}
			friendRepo.deleteById(friends.getId());
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
	
		//--------------------Accept request-------------------------------------------------
		@RequestMapping(value="/accept/{friendsId}", method=RequestMethod.PUT)
		public ResponseEntity<?> acceptFriend(@PathVariable("friendsId") long friendsId, HttpServletRequest request){
			String username = authService.extractAuthUsernameFromRequest(request);
			User user = userRepo.findByUsername(username);
			User friend = userRepo.findById(friendsId);
			Friends friends = new Friends(new FriendsId(friend.getId(),user.getId()),user, friend);
			friends.setFriendshipDate(true);
			if (user == null || friends == null) {
				
				return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + friends.getId() + " not found."),
						HttpStatus.NOT_FOUND);
			}
			friendRepo.save(friends);
			return new ResponseEntity<UserDTO>( HttpStatus.OK);
		}
}
