package edu.ftn.isa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ftn.isa.model.Friends;
import edu.ftn.isa.model.FriendsId;



@Repository
public interface FriendsRepository extends JpaRepository<Friends, FriendsId>{

}
