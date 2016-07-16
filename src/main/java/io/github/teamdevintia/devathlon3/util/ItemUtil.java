package io.github.teamdevintia.devathlon3.util;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Shad0wCore
 */
public final class ItemUtil {

    /**
     * Spawns a item with given arguments
     *
     * @param itemStack The item stack which will be applied to the item
     * @param location  The location where the item will spawn at
     */
    public Item spawnItem(ItemStack itemStack, Location location) {
        Item item = location.getWorld().dropItemNaturally(location, itemStack);
        return item;
    }

    /**
     * Spawns a item with given arguments
     *
     * @param itemStack   The item stack which will be applied to the item
     * @param location    The location where the item will spawn at
     * @param pickupDelay The pickup delay until the entity will be able to pickup the item
     */
    public Item spawnItem(ItemStack itemStack, Location location, int pickupDelay) {
        Item item = location.getWorld().dropItemNaturally(location, itemStack);
        item.setPickupDelay(pickupDelay);
        return item;
    }

    /**
     * Spawns a item with given arguments
     *
     * @param itemStack    The item stack which will be applied to the item
     * @param location     The location where the item will spawn at
     * @param disposeAfter The delay until the item despawns
     */
    public Item spawnItem(ItemStack itemStack, Location location, long disposeAfter) {
        Item item = location.getWorld().dropItemNaturally(location, itemStack);
        disposeAfter(Devathlon3.getInstance(), item, disposeAfter);
        return item;
    }

    /**
     * Spawns a not collectable item with given arguments
     *
     * @param itemStack The item stack which will be applied to the item
     * @param location  The location where the item will spawn at
     */
    public Item spawnNotCollectAbleItem(ItemStack itemStack, Location location) {
        Item item = spawnItem(itemStack, location);
        item.setPickupDelay(Integer.MAX_VALUE);
        return item;
    }

    /**
     * Spawns a not collectable item with given arguments
     *
     * @param itemStack    The item stack which will be applied to the item
     * @param location     The location where the item will spawn at
     * @param disposeAfter The delay until the item despawns
     */
    public Item spawnNotCollectAbleItem(ItemStack itemStack, Location location, long disposeAfter) {
        Item item = spawnNotCollectAbleItem(itemStack, location);
        disposeAfter(Devathlon3.getInstance(), item, disposeAfter);
        return item;
    }

    private void disposeAfter(JavaPlugin javaPlugin, Entity entity, long ticks) {
        Bukkit.getScheduler().runTaskLater(javaPlugin, entity::remove, ticks);
    }

}
