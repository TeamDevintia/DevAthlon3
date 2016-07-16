package io.github.teamdevintia.devathlon3.items;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import io.github.teamdevintia.devathlon3.util.factory.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemFlag;
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

    private static final List<EntityType> agressiveMobs = new ArrayList<>();

    public static final ItemStack BLOOD;

    static {
        // add blood dropping entities
        agressiveMobs.add(EntityType.ZOMBIE);
        agressiveMobs.add(EntityType.SKELETON);
        agressiveMobs.add(EntityType.SPIDER);
        agressiveMobs.add(EntityType.WITCH);

        // blood item
        BLOOD = new ItemFactory(Material.REDSTONE).amount(1).displayName(ChatColor.RED + "Blut")
                .itemFlags(ItemFlag.HIDE_ENCHANTS).enchantment(Enchantment.DIG_SPEED, 1, true).release();
    }

    public Blood(Devathlon3 plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        // spawn blood on death of some entities
        // only before wizard spawns
        if (!MagicPortal.wizardActive) {
            if (agressiveMobs.contains(event.getEntity().getType())) {
                int r = ThreadLocalRandom.current().nextInt(100);
                // 33% chance
                if (r <= 33) {
                    Location loc = event.getEntity().getLocation();
                    loc.getWorld().dropItemNaturally(loc, BLOOD);
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        // don't place blood
        if (event.getItemInHand().equals(BLOOD)) {
            event.setBuild(false);
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        // don't craft normal stuff with blood
        if (event.getInventory().contains(BLOOD)) {
            if (event.getRecipe().getResult().equals(Devathlon3.ritualsLeuchter)) {
                return;
            }

            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        // don't craft normal stuff with blood
        if (event.getInventory().contains(BLOOD)) {
            if (event.getRecipe().getResult().equals(Devathlon3.ritualsLeuchter)) {
                return;
            }

            event.setCancelled(true);
        }
    }
}
