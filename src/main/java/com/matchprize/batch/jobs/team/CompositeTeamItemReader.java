package com.matchprize.batch.jobs.team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.matchprize.batch.common.model.FFPlayer;
import com.matchprize.batch.common.model.FFTeam;
import com.matchprize.batch.common.model.Player;
import com.matchprize.batch.common.model.PlayerPerformance;


public class CompositeTeamItemReader implements ItemReader<FFTeam> {
	
	@Autowired
	private RedisTemplate<String, String> template;
	ItemReader<FFTeam> itemReader;	
	private MongoTemplate mongoTemplate2;
  	int totalScore;
  	int totalGoalsFor;
  	int totalGoalsAgainst;
  	int totalYellowCards;
  	int totalRedCards;
  	int totalAssists;
  	int totalGoalsDifference;

    public CompositeTeamItemReader(ItemReader<FFTeam> itemReader, MongoTemplate mongoTemplate) {
        this.itemReader = itemReader;
        this.mongoTemplate2  = mongoTemplate;
    }

    public FFTeam read() throws Exception {
    	MongoOperations mongoOperation = (MongoOperations)mongoTemplate2;
    	ZSetOperations<String, String> ops = this.template.opsForZSet();
    	FFTeam team = itemReader.read();
    	DateTime teamCreatedDate = new DateTime(team.getDateCreated());
    	String localDateCreated = teamCreatedDate.toLocalDate().toString();   	
       	System.out.println("TeamDate -" + localDateCreated);
       	
    	int playersScore = 0;
    	int previousTotalScore = team.getTotalPoints();
    	
    	int playersGoalsFor = 0;
    	int previousTotalGoalsFor = team.getTotalGoalsFor();
    	
    	int playersGoalsAgainst = 0;
    	int previousTotalGoalsAgainst = team.getTotalGoalsAgainst();
    	
    	int playersAssist = 0;
    	int previousTotalAssists = team.getTotalAssists();
    	
    	int playersYellowCards = 0;
    	int previousTotalYellowCards = team.getTotalYellowCards();
    	
    	int playersRedCards = 0;
    	int previousTotalRedCards = team.getTotalRedCards();
    	
    	int previousTotalGoalsDifference = team.getTotalGoalsDifference();
    	
    	List<String> players = team.getPlayers();
    	List<FFPlayer> fFEleven = new ArrayList<FFPlayer>();
    	
    	 Iterator<String> playersIterator = players.iterator(); 
			while (playersIterator.hasNext()) {
				
				int matchDayScore = 0;
				int matchDayGoalsScored = 0;
				int matchDayGoalsConceded = 0;
				int matchDayAssists = 0;
				int matchDayYellowCards = 0;
				int matchDayRedCards = 0;
				
				FFPlayer fFPlayer = new FFPlayer();
				String player_id = playersIterator.next();
				Query searchPlayerQuery = new Query(Criteria.where("_id").is(player_id));
				Player player = mongoOperation.findOne(searchPlayerQuery, Player.class, "players");
				if(!(player.getPlayerPerformance().isEmpty())){
					String playerId = player.getPlayer_id();
					Query searchPerfQuery = new Query(Criteria.where("playerId").is(playerId).and("matchDate").is(localDateCreated));
					PlayerPerformance playerPerf = mongoOperation.findOne(searchPerfQuery, PlayerPerformance.class, "playerPerformance");
					
					matchDayScore = playerPerf.getMatchDayPoints().getGamePoints();
					matchDayGoalsScored = playerPerf.getGoals();
					matchDayGoalsConceded = playerPerf.getGoalsConceded();
					matchDayAssists = playerPerf.getAssists();
					matchDayYellowCards = playerPerf.getYellowCards();
					matchDayRedCards = playerPerf.getRedCards();
					
					
					fFPlayer.setPlayerPerformance(playerPerf);
					
					}
				System.out.println("MatchDay Score - " + matchDayScore);
				
				playersScore = playersScore + matchDayScore;
				playersGoalsAgainst = playersGoalsAgainst + matchDayGoalsConceded;
				playersGoalsFor = playersGoalsFor + matchDayGoalsScored;
				playersAssist = playersAssist + matchDayAssists;
				playersYellowCards = playersYellowCards + matchDayYellowCards;
				playersRedCards = playersRedCards + matchDayRedCards;
				
				
				//Set FFPlayer
				fFPlayer.setName(player.getName());
				fFPlayer.setClub(player.getClub());
				fFPlayer.setPlayer_id(player.getPlayer_id());
				fFPlayer.setPosition(player.getPosition());
				fFPlayer.setMatchDayPoints(playersScore);
				fFPlayer.setMatchDayDate(localDateCreated);	
				
				//Add player to first eleven
				fFEleven.add(fFPlayer);
			}
			System.out.println("Previous Score - " + previousTotalScore);
			
			totalScore = playersScore + previousTotalScore;
			team.setTotalPoints(totalScore);
			
			totalGoalsFor = playersGoalsFor + previousTotalGoalsFor;
			team.setTotalGoalsFor(totalGoalsFor);
			
			totalGoalsAgainst= playersGoalsAgainst + previousTotalGoalsAgainst;
			team.setTotalGoalsAgainst(totalGoalsAgainst);
			
			totalYellowCards = playersYellowCards + previousTotalYellowCards;
			team.setTotalYellowCards(totalYellowCards);
			
			totalRedCards = playersRedCards + previousTotalRedCards;
			team.setTotalRedCards(totalRedCards);
			
			totalAssists = playersAssist + previousTotalAssists;
			team.setTotalAssists(totalAssists);
			
			totalGoalsDifference = (playersGoalsFor - playersGoalsAgainst)+ previousTotalGoalsDifference;
			team.setTotalGoalsDifference(totalGoalsDifference);
			
			System.out.println("Goals Diff - " + totalGoalsDifference);
			
			team.setFFEleven(fFEleven);
			
//			List<String> team_leagues = team.getLeague();			
//			Iterator<String> team_leaguesIterator = team_leagues.iterator(); 
//			while (team_leaguesIterator.hasNext()) {
				//String key = team_leaguesIterator.next();
				String key = "todays-Leaderboard";
				String value = team.getName();
				Double score = (double) team.getTotalPoints();
				ops.add(key, value, score);				
				System.out.println("Found key " + key + ", value=" + ops.size(key));				
//			}		
        
        return team;
   
    }

    public void setDelegate(ItemReader<FFTeam> itemReader){
        this.itemReader = itemReader;
    }

}
