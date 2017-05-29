package com.monolytum.heatmap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovementMap extends AbstractHeatMap implements Listener {

	public PlayerMovementMap(BaseHeatMap map) {
		super(map);
	}

	@Override
	void enable() {
		Bukkit.getPluginManager().registerEvents(this, HeatMap.heatMap);
	}

	@Override
	void disable() {
		PlayerMoveEvent.getHandlerList().unregister(this);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Location location = e.getPlayer().getLocation();
		Block block = location.getBlock();
		Block previousBlock = e.getFrom().subtract(location).getBlock();
		if (!(block.getX() == previousBlock.getX() && block.getZ() == previousBlock.getZ())) {
			map.addAt(location, 1);
		}
	}
	
}