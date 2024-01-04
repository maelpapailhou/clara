package com.mael.Clara.menus;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ProfileMenu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Vérifiez si la commande est exécutée par un joueur
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
        Inventory menu = Bukkit.createInventory(null, 54, playerName + "'s Profile");

        // Slot 1: Tête du joueur avec description
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.setOwningPlayer(player);
        playerHeadMeta.setDisplayName(ChatColor.AQUA + playerName);
        playerHeadMeta.setLore(Arrays.asList("Grade: Joueur"));
        playerHead.setItemMeta(playerHeadMeta);
        menu.setItem(4, playerHead);

        // Slot 2: Tête verte avec un +
        ItemStack greenHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta greenHeadMeta = (SkullMeta) greenHead.getItemMeta();
        greenHeadMeta.setOwner("MHF_ArrowUp");
        greenHeadMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "+" + ChatColor.RESET + ChatColor.GREEN + " Ajouter un ami");
        greenHead.setItemMeta(greenHeadMeta);
        menu.setItem(1, greenHead);

        // Ouvrir le menu pour le joueur
        player.openInventory(menu);
    }

}
