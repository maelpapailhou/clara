package com.mael.Clara.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractionListener implements Listener {

    @EventHandler
    public void noBlockInteraction(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        e.setCancelled(true);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }
}
