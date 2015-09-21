package com.matchprize.batch.jobs.matchday;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import com.matchprize.batch.common.model.MatchDay;

public class MatchDayItemProcessor implements ItemProcessor<MatchDay, MatchDay> {
	@Autowired
	private RedisTemplate<String, String> template;
	private ZSetOperations<String, String> ops;

    
	@Override
    public MatchDay process(final MatchDay matchDay) throws Exception {
	    	ops = this.template.opsForZSet();
	    	
	    	String key = "matchDays";
			String value = matchDay.get_id();
			Double score = matchDay.getScore();
			ops.add(key, value, score);				
			System.out.println("Found key " + key + ", value=" + ops.size(key));
	    	
  
        System.out.println("Converting (" + matchDay.get_id() + ")");

        return matchDay;
    }


}