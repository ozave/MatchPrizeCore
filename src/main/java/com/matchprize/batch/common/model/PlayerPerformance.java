package com.matchprize.batch.common.model;

public class PlayerPerformance {

	private String playerId;

	private Boolean playedInMatch;

	private Boolean startInMatch;

	private int playedMinutes;

	private int goals;

	private int assists;

	private int secondAssists;

	private int goalsConceded;

	private int ownGoals;

	private int penaltyScored;

	private int penaltyMisses;

	private int penaltySaves;

	private int yellowCards;

	private int secondYellowCards;

	private int redCards;

	private int saves;

	private Points matchDayPoints;
	
	private String fixture_id;
	
	private String matchDate;


	/**
	 * @return the player
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the playedInMatch
	 */
	public Boolean getPlayedInMatch() {
		return playedInMatch;
	}

	/**
	 * @param playedInMatch
	 *            the playedInMatch to set
	 */
	public void setPlayedInMatch(Boolean playedInMatch) {
		this.playedInMatch = playedInMatch;
	}

	/**
	 * @return the startInMatch
	 */
	public Boolean getStartInMatch() {
		return startInMatch;
	}

	/**
	 * @param startInMatch
	 *            the startInMatch to set
	 */
	public void setStartInMatch(Boolean startInMatch) {
		this.startInMatch = startInMatch;
	}

	/**
	 * @return the playedMinutes
	 */
	public int getPlayedMinutes() {
		return playedMinutes;
	}

	/**
	 * @param playedMinutes
	 *            the playedMinutes to set
	 */
	public void setPlayedMinutes(int playedMinutes) {
		this.playedMinutes = playedMinutes;
	}

	/**
	 * @return the goals
	 */
	public int getGoals() {
		return goals;
	}

	/**
	 * @param goals
	 *            the goals to set
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}

	/**
	 * @return the assists
	 */
	public int getAssists() {
		return assists;
	}

	/**
	 * @param assists
	 *            the assists to set
	 */
	public void setAssists(int assists) {
		this.assists = assists;
	}

	/**
	 * @return the secondAssists
	 */
	public int getSecondAssists() {
		return secondAssists;
	}

	/**
	 * @param secondAssists
	 *            the secondAssists to set
	 */
	public void setSecondAssists(int secondAssists) {
		this.secondAssists = secondAssists;
	}

	/**
	 * @return the goalsConceded
	 */
	public int getGoalsConceded() {
		return goalsConceded;
	}

	/**
	 * @param goalsConceded
	 *            the goalsConceded to set
	 */
	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}

	/**
	 * @return the ownGoals
	 */
	public int getOwnGoals() {
		return ownGoals;
	}

	/**
	 * @param ownGoals
	 *            the ownGoals to set
	 */
	public void setOwnGoals(int ownGoals) {
		this.ownGoals = ownGoals;
	}

	/**
	 * @return the penaltyScored
	 */
	public int getPenaltyScored() {
		return penaltyScored;
	}

	/**
	 * @param penaltyScored
	 *            the penaltyScored to set
	 */
	public void setPenaltyScored(int penaltyScored) {
		this.penaltyScored = penaltyScored;
	}

	/**
	 * @return the penaltyMisses
	 */
	public int getPenaltyMisses() {
		return penaltyMisses;
	}

	/**
	 * @param penaltyMisses
	 *            the penaltyMisses to set
	 */
	public void setPenaltyMisses(int penaltyMisses) {
		this.penaltyMisses = penaltyMisses;
	}

	/**
	 * @return the penaltySaves
	 */
	public int getPenaltySaves() {
		return penaltySaves;
	}

	/**
	 * @param penaltySaves
	 *            the penaltySaves to set
	 */
	public void setPenaltySaves(int penaltySaves) {
		this.penaltySaves = penaltySaves;
	}

	/**
	 * @return the yellowCards
	 */
	public int getYellowCards() {
		return yellowCards;
	}

	/**
	 * @param yellowCards
	 *            the yellowCards to set
	 */
	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}

	/**
	 * @return the secondYellowCards
	 */
	public int getSecondYellowCards() {
		return secondYellowCards;
	}

	/**
	 * @param secondYellowCards
	 *            the secondYellowCards to set
	 */
	public void setSecondYellowCards(int secondYellowCards) {
		this.secondYellowCards = secondYellowCards;
	}

	/**
	 * @return the redCards
	 */
	public int getRedCards() {
		return redCards;
	}

	/**
	 * @param redCards
	 *            the redCards to set
	 */
	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}

	/**
	 * @return the saves
	 */
	public int getSaves() {
		return saves;
	}

	/**
	 * @param saves
	 *            the saves to set
	 */
	public void setSaves(int saves) {
		this.saves = saves;
	}
//
//	/**
//	 * @return the fixture
//	 */
//	public Fixture getFixture() {
//		return fixture;
//	}
//
//	/**
//	 * @param fixture
//	 *            the fixture to set
//	 */
//	public void setFixture(Fixture fixture) {
//		this.fixture = fixture;
//	}

	/**
	 * @return the matchDayPoints
	 */
	public Points getMatchDayPoints() {
		return matchDayPoints;
	}

	/**
	 * @param matchDayPoints
	 *            the matchDayPoints to set
	 */
	public void setMatchDayPoints(Points matchDayPoints) {
		this.matchDayPoints = matchDayPoints;
	}

	
	public String getFixture_id(){
		return fixture_id;		
	}
	
	public void setFixture_id(String fixture_id){
		this.fixture_id = fixture_id;		
	}
	
	public String getMatchDate(){
		return matchDate;		
	}
	
	public void setMatchDate(String matchDate){
		this.matchDate = matchDate;		
	}


}
