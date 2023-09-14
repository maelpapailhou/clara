package com.mael.Clara.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FullHealthListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // Annulez les dégâts pour garantir que le joueur a toujours une santé maximale
            player.setHealth(20);

            // Annulez l'événement de dégâts
            event.setCancelled(true);
        }
    }


}

