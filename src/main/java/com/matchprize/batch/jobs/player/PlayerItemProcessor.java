package com.matchprize.batch.jobs.player;

import org.springframework.batch.item.ItemProcessor;

import com.matchprize.batch.common.model.Player;

public class PlayerItemProcessor implements ItemProcessor<Player, Player> {
	 @Override
    public Player process(final Player player) throws Exception {
  
        System.out.println("Converting (" + player.getName() + ")");

        return player;
    }

}