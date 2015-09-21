package com.matchprize.batch.jobs.team;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.matchprize.batch.common.model.FFPlayer;
import com.matchprize.batch.common.model.FFTeam;

public class CustomTeamMongoItemWriter implements ItemWriter<FFTeam> {
	
	private MongoTemplate mongoTemplate2;
	
	public CustomTeamMongoItemWriter(MongoTemplate mongoTemplate) {
	    this.mongoTemplate2  = mongoTemplate;
	}
	
	
	 @Override  
	 public void write(List<? extends FFTeam> items) throws Exception { 
		 MongoOperations mongoOperation = (MongoOperations)mongoTemplate2;
		 Iterator<? extends FFTeam> teamsIterator = items.iterator(); 
			while (teamsIterator.hasNext()) {
				FFTeam team = teamsIterator.next();
				String team_name = team.getName();
				int totalPoints = team.getTotalPoints();
				int totalGoalsDifference = team.getTotalGoalsDifference();
				List<FFPlayer> fFEleven = team.getFFEleven();
				Query searchUserQuery = new Query(Criteria.where("name").is(team_name));					
				System.out.println(team_name + "-" + totalPoints);
			  	mongoOperation.upsert(searchUserQuery, 
                        Update.update("totalPoints", totalPoints),"teams"); 
			  	mongoOperation.upsert(searchUserQuery, 
                        Update.update("totalGoalsDifference", totalGoalsDifference),"teams"); 
			  	mongoOperation.upsert(searchUserQuery, 
                        Update.update("fFEleven", fFEleven),"teams");
			}	    	
	 }  

}
