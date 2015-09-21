package com.matchprize.batch.common.model;

import java.util.List;

public class League {
	
	private String name;
	
	private String type;
	
	private List<String> teams;
	
	
	public void setName(String name){
		this.name = name;		
	}
	
	public String getName(){
		return name;		
	}
	
	public void setType(String type){
		this.type = type;		
	}
	
	public String getType(){
		return type;		
	}
	
	public List<String> getTeams(){
		return teams;		
	}
	
	public void setTeams(List<String> teams){
		this.teams = teams;		
	}

}
