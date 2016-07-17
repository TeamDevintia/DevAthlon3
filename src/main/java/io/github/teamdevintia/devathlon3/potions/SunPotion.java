package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.managers.VFXManager;
import io.github.teamdevintia.devathlon3.visuals.potion.SunSplashVPacket;
import io.github.teamdevintia.devathlon3.visuals.potion.trails.SunTrailVPacket;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;

/**
 * @author Shad0wCore
 */
public class SunPotion extends MagicPotion {

    private static final int range = 3;

    public SunPotion(Devathlon3 devathlon3) {
        super(devathlon3, "SunPotion", devathlon3.getItemConstant().get("item.sunPotion"));
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
