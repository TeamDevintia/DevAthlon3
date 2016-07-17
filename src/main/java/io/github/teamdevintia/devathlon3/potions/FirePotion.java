package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.managers.VFXManager;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import io.github.teamdevintia.devathlon3.visuals.potion.FireSplashVPacket;
import io.github.teamdevintia.devathlon3.visuals.potion.trails.FireTrailVPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Potion that sets nearby entities on fire
 *
 * @author MiniDigger
 */
public class FirePotion extends MagicPotion {

    private static final int range = 3;
    private static final int fireTicks = 5 * 20;

    public FirePotion(Devathlon3 devathlon3) {
        super(devathlon3, "FirePotion", devathlon3.getItemConstant().get("item.firePotion"));
    }

    @Override
    public Recipe createRecipe() {
        return instance().getRecipeConstant().get("recipe.potion.fire");
    }

    @Override
    public void onPotionBuild(Entity entity) {
    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new FireTrailVPacket(), null, null, thrownPotion);
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new FireSplashVPacket(), null, location, range, fireTicks);
        // set stuff on fire
        location.getWorld().getNearbyEntities(location, range, range, range).forEach(entity -> entity.setFireTicks(fireTicks));

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
                    // if on surface, spawn fire
                    if (block.getType() != Material.AIR && block.getRelative(BlockFace.UP).getType() == Material.AIR) {
                        block.getRelative(BlockFace.UP).setType(Material.FIRE);
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
                    ParticleUtil.play(loc, Effect.CRIT, 0, 0, 0, 0, 0, 1, 1, 50);
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
