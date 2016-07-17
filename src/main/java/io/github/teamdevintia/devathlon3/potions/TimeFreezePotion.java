package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.managers.VFXManager;
import io.github.teamdevintia.devathlon3.visuals.potion.TimeFreezeSplashVPacket;
import io.github.teamdevintia.devathlon3.visuals.potion.TimeFreezeTrailVPacket;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * potion that stops time in a radius
 */
public class TimeFreezePotion extends MagicPotion {

    private final int radius = 3;

    public TimeFreezePotion(Devathlon3 devathlon3) {
        super(devathlon3, "TimeFreezePotion", devathlon3.getItemConstant().get("item.timeFreezePotion"));
    }

    @Override
    public Recipe createRecipe() {
        return instance().getRecipeConstant().get("recipe.potion.timeFreeze");
    }

    @Override
    public void onPotionBuild(Entity entity) {
    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket( new TimeFreezeTrailVPacket(), null, null, thrownPotion);
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new TimeFreezeSplashVPacket(), null, location, radius);
        location.getWorld().getNearbyEntities(location, radius, radius, radius).stream().filter(entity -> entity instanceof LivingEntity).
                forEach(entity -> {
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 100, false, false));
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 5 * 20, 100, false, false));
                });
    }
}
