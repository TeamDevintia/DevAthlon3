package io.github.teamdevintia.devathlon3.items;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Handles the mechanics attached to the blood item.
 *
 * @author MiniDigger
 */
public class Blood implements Listener {

    private static final List<EntityType> aggressiveMobs = new ArrayList<>();
    private ItemStack BLOOD_STACK, RITUAL_LANTERN;

    static {
        // Add blood dropping entities
        aggressiveMobs.add(EntityType.ZOMBIE);
        aggressiveMobs.add(EntityType.SKELETON);
        aggressiveMobs.add(EntityType.SPIDER);
        aggressiveMobs.add(EntityType.WITCH);
    }

    public Blood(Devathlon3 plugin) {
        this.BLOOD_STACK = plugin.getItemConstant().get("item.blood");
        this.RITUAL_LANTERN = plugin.getItemConstant().get("item.ritualLantern");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        // spawn blood on death of some entities
        // only before wizard spawns
        if (!MagicPortal.wizardActive) {
            if (aggressiveMobs.contains(event.getEntity().getType())) {
                int r = ThreadLocalRandom.current().nextInt(100);
                // 33% chance
                if (r <= 33) {
                    Location loc = event.getEntity().getLocation();
                    loc.getWorld().dropItemNaturally(loc, this.BLOOD_STACK);
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        // don't place blood
        if (event.getItemInHand().equals(BLOOD_STACK)) {
            event.setBuild(false);
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        // don't craft normal stuff with blood
        if (event.getInventory().contains(BLOOD_STACK)) {
            if (event.getRecipe().getResult().equals(this.RITUAL_LANTERN)) {
                return;
            }

            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        // don't craft normal stuff with blood
        if (event.getInventory().contains(BLOOD_STACK)) {
            if (event.getRecipe().getResult().equals(this.RITUAL_LANTERN)) {
                return;
            }

            event.setCancelled(true);
        }
    }
}
