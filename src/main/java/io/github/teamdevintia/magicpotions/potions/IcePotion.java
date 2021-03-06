package io.github.teamdevintia.magicpotions.potions;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.managers.VFXManager;
import io.github.teamdevintia.magicpotions.util.ParticleUtil;
import io.github.teamdevintia.magicpotions.visuals.potion.trails.TrailVPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Potion that freezes everything in a range
 *
 * @author MiniDigger
 */
public class IcePotion extends MagicPotion {

    private static final int range = 3;

    public IcePotion(MagicPotions magicPotions) {
        super(magicPotions, "IcePotion", magicPotions.getItemConstant().get("item.icePotion"));
    }

    @Override
    public Recipe createRecipe() {
        return instance().getRecipeConstant().get("recipe.potion.ice");
    }

    @Override
    public void onPotionBuild(Entity entity) {

    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new TrailVPacket(), null, null, Sound.BLOCK_SNOW_BREAK, thrownPotion, Effect.SMALL_SMOKE, 50f, 100f, 100f);
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        // ice ice baby
        location.getWorld().getNearbyEntities(location, range, range, range).stream().filter(entity -> entity instanceof LivingEntity).
                forEach(entity -> {
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 2, false, false));
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 5 * 20, 1, false, false));
                    ((LivingEntity) entity).damage(2, (Entity) thrownPotion.getShooter());
                });

        int fromX;
        int toX;
        if (location.getX() > 0) {
            fromX = location.getBlockX() - range;
            toX = location.getBlockX() + range;
        } else {
            fromX = location.getBlockX() + range;
            toX = location.getBlockX() - range;
        }

        int fromY;
        int toY;
        if (location.getY() > 0) {
            fromY = location.getBlockY() - range;
            toY = location.getBlockY() + range;
        } else {
            fromY = location.getBlockY() + range;
            toY = location.getBlockY() - range;
        }

        int fromZ;
        int toZ;
        if (location.getZ() > 0) {
            fromZ = location.getBlockZ() - range;
            toZ = location.getBlockZ() + range;
        } else {
            fromZ = location.getBlockZ() + range;
            toZ = location.getBlockZ() - range;
        }

        List<Location> particleLocs = new ArrayList<>();
        for (int x = fromX; x < toX; x++) {
            for (int y = fromY; y < toY; y++) {
                for (int z = fromZ; z < toZ; z++) {
                    Block block = location.getWorld().getBlockAt(x, y, z);
                    // make ice transform into water
                    if (block.getType() == Material.WATER || block.getType() == Material.STATIONARY_WATER) {
                        block.setType(Material.ICE);
                    }

                    // if in air, spawn particle
                    if (block.getType() == Material.AIR) {
                        particleLocs.add(block.getLocation());
                    }
                }
            }
        }

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Location loc : particleLocs) {
                    ParticleUtil.play(loc, Effect.SNOW_SHOVEL, 0, 0, 0, 0, 0, .1F, 1, 50);
                }
            }
        }.runTaskTimer(instance(), 0, 5);

        new BukkitRunnable() {
            @Override
            public void run() {
                task.cancel();
            }
        }.runTaskLater(instance(), 5 * 20);
    }
}
