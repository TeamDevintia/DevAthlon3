package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

/**
 * @author Shad0wCore
 */
public final class FreezePotion extends MagicPotion {

    public FreezePotion(Devathlon3 devathlon3, String potionIdentifier) {
        super(devathlon3, potionIdentifier, new ItemStack(Material.SLIME_BALL));
    }

    @Override
    public Recipe createRecipe() {
        return null;
    }

    @Override
    public void onPotionBuild(Entity entity) {

    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {

    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {

    }

}
