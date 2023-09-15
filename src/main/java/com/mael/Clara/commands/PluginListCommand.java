package com.mael.Clara.commands;

import com.mael.Clara.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class PluginListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.sendMessage("Liste des plugins :");

            Plugin[] plugins = Bukkit.getPluginManager().getPlugins();

            for (Plugin plugin : plugins) {
                PluginDescriptionFile description = plugin.getDescription();
                String pluginName = description.getName();
                String pluginVersion = description.getVersion();
                String pluginAuthors = String.join(", ", description.getAuthors());

                player.sendMessage(ChatColor.AQUA + pluginName
                        + ChatColor.GRAY + "\nStatut : " + ChatColor.GREEN + ChatColor.BOLD + "\u2713"
                        + ChatColor.RESET + ChatColor.GRAY + "\nVersion : "
                        + pluginVersion
                        + "\nAuteur(s) : "
                        + pluginAuthors);
            }
        }
        return true;
    }

}
