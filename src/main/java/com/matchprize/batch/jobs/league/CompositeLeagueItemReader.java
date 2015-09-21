package com.matchprize.batch.jobs.league;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.matchprize.batch.common.model.FFTeam;
import com.matchprize.batch.common.model.League;


public class CompositeLeagueItemReader implements ItemReader<League> {
	
	@Autowired
	private RedisTemplate<String, String> template;
	ItemReader<League> itemReader;	
	private MongoTemplate mongoTemplate2;
	private MongoOperations mongoOperation;
	private ZSetOperations<String, String> ops;
  	int totalScore;

    public CompositeLeagueItemReader(ItemReader<League> itemReader, MongoTemplate mongoTemplate) {
        this.itemReader = itemReader;
        this.mongoTemplate2  = mongoTemplate;
    }

    public League read() throws Exception {
    	mongoOperation = (MongoOperations)this.mongoTemplate2;
    	ops = this.template.opsForZSet();
    	League league = itemReader.read();
    	List<String> teams = league.getTeams();
    	
    	 Iterator<String> teamsIterator = teams.iterator(); 
			while (teamsIterator.hasNext()) {
				String team_id = teamsIterator.next();
				System.out.println("Team ID--------------" + team_id);
				Query searchTeamQuery = new Query(Criteria.where("_id").is(team_id));
				FFTeam fFTeam = mongoOperation.findOne(searchTeamQuery, FFTeam.class, "teams");
				System.out.println(fFTeam.getName());
				
				Double team_score = (double) fFTeam.getTotalPoints();
				String key = "todays-Leaderboard";
				String value = fFTeam.getName();
				Set<String> tieList = ops.rangeByScore(key,team_score, team_score);
				int tieSize = tieList.size();
				
				if(tieSize > 1){
					System.out.println("Tie Found " + tieSize);	
					assignSameLeaguePosition(tieList, key, value);
					
//					breakTieWithGoalsDifference(tieList);					
//					Double new_team_score = ops.score(key, value);
//					Set<String> tieList2 = ops.rangeByScore(key,new_team_score, new_team_score);
//					int tieSize2 = tieList2.size();
//					
//					if(tieSize2 > 1){
//						assignSameLeaguePosition(tieList2, key, value);
//					}				
					
				}else{
					System.out.println("No Tie Found ");	
				}
				int rank = (int)((ops.reverseRank(key, value)) + 1);			
				System.out.println("Rank--- " + rank);
			  	mongoOperation.upsert(searchTeamQuery, 
                        Update.update("leaguePosition", rank),"teams"); 
			}
	        
        return league;
   
    }

    public void setDelegate(ItemReader<League> itemReader){
        this.itemReader = itemReader;
    }
    
    public void breakTieWithGoalsDifference(Set<String> tieList){
    	System.out.println("Break tie----- ");
	 	Double delta = 0.0;    	
    	 Iterator<String> tieListIterator = tieList.iterator(); 
			while (tieListIterator.hasNext()) {
				String team_name = tieListIterator.next();
				System.out.println(team_name );
				Query searchTeamQuery = new Query(Criteria.where("name").is(team_name));
				FFTeam fFTeam = mongoOperation.findOne(searchTeamQuery, FFTeam.class, "teams");
				Double team_score = (double) fFTeam.getTotalPoints();
				Double team_goalsDiff = (double) fFTeam.getTotalGoalsDifference();
				System.out.println("Team score-------- " + team_score + team_goalsDiff);
				
				delta = team_goalsDiff /1000;
				String key = "todays-Leaderboard";
				
				String value = fFTeam.getName();
				Double score = (team_score + delta) ;
				System.out.println("New score ---- " + score + delta);
				ops.incrementScore(key, value, delta);		
			}
    }
    
    public void assignSameLeaguePosition(Set<String> tieList, String key, String team_name){
    	System.out.println("Assign same position----- ");
	  	int size = tieList.size();
	  	int position = (int)((ops.reverseRank(key, team_name)) + 1);
	  	
    	 Iterator<String> tieListIterator = tieList.iterator(); 
			while (tieListIterator.hasNext()) {
				String name = tieListIterator.next();
				System.out.println("Team Name--------------" + name);
			
				int team_rank = (int)((ops.reverseRank(key, name)) + 1);			
				if(team_rank < position){
					position = team_rank;
				}
			}
			
	    	 Iterator<String> tieListIterator2 = tieList.iterator(); 
				while (tieListIterator2.hasNext()) {
					String name = tieListIterator2.next();
					Query searchTeamQuery = new Query(Criteria.where("name").is(name));
				  	mongoOperation.upsert(searchTeamQuery, 
	                        Update.update("leaguePosition", position),"teams"); 	
			}
    }
    
    public void breakTieWithGoalsFor(Set<String> tieList){
        this.itemReader = itemReader;
    }
    
    public void breakTieWithGoalsAgainst(Set<String> tieList){
        this.itemReader = itemReader;
    }

}
