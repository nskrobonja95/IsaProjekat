package edu.ftn.isa.dto;

import edu.ftn.isa.model.Friends;

public class FriendsDTO {
	String friendsUsername;
	public String getFriendsUsername() {
		return friendsUsername;
	}
	public void setFriendsUsername(String friendsUsername) {
		this.friendsUsername = friendsUsername;
	}
	long friendsKey;
	boolean accepted;
	String sent;
	public String getSent() {
		return sent;
	}
	public void setSent(String sent) {
		this.sent = sent;
	}
	public long getFriendsKey() {
		return friendsKey;
	}
	public void setFriendsKey(long friendsKey) {
		this.friendsKey = friendsKey;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public FriendsDTO(String friendsUsername, long friendsKey, boolean accepted, String sent) {
		super();
		this.friendsUsername = friendsUsername;
		this.friendsKey = friendsKey;
		this.accepted = accepted;
		this.sent = sent;
	}
	public FriendsDTO(){
		
	}
	public static FriendsDTO parseFriendsToDTO(Friends friends){
		FriendsDTO friendsDTO = new FriendsDTO();
		friendsDTO.friendsKey=friends.getFriends().getId();
		friendsDTO.setAccepted(friends.getFriendshipDate());
		return friendsDTO;
	}
	
	
	
}