package io.github.teamdevintia.magicpotions.managers;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.potions.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SplashPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Shad0wCore
 */
public final class PotionManager implements Listener {

    private MagicPotions plugin;
    private HashMap<String, MagicPotion> magicPotionMap = new HashMap<>();

    public PotionManager(MagicPotions magicPotions) {
        this.plugin = magicPotions;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Registers a magic potion
     *
     * @param magicPotion the potion to register
     */
    public void registerMagicPotion(MagicPotion magicPotion) {
        magicPotionMap.put(magicPotion.getPotionID(), magicPotion);
        Bukkit.getServer().addRecipe(magicPotion.createRecipe());
        Bukkit.getPluginManager().registerEvents(magicPotion, plugin);
    }

    /**
     * Registers all potions
     */
    public void registerPotions() {
        registerMagicPotion(new FirePotion(plugin));
        registerMagicPotion(new IcePotion(plugin));
        registerMagicPotion(new TimeFreezePotion(plugin));
        registerMagicPotion(new ImpulsePotion(plugin));
        registerMagicPotion(new WinterPotion(plugin));
        registerMagicPotion(new SunPotion(plugin));
        registerMagicPotion(new ExplosionPotion(plugin));
    }

    @EventHandler
    public void onSplash(PotionSplashEvent event) {
        // call onPotionHit for the magic potion that just splashed
        if (!(event.getEntity().getShooter() instanceof Entity)) {
            return;
        }
        if (event.getEntity() instanceof SplashPotion) {
            SplashPotion s = (SplashPotion) event.getEntity();

            MagicPotion potion = getFromItem(s.getItem());
            if (potion == null) {
                return;
            }
            potion.onPotionHit(event.getEntity().getLocation(), s);
        }
    }

    @EventHandler
    public void potionThrow(ProjectileLaunchEvent event) {
        // call onPotionLaunch for the magic potion that was just launched
        if (!(event.getEntity().getShooter() instanceof Entity)) {
            return;
        }

        if (event.getEntity() instanceof SplashPotion) {
            SplashPotion s = (SplashPotion) event.getEntity();

            // fix amount
            ItemStack item = s.getItem();
            item.setAmount(1);
            s.setItem(item);

            MagicPotion potion = getFromItem(s.getItem());
            if (potion == null) {
                return;
            }
            potion.onPotionLaunch((Entity) event.getEntity().getShooter(), s);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        // call onPotionBuild for the magic potion that was just crafted
        magicPotionMap.values().stream().filter(potion -> event.getRecipe().getResult().equals(potion.getItem()))
                .forEach(potion -> potion.onPotionBuild(event.getWhoClicked()));
    }

    /**
     * searches for the magic potion that belongs to that item
     *
     * @param is the item that should be a potion
     * @return the found potion, may be null
     */
    public MagicPotion getFromItem(ItemStack is) {
        for (String key : magicPotionMap.keySet()) {
            MagicPotion potion = magicPotionMap.get(key);
            if (potion.getItem().equals(is)) {
                return potion;
            }
        }

        return null;
    }

    /**
     * Gives the player the potion with that id, will fail silently if there is no such potion
     *
     * @param player the player
     * @param id     the potion id
     */
    public void give(Player player, String id) {
        MagicPotion potion = magicPotionMap.get(id);
        if (potion == null) {
            return;
        }

        player.getInventory().addItem(potion.getItem());
    }

    /**
     * @param id the id of the potion
     * @return the potion with that id, null if not present
     */
    public MagicPotion getFromId(String id) {
        return magicPotionMap.get(id);
    }

    /**
     * @return the names of all potions
     */
    public Set<String> getNames() {
        return magicPotionMap.keySet();
    }

    /**
     * @return a random potion item stack, null if error occoured
     */
    public ItemStack getRandomPotion() {
        int r = ThreadLocalRandom.current().nextInt(magicPotionMap.size());
        String key = "";
        Iterator<String> it = magicPotionMap.keySet().iterator();
        for (int i = 0; i < r; i++) {
            if (it.hasNext()) {
                key = it.next();
            }
        }

        MagicPotion potion = magicPotionMap.get(key);
        if (potion != null) {
            return potion.getItem();
        }
        // just don't return null
        return new ItemStack(Material.SPLASH_POTION);
    }
}
