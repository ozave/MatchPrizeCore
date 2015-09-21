package com.matchprize.batch.common.model;

public class PlayerValue {
	
	private String gameWeek;
	
	private Number value;
	
	public String getGameWeek(){
		return gameWeek;		
	}

	public void setGameWeek(String gameWeek){
		 this.gameWeek = gameWeek;		
	}
	
	public Number getValue(){
		return value;		
	}

	public void setValue(Number value){
		 this.value = value;		
	}

}
