package io.github.teamdevintia.magicpotions.potions;

import io.github.teamdevintia.magicpotions.MagicPotions;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

/**
 * @author Shad0wCore & MiniDigger
 */
public abstract class MagicPotion implements Listener {

    private MagicPotions magicPotions;
    private String potionID;
    private ItemStack item;

    public MagicPotion(MagicPotions magicPotions, String potionIdentifier, ItemStack item) {
        this.magicPotions = magicPotions;
        this.potionID = potionIdentifier;
        this.item = item;
    }

    /**
     * Creates the recipe, ready to be registered
     *
     * @return the recipe for this potion
     */
    public abstract Recipe createRecipe();

    /**
     * Called when this potion gets crafted
     *
     * @param entity the entity who crafted or created (via give or a plugin) it
     */
    public abstract void onPotionBuild(Entity entity);

    /**
     * Called then the potion gets launched
     *
     * @param thrower the entity that launched it
     * @param thrownPotion the launched potion
     */
    public abstract void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion);

    /**
     * Called then the potion splashes
     *
     * @param location the location where it hit
     * @param thrownPotion the launched potion
     */
    public abstract void onPotionHit(Location location, ThrownPotion thrownPotion);

    /**
     * @return the name of this potion
     */
    public String getPotionID() {
        return potionID;
    }

    /**
     * @return the item stack for this potion
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * @return the plugin instance
     */
    protected MagicPotions instance() {
        return this.magicPotions;
    }

}
