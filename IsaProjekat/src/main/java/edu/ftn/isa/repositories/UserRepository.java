package edu.ftn.isa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.ftn.isa.model.Role;
import edu.ftn.isa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	User findByEmail(String email);
	User findById(long id);
	List<User> findByRole(Role role);
	
	User findByUsernameOrEmail(String username, String email);
	
	User findByVerificationToken(String verificationToken);
	
	@Query("SELECT u FROM User u where u.name like %:searchValue% or u.lastname like %:searchValue%")
    public List<User> querySearch(@Param("searchValue") String searchValue);
}
