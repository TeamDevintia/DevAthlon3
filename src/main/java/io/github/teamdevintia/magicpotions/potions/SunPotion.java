package io.github.teamdevintia.magicpotions.potions;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.managers.VFXManager;
import io.github.teamdevintia.magicpotions.visuals.potion.SunSplashVPacket;
import io.github.teamdevintia.magicpotions.visuals.potion.trails.SunTrailVPacket;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;

/**
 * potion that causes a light beam from the sun
 *
 * @author Shad0wCore
 */
public class SunPotion extends MagicPotion {

    private static final int range = 3;

    public SunPotion(MagicPotions magicPotions) {
        super(magicPotions, "SunPotion", magicPotions.getItemConstant().get("item.sunPotion"));
    }

    @Override
    public Recipe createRecipe() {
        return instance().getRecipeConstant().get("recipe.potion.sun");
    }

    @Override
    public void onPotionBuild(Entity entity) {
    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new SunTrailVPacket(), null, null, thrownPotion);
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new SunSplashVPacket(), null, location, range);
    }

}
