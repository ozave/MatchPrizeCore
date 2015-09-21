package com.matchprize.batch.jobs.matchday;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import com.matchprize.batch.common.model.MatchDay;

public class CustomMatchDayItemWriter implements ItemWriter<MatchDay> {
	
	@Autowired
	private MongoTemplate mongoTemplate2;
	@Autowired
	private RedisTemplate<String, String> template;
	private MongoOperations mongoOperation;
	private ZSetOperations<String, String> ops;
	
	public CustomMatchDayItemWriter(MongoTemplate mongoTemplate) {
	    this.mongoTemplate2  = mongoTemplate;
	}
	
	
	 @Override  
	 public void write(List<? extends MatchDay> items) throws Exception { 
		 mongoOperation = (MongoOperations)this.mongoTemplate2;
	     ops = this.template.opsForZSet();
		 Iterator<? extends MatchDay> matchDaysIterator = items.iterator(); 
			while (matchDaysIterator.hasNext()) {
				
				MatchDay matchDay = matchDaysIterator.next();
				String matchDay_id = matchDay.get_id();
				
				Query searchMatchDayQuery = new Query(Criteria.where("_id").is(matchDay_id));
				Query searchFixturesQuery = new Query(Criteria.where("matchDate").is(matchDay_id));
				String key = "matchDays";
				int rank = (int)((ops.rank(key, matchDay_id)) + 1);
				System.out.println(matchDay_id + "--- " + rank);
				System.out.println(getGameweekNo(matchDay_id));
				
				int gameWeek = getGameweekNo(matchDay_id);
				
			  	mongoOperation.upsert(searchMatchDayQuery,Update.update("matchDay", rank),"matchDays"); 
			  	mongoOperation.updateMulti(searchFixturesQuery, Update.update("matchDay", rank),"fixtures");
			  	
			  	mongoOperation.upsert(searchMatchDayQuery,Update.update("gameWeek", gameWeek),"matchDays"); 
			  	mongoOperation.updateMulti(searchFixturesQuery, Update.update("gameWeek", gameWeek),"fixtures");
			  	
			}			  	
	    	
	 	}
	 
	    public int getGameweekNo(String date) throws ParseException {
            int gameweekNo = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            for (GameWeekEnum gwk : GameWeekEnum.values()){
            	 Date match_date = formatter.parse(date);
                 Date gwk_start = formatter.parse(gwk.start_date());
                 Date gwk_end = formatter.parse(gwk.end_date());

                 if(!(match_date.before(gwk_start)) && !(match_date.after(gwk_end))){
                      gameweekNo = gwk.gameweek_No();
                      break;  
                 }

            }

            return gameweekNo;

	    }

}

