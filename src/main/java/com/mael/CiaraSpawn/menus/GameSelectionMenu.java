package com.mael.CiaraSpawn.menus;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GameSelectionMenu implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openProfileMenu(player);
        } else {
            sender.sendMessage("Cette commande est réservée aux joueurs.");
        }
        return true;
    }
    public static void openProfileMenu(Player player) {
        String playerName = player.getName();
        Inventory menu = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Accès rapide > " + ChatColor.AQUA + "Sélectionnez un jeu");

        // Créez un ItemStack avec le matériau BRICK
        ItemStack brickBlock = new ItemStack(Material.BRICK);
        ItemMeta brickBlockMeta = brickBlock.getItemMeta();
        brickBlockMeta.setDisplayName("FreeCube");
        brickBlockMeta.setLore(Arrays.asList("Description du bloc ici."));
        brickBlock.setItemMeta(brickBlockMeta);

        menu.setItem(4, brickBlock);

        player.openInventory(menu);
    }

}
