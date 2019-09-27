package edu.ftn.isa.dto;

import java.util.ArrayList;
import java.util.List;

import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;
import lombok.Data;

public @Data class UserDTO {
	
	private Long id;

	private String name;
	
	private String lastname;

	private String username;
	
	private String city;
	
	private List<FriendsDTO> friends;

	private String email;

	private String password;
	
	private String phoneNumber;
	
	private boolean passwordChanged;

	private Role role;
	
	public static UserDTO parseUsertoDTO(User user){
		UserDTO userDTO = new UserDTO();
		userDTO.id = user.getId();
		userDTO.name = user.getName();
		userDTO.lastname = user.getLastname();
		userDTO.email = user.getEmail();
		userDTO.password = user.getPassword();
		userDTO.city = user.getCity();
		userDTO.phoneNumber = user.getPhoneNumber();
		userDTO.role = user.getRole();
		userDTO.username = user.getUsername();
		userDTO.passwordChanged = user.isPasswordChanged();
		//userDTO.phoneNumber = user.getPhoneNumber();
		return userDTO;
	}
	
	public static List<UserDTO> parsUserDTOList(List<User> users){
		List<UserDTO> usersDTO = new ArrayList<>();
		for(User user : users){
			usersDTO.add(parseUsertoDTO(user));
		}
		return usersDTO;
		
	}
}
