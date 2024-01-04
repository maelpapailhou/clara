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

            TextComponent message = new TextComponent("");

            TextComponent option1 = new TextComponent(ChatColor.GREEN + "* Comment jouer ?\n");
            option1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help commentjouer"));
            option1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Apprenez comment jouer")));

            TextComponent option2 = new TextComponent(ChatColor.GREEN + "* Découvrir le lobby\n");
            option2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help lobby"));
            option2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Explorez notre lobby et découvrez les règles du serveur")));

            TextComponent option3 = new TextComponent(ChatColor.GREEN + "* Contacter le staff\n");
            option3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help staff"));
            option3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Contactez notre équipe de modération et d'assistance en cas de besoin")));

            TextComponent option4 = new TextComponent(ChatColor.RED + "* Signaler un joueur\n");
            option4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report"));
            option4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Signalefsdfqdsfdsfqsdfdsz un joueur si vous rencontrez des problèmes ou des comportements inappropriés")));

            message.addExtra(option1);
            message.addExtra(option2);
            message.addExtra(option3);
            message.addExtra(option4);
            player.spigot().sendMessage(message);
            player.sendMessage(ChatColor.GRAY + "Pour test de l'aide, ouvrez le chat et cliquez sur l'option d'aide souhaitée.");

            return true;
        }
        return false;
    }

}
