package com.mael.Clara.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void itemThrownEvent(PlayerDropItemEvent throwevent){
        throwevent.setCancelled(false);
        throwevent.getPlayer().sendMessage("Tu ne peux pas jeter des objets");

    }
}
