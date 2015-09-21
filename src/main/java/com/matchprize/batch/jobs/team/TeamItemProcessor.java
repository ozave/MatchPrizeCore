package com.matchprize.batch.jobs.team;

import org.springframework.batch.item.ItemProcessor;

import com.matchprize.batch.common.model.FFTeam;

public class TeamItemProcessor implements ItemProcessor<FFTeam, FFTeam> {
	 @Override
    public FFTeam process(final FFTeam team) throws Exception {
  
        System.out.println("Points for (" + team.getName() + "-" + team.getTotalPoints() + ")");

        return team;
    }

}