package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	User findByEmail(String email);
	
	List<User> findByRole(Role role);
	
	User findByUsernameOrEmail(String username, String email);
	
}
