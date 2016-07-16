package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.Recipe;

/**
 * Potion that sets nearby entities on fire
 *
 * @author MiniDigger
 */
public class FirePotion extends MagicPotion {

    private static final int range = 3;

    public FirePotion(Devathlon3 devathlon3) {
        super(devathlon3, "FirePotion", devathlon3.getItemConstant().get("item.firepotion"));
    }

    @Override
    public Recipe createRecipe() {
        System.out.println("register");
        return instance().getRecipeConstant().get("recipe.potion.fire");
    }

    @Override
    public void onPotionBuild(Entity entity) {
        System.out.println("build!");
    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        // TODO particle trail
        System.out.println("launch!");
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        System.out.println("hit!");
        // set stuff on fire
        location.getWorld().getNearbyEntities(location, range, range, range).forEach(entity -> entity.setFireTicks(20 * 5));

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
                        ParticleUtil.play(block.getLocation(), Effect.BOW_FIRE, 0, 0, 0, 0, 0, 1, 1, 50);
                    }
                }
            }
        }
    }
}