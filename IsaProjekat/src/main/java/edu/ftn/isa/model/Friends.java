package edu.ftn.isa.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prijatelji1")
public class Friends implements java.io.Serializable{
	 private FriendsId id;
	 private User friends;
	 private User persons;
	 private boolean accepted;
	 
	 public Friends(FriendsId id, User friends,
	            User persons) {
	        this.id = id;
	        this.friends = friends;
	        this.persons = persons;
	    }
	 
	    public Friends(FriendsId id, User friends,
	            User persons, boolean accepted) {
	        this.id = id;
	        this.friends = friends;
	        this.persons = persons;
	        this.accepted = accepted;
	    }
	    
	    @EmbeddedId
	    @AttributeOverrides({
	            @AttributeOverride(name = "personId", column = @Column(name = "id_prijatelja1", nullable = false)),
	            @AttributeOverride(name = "friendId", column = @Column(name = "id_prijatelja2", nullable = false)) })
	    public FriendsId getId() {
	        return this.id;
	    }
	    
	    public void setId(FriendsId id) {
	        this.id = id;
	    }
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "id_prijatelja2", nullable = false, insertable = false, updatable = false)
	    public User getFriends() {
	        return this.friends;
	    }
	    
	    public void setFriends(User friends) {
	        this.friends = friends;
	    }
	    
	    @Override
		public String toString() {
			return "Friends [id=" + id + ", friends=" + friends + ", persons=" + persons + ", accepted=" + accepted
					+ "]";
		}

		public Friends() {
			// TODO Auto-generated constructor stub
		}
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "id_prijatelja1", nullable = false, insertable = false, updatable = false)
	    public User getPersons() {
	        return persons;
	    }
	 
	    public void setPersons(User persons) {
	        this.persons = persons;
	    }
	    
	    @Column(name = "prihvaceno")
	    public boolean getFriendshipDate() {
	        return this.accepted;
	    }
	    
	    public void setFriendshipDate(boolean accepted) {
	        this.accepted = accepted;
	    }
	

}
