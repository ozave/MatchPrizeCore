package com.matchprize.batch.jobs.league;

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

import com.matchprize.batch.common.model.League;

public class CustomLeagueMongoItemWriter implements ItemWriter<League> {
	
	private MongoTemplate mongoTemplate2;
	
	public CustomLeagueMongoItemWriter(MongoTemplate mongoTemplate) {
	    this.mongoTemplate2  = mongoTemplate;
	}
	
	
	 @Override  
	 public void write(List<? extends League> items) throws Exception { 
		 MongoOperations mongoOperation = (MongoOperations)mongoTemplate2;
		 Iterator<? extends League> leaguesIterator = items.iterator(); 
			while (leaguesIterator.hasNext()) {
				League league = leaguesIterator.next();
				String league_name = league.getName();
				
			}	    	
	 }  

}
