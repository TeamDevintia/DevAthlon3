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

    public Item spawnItem(ItemStack itemStack, Location location) {
        Item item = location.getWorld().dropItemNaturally(location, itemStack);
        return item;
    }

    public Item spawnItem(ItemStack itemStack, Location location, int pickupDelay) {
        Item item = location.getWorld().dropItemNaturally(location, itemStack);
        item.setPickupDelay(pickupDelay);
        return item;
    }

    public Item spawnItem(ItemStack itemStack, Location location, long disposeAfter) {
        Item item = location.getWorld().dropItemNaturally(location, itemStack);
        disposeAfter(Devathlon3.getInstance(), item, disposeAfter);
        return item;
    }

    public Item spawnNotCollectAbleItem(ItemStack itemStack, Location location) {
        Item item = spawnItem(itemStack, location);
        item.setPickupDelay(Integer.MAX_VALUE);
        return item;
    }

    public Item spawnNotCollectAbleItem(ItemStack itemStack, Location location, long disposeAfter) {
        Item item = spawnNotCollectAbleItem(itemStack, location);
        disposeAfter(Devathlon3.getInstance(), item, disposeAfter);
        return item;
    }

    private void disposeAfter(JavaPlugin javaPlugin, Entity entity, long ticks) {
        Bukkit.getScheduler().runTaskLater(javaPlugin, entity::remove, ticks);
    }

}
