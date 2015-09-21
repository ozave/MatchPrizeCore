package com.matchprize.batch.common.model;

import java.util.List;

public class Player {
	private String name;
	
	private String player_id;
	
	private String club;
	
	private String position;
	
	private List<PlayerValue> value;
	
	private List<PlayerPerformance> playerPerformance;
	
	private int totalPoints;
	
    private List<Stat> stat;
		
	public String getName(){
		return name;		
	}
	
	public String getClub(){
		return club;		
	}
	
	public String getPosition(){
		return position;		
	}
	
	public List<PlayerValue>  getValue(){
		return value;		
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
	
	public void setValue(List<PlayerValue>  value){
		this.value = value;		
	}
	
	public List<Stat> getStat(){
		return stat;		
	}
	
	public void setPlayer(List<Stat> stat){
		this.stat = stat;		
	}
	

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	public List<PlayerPerformance> getPlayerPerformance(){
		return playerPerformance;		
	}
	
	public void setPlayerPerformance(List<PlayerPerformance> playerPerformance){
		this.playerPerformance = playerPerformance;		
	}
	


}
