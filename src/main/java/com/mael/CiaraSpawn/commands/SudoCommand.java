package com.mael.Clara.commands;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SudoCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            // Vérifiez si la commande est exécutée par un joueur
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Créez une entité poussin nommée "Clara" à la position du joueur
                Chicken claraChicken = (Chicken) player.getWorld().spawnEntity(player.getLocation(), EntityType.CHICKEN);
                claraChicken.setCustomName("Clara");
                claraChicken.setCustomNameVisible(true);

                // Ajoutez des particules de cœur autour du poussin Clara
                claraChicken.getWorld().spawnParticle(Particle.HEART, claraChicken.getLocation().add(0, 1, 0), 50, 0.5, 0.5, 0.5, 0.1);

                player.sendMessage("Un poussin nommé Clara a été créé !");
                return true;
            } else {
                sender.sendMessage("Cette commande est réservée aux joueurs.");
                return false;
            }
        }

    }
