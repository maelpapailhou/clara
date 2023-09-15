package com.mael.Clara.events;

import com.mael.Clara.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        String playerRank = "Joueur";
        String message = event.getMessage();

        TextComponent playerNameComponent = new TextComponent(playerRank + " ");
        playerNameComponent.setColor(ChatColor.YELLOW);
        playerNameComponent.addExtra(playerName);
        playerNameComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Informations sur le joueur").create()));

        TextComponent messageComponent = new TextComponent(": " + message);
        messageComponent.setColor(ChatColor.WHITE);
        TextComponent formattedMessage = new TextComponent(playerNameComponent, messageComponent);

        Main.getInstance().getServer().spigot().broadcast(formattedMessage);
        event.setCancelled(true);
    }
}






