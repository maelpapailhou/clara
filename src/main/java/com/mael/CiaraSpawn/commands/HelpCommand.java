package com.mael.CiaraSpawn.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    private final FileConfiguration config;

    public HelpCommand(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            TextComponent message = new TextComponent("");

            for (int i = 1; i <= 4; i++) {
                String text = config.getString("help.message" + i + ".text");
                String hoverText = config.getString("help.message" + i + ".hover_text");

                TextComponent option = new TextComponent(ChatColor.translateAlternateColorCodes('&', text + "\n"));
                option.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/help message" + i));
                option.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.translateAlternateColorCodes('&', hoverText))));

                message.addExtra(option);
            }

            player.spigot().sendMessage(message);
            player.sendMessage(ChatColor.GRAY + "Pour avoir de l'aide, ouvrez le chat et cliquez sur l'option d'aide souhaitée.");

            return true;
        }
        return false;
    }
}