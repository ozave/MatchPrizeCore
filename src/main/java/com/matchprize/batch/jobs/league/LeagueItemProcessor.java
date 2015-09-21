package com.matchprize.batch.jobs.league;

import org.springframework.batch.item.ItemProcessor;

import com.matchprize.batch.common.model.League;

public class LeagueItemProcessor implements ItemProcessor<League, League> {
	
	 @Override
    public League process(final League league) throws Exception {
  
        System.out.println("Points for (" + league.getName() + ")");

        return league;
    }

}