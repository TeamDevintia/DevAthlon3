package io.github.teamdevintia.devathlon3.items;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Handles the mechanics attached to the essence item.
 *
 * @author MiniDigger
 */
public final class Essence implements Listener {

    private ItemStack ESSENCE_STACK;

    public Essence(Devathlon3 plugin) {
        this.ESSENCE_STACK = plugin.getItemConstant().get("item.essence");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // spawn essences on death of some entities
        // after before wizard spawns
        if (MagicPortal.wizardActive) {
            Location location = event.getEntity().getLocation();

            // 3 for witch
            if (event.getEntityType() == EntityType.WITCH) {
                for (int i = 0; i < 3; i++) {
                    location.getWorld().dropItemNaturally(location, this.ESSENCE_STACK);
                }
            } else
                // 1 for the rest
                if (event.getEntityType() == EntityType.ZOMBIE || event.getEntityType() == EntityType.SKELETON || event.getEntityType() == EntityType.SPIDER) {
                    location.getWorld().dropItemNaturally(location, this.ESSENCE_STACK);
                }
        }
    }
}
