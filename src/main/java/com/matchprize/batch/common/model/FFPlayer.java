package com.matchprize.batch.common.model;

public class FFPlayer {
	private String name;
	
	private String player_id;
	
	private String club;
	
	private String position;
	
	private PlayerPerformance playerPerformance;
	
	private int matchDayPoints;
	
	private String matchDayDate;
	
 	public String getName(){
		return name;		
	}
	
	public String getClub(){
		return club;		
	}
	
	public String getPosition(){
		return position;		
	}
	
	public String getPlayer_id(){
		return player_id;		
	}

	public void setName(String name){
		 this.name = name;		
	}
	
	public void setPlayer_id(String player_id){
		this.player_id = player_id;		
	}
	
	public void setClub(String club){
		this.club = club;		
	}
	
	public void setPosition(String position){
		this.position = position;		
	}
	
	public int getMatchDayPoints() {
		return matchDayPoints;
	}

	public void setMatchDayPoints(int matchDayPoints) {
		this.matchDayPoints = matchDayPoints;
	}
	
	public PlayerPerformance getPlayerPerformance(){
		return playerPerformance;		
	}
	
	public void setPlayerPerformance(PlayerPerformance playerPerformance){
		this.playerPerformance = playerPerformance;		
	}
	
	public String getMatchDayDate(){
		return matchDayDate;		
	}
	
	public void setMatchDayDate(String matchDayDate){
		this.matchDayDate = matchDayDate;		
	}
	


}
