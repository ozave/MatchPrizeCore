package com.matchprize.batch.jobs.player;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.matchprize.batch.common.model.Player;

public class CustomPlayerItemWriter implements ItemWriter<Player> {
	
	private MongoTemplate mongoTemplate2;
	
	public CustomPlayerItemWriter(MongoTemplate mongoTemplate) {
	    this.mongoTemplate2  = mongoTemplate;
	}
	
	
	 @Override  
	 public void write(List<? extends Player> items) throws Exception { 
		 MongoOperations mongoOperation = (MongoOperations)mongoTemplate2;
		 Iterator<? extends Player> playersIterator = items.iterator(); 
			while (playersIterator.hasNext()) {
				int totalPoints;
				int matchDayScore = 0;
				Player player = playersIterator.next();
				String player_id = player.getPlayer_id();
				int previousTotal = player.getTotalPoints();
				if(!(player.getPlayerPerformance().isEmpty())){
					matchDayScore = player.getPlayerPerformance().get(2).getMatchDayPoints().getGamePoints();
				}
				totalPoints = previousTotal + matchDayScore;
				Query searchUserQuery = new Query(Criteria.where("player_id").is(player_id));
					
				System.out.println(player_id + "-" + totalPoints);
			  	mongoOperation.upsert(searchUserQuery, 
                        Update.update("totalPoints", totalPoints),"players"); 
			}

	    	
	 }  

}
