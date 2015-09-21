package com.matchprize.batch.common.model;

import java.util.Date;
import java.util.List;

public class FFTeam {
	
	private String name;
	
	private String value;
	
	private int totalPoints;
	
	private int totalGoalsFor;
	
	private int totalGoalsAgainst;
	
	private int totalAssists;
	
	private int totalYellowCards;
	
	private int totalRedCards;
	
	private int totalGoalsDifference;
	
	private int leaguePosition;
	
	private List<String> league;
	
	private List<String> players;
	
	private List<String> subOff;
	
	private List<String> subOn;
	
	private List<FFPlayer> fFEleven;
	
	private List<FFPlayer> fFSubstitutes;
	
	private Date dateCreated;
	
	public String getName(){
		return name;		
	}
	
	public void setName(String name){
		this.name = name;		
	}
	
	public void setDateCreated(Date dateCreated){
		this.dateCreated = dateCreated;		
	}
	
	public String getValue(){
		return value;		
	}
	
	public void setValue(String value){
		this.value = value;		
	}
	
	public int getTotalPoints(){
		return totalPoints;		
	}
	
	public int getLeaguePosition(){
		return leaguePosition;		
	}
	
	public Date getDateCreated(){
		return dateCreated;		
	}
	
	public void setTotalPoints(int totalPoints){
		this.totalPoints = totalPoints;		
	}
	
	public void setLeaguePosition(int leaguePosition){
		this.leaguePosition = leaguePosition;		
	}
	
	public List<String> getLeague(){
		return league;		
	}
	
	public void setLeague(List<String> league){
		this.league = league;		
	}
	
	public List<String> getSubOn(){
		return subOn;		
	}
	
	public void setSubOn(List<String> subOn){
		this.subOn = subOn;		
	}
	
	public List<String> getSubOff(){
		return subOff;		
	}
	
	public void setSubOff(List<String> subOff){
		this.subOff = subOff;		
	}
	
	public List<String> getPlayers(){
		return players;		
	}
	
	public void setPlayers(List<String> players){
		this.players = players;		
	}
		
	public List<FFPlayer> getFFEleven(){
		return fFEleven;		
	}
	
	public void setFFEleven(List<FFPlayer> fFEleven){
		this.fFEleven = fFEleven;		
	}
	
	public List<FFPlayer> getFFSubstitutes(){
		return fFSubstitutes;		
	}
	
	public void setFFSubstitutes(List<FFPlayer> fFSubstitutes){
		this.fFSubstitutes = fFSubstitutes;		
	}
	public int getTotalGoalsFor(){
		return totalGoalsFor;		
	}
	
	public void setTotalGoalsFor(int totalGoalsFor){
		this.totalGoalsFor = totalGoalsFor;		
	}
	
	public int getTotalGoalsAgainst(){
		return totalGoalsAgainst;		
	}
	
	public void setTotalGoalsAgainst(int totalGoalsAgainst){
		this.totalGoalsAgainst = totalGoalsAgainst;		
	}
	
	public int getTotalAssists(){
		return totalAssists;		
	}
	
	public void setTotalAssists(int totalAssists){
		this.totalAssists = totalAssists;		
	}
	
	public int getTotalYellowCards(){
		return totalYellowCards;		
	}
	
	public void setTotalYellowCards(int totalYellowCards){
		this.totalYellowCards = totalYellowCards;		
	}
	
	public int getTotalRedCards(){
		return totalRedCards;		
	}
	
	public void setTotalRedCards(int totalRedCards){
		this.totalRedCards = totalRedCards;		
	}
	
	public int getTotalGoalsDifference(){
		return totalGoalsDifference;		
	}
	
	public void setTotalGoalsDifference(int GoalsDifference){
		this.totalGoalsDifference = GoalsDifference;		
	}

}
