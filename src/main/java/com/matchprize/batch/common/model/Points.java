package com.matchprize.batch.common.model;

public class Points {
	
	private String matchDay;
	
	private int gameWeek;
	
	private int gameMonth;
	
	private int gamePoints;
	
	public String getMatchDay(){
		return matchDay;		
	}

	public void setMatchDay(String matchDay){
		 this.matchDay = matchDay;		
	}
	
	public int getGameWeek(){
		return gameWeek;		
	}

	public void setGameWeek(int gameWeek){
		 this.gameWeek = gameWeek;		
	}
	
	public int getgameMonth(){
		return gameMonth;		
	}

	public void setGameMonth(int gameMonth){
		 this.gameMonth = gameMonth;		
	}
	
	public int getGamePoints(){
		return gamePoints;		
	}

	public void setGamePoints(int gamePoints){
		 this.gamePoints = gamePoints;		
	}

}
