package io.github.teamdevintia.magicpotions.potions;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.managers.VFXManager;
import io.github.teamdevintia.magicpotions.visuals.potion.TimeFreezeSplashVPacket;
import io.github.teamdevintia.magicpotions.visuals.potion.trails.TrailVPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * potion that stops time in a radius
 *
 * @author MiniDigger
 */
public class TimeFreezePotion extends MagicPotion {

    private final int radius = 3;

    public TimeFreezePotion(MagicPotions magicPotions) {
        super(magicPotions, "TimeFreezePotion", magicPotions.getItemConstant().get("item.timeFreezePotion"));
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
        VFXManager.triggerVFXPacket(new TrailVPacket(), null, null, Sound.ENTITY_ZOMBIE_INFECT, thrownPotion, Effect.SNOWBALL_BREAK, 52f, 73f, 94f);
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
