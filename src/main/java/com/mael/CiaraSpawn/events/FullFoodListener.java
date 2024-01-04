package com.mael.CiaraSpawn.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FullFoodListener implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getEntity();

            // Annulez le changement de niveau de faim pour garantir que le joueur a toujours une faim complète
            event.setCancelled(true);

            // Rétablissez le niveau de faim du joueur à son maximum
            player.setFoodLevel(20);
        }
    }
}
