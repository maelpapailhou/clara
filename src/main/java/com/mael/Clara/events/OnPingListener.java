package com.mael.Clara.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

public class OnPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {

        e.setMaxPlayers(19);
        e.setMotd("          §e§k!!§r §d§lProjet L&A §r§7(Version Alpha) §e§k!!\n       §bUne aventure, un univers, un destin...");
        try {
            e.setServerIcon(Bukkit.loadServerIcon(new File("icon.png")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }

    }
