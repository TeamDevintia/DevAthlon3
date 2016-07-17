package io.github.teamdevintia.magicpotions.potions;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.managers.VFXManager;
import io.github.teamdevintia.magicpotions.portal.WorldSnapshot;
import io.github.teamdevintia.magicpotions.visuals.potion.trails.TrailVPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Potion that creates a huge explosion
 *
 * @author MiniDigger
 */
public class ExplosionPotion extends MagicPotion {

    public ExplosionPotion(MagicPotions magicPotions) {
        super(magicPotions, "ExplosionPotion", magicPotions.getItemConstant().get("item.explosionPotion"));
    }

    @Override
    public Recipe createRecipe() {
        return instance().getRecipeConstant().get("recipe.potion.explosion");
    }

    @Override
    public void onPotionBuild(Entity entity) {

    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new TrailVPacket(), null, null, Sound.ENTITY_GENERIC_EXPLODE, thrownPotion, Effect.LARGE_SMOKE, 255f, 255f, 255f);
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        WorldSnapshot snapshot = new WorldSnapshot(location.clone().subtract(0, 40, 0), 40, 80, Material.AIR);
        location.getWorld().createExplosion(location, 10);

        new BukkitRunnable() {
            @Override
            public void run() {
                snapshot.restoreSlowly(20 * 10, 100);
            }
        }.runTaskLater(instance(), 20 * 10);
    }
}
