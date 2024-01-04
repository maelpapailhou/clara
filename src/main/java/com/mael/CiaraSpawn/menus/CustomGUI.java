package com.mael.CiaraSpawn.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomGUI {
    private final String name;
    private final String command;
    private final Inventory inventory;

    public CustomGUI(String name, String command, int size) {
        this.name = name;
        this.command = command;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public void setItem(int slot, ItemStack itemStack) {
        inventory.setItem(slot, itemStack);
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    public String getCommand() {
        return command;
    }

    public int getInventorySize() {
        return inventory.getSize();
    }
}