package com.matchprize.batch.common.model;

public class MatchDay  {
	
	private String _id;
	
	private int matchDay;
	
	private int gameWeek;
	
	private String month;
	
	private Double score;	
	
	public int getMatchDay(){
		return matchDay;		
	}
	
	public void setMatchDay(int matchDay){
		this.matchDay = matchDay;		
	}
	
	public String get_id(){
		return _id;		
	}
	
	public void set_id(String _id){
		this._id = _id;		
	}
	
	public int getGameWeek(){
		return gameWeek;		
	}
	
	public void setGameWeek(int gameWeek){
		this.gameWeek = gameWeek;		
	}
	
	public String getMonth(){
		return month;		
	}
	
	public void setMonth(String month){
		this.month = month;		
	}
	
	public Double getScore(){
		return score;		
	}
	
	public void setScore(Double score){
		this.score = score;		
	}

}
