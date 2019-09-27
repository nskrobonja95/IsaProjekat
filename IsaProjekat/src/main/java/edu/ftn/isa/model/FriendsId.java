package edu.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendsId implements java.io.Serializable {
	private long personId;
    private long friendId;
    
    public FriendsId() {
    }
 
    public FriendsId(long personId, long friendId) {
        this.personId = personId;
        this.friendId = friendId;
    }
    
    @Column(name = "person_id", nullable = false)
    public long getPersonId() {
        return this.personId;
    }
 
    public void setPersonId(long personId) {
        this.personId = personId;
    }
 
    @Column(name = "friend_id", nullable = false)
    public long getFriendId() {
        return this.friendId;
    }
 
    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }
 
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof FriendsId))
            return false;
        FriendsId castOther = (FriendsId) other;
 
        return (this.getPersonId() == castOther.getPersonId())
                && (this.getFriendId() == castOther.getFriendId());
    }
 
    public int hashCode() {
        long result = 17;
 
        result = 37 * result + this.getPersonId();
        result = 37 * result + this.getFriendId();
        return (int)result;
    }
}