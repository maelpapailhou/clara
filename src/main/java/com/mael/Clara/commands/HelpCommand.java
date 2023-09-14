package com.mael.Clara.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // Créez un message vide
            TextComponent message = new TextComponent("");

            // Option 1 : Retourner au spawn
            TextComponent option1 = new TextComponent(ChatColor.GREEN + "* Retourner au spawn");
            option1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spawn"));
            option1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Cliquez pour retourner au spawn")));

            // Option 2 : Exemple de commande 2
            TextComponent option2 = new TextComponent("\n* Option 2");
            option2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/commande2"));
            option2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Exemple de commande 2")));

            // Ajoutez les options au message principal
            player.sendMessage("\n");
            message.addExtra(option1);
            message.addExtra(option2);
            player.spigot().sendMessage(message);
            player.sendMessage("\n");
            player.sendMessage(ChatColor.GRAY + "Pour obtenir de l'aide, ouvrez le chat et cliquez sur l'option d'aide souhaitée.");

            return true;
        }
        return false;
    }

}
