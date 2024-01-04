package com.mael.CiaraSpawn.events;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class ArrowHitListener implements Listener {

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ARROW && event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();

            // Créez un feu d'artifice à l'emplacement de la flèche
            Location explosionLocation = event.getEntity().getLocation();
            Firework firework = player.getWorld().spawn(explosionLocation, Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();
            FireworkEffect.Builder builder = FireworkEffect.builder();

            // Configurez les propriétés de l'effet du feu d'artifice ici
            builder.withColor(Color.AQUA); // Couleur bleu clair
            builder.with(FireworkEffect.Type.BALL_LARGE); // Type BALL_LARGE (grosse boule)
            builder.withFlicker(); // Scintillement

            // Luminance élevée pour simuler la lumière
            builder.withFade(Color.AQUA); // Couleur d'estompage (AQUA est une nuance de bleu)

            FireworkEffect effect = builder.build();
            meta.addEffect(effect);
            meta.setPower(1); // Puissance du feu d'artifice

            firework.setFireworkMeta(meta);

            // Supprimez la flèche pour éviter la duplication
            event.getEntity().remove();

            // Simulez l'apparition de lumière avec un effet de particules
            simulateLight(explosionLocation);
        }
    }

    private void simulateLight(Location location) {
        // Créez ici un effet de particules pour simuler la lumière, par exemple, des particules de glowstone
        location.getWorld().spawnParticle(Particle.END_ROD, location, 500, 1, 1, 1, 1, null);
    }
}
