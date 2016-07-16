package io.github.teamdevintia.devathlon3;

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
 * Created by Martin on 16.07.2016.
 */
public class Blood implements Listener {

    public static final ItemStack BLOOD;
    private static final List<EntityType> agressiveMobs = new ArrayList<>();

    static {
        agressiveMobs.add(EntityType.ZOMBIE);
        agressiveMobs.add(EntityType.SKELETON);
        agressiveMobs.add(EntityType.SPIDER);
        agressiveMobs.add(EntityType.WITCH);

        // blood item
        BLOOD = new ItemFactory(Material.REDSTONE).enchantment(Enchantment.DIG_SPEED, 1, true)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).displayName(ChatColor.RED + "Blut").release();
    }

    public Blood(Devathlon3 plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (!MagicPortal.wizardActive) {
            if (agressiveMobs.contains(event.getEntity().getType())) {
                int r = ThreadLocalRandom.current().nextInt(100);
                if (r <= 33) {
                    Location loc = event.getEntity().getLocation();
                    loc.getWorld().dropItemNaturally(loc, BLOOD);
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().getType() == Material.REDSTONE) {
            if (event.getItemInHand().hasItemMeta()) {
                if (event.getItemInHand().getItemMeta().hasDisplayName()) {
                    if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Blut")) {
                        event.setBuild(false);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        if (event.getInventory().contains(BLOOD)) {
            //TODO whitelist our own recipes here
            event.getInventory().setResult(null);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (event.getInventory().contains(BLOOD)) {
            //TODO whitelist our own recipes here
            event.setCancelled(true);
        }
    }
}
