package io.github.teamdevintia.magicpotions.potions;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.managers.VFXManager;
import io.github.teamdevintia.magicpotions.util.EntityUtil;
import io.github.teamdevintia.magicpotions.visuals.potion.trails.TrailVPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * potion that lets the earth shake
 *
 * @author MiniDigger
 */
public class ImpulsePotion extends MagicPotion {

    private static final int range = 3;

    public ImpulsePotion(MagicPotions magicPotions) {
        super(magicPotions, "ImpulsePotion", magicPotions.getItemConstant().get("item.impulsePotion"));
    }

    @Override
    public Recipe createRecipe() {
        return instance().getRecipeConstant().get("recipe.potion.impulse");
    }

    @Override
    public void onPotionBuild(Entity entity) {

    }

    @Override
    public void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {
        VFXManager.triggerVFXPacket(new TrailVPacket(), null, null, Sound.BLOCK_ANVIL_LAND, thrownPotion, Effect.CRIT, -1f, -1f, -1f);
    }

    @Override
    public void onPotionHit(Location location, ThrownPotion thrownPotion) {
        // push entities
        EntityUtil.pushAway(location, range, 0.5);
        // push blocks

        // row rad = 0
        spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN));

        // row rad = 1
        new BukkitRunnable() {
            @Override
            public void run() {
                for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST,}) {
                    spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN).getRelative(face));
                }
            }
        }.runTaskLater(instance(), 3);

        // row rad = 2
        new BukkitRunnable() {
            @Override
            public void run() {

                for (BlockFace face : BlockFace.values()) {
                    if (face == BlockFace.UP || face == BlockFace.DOWN) {
                        continue;
                    }

                    spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN).getRelative(face));
                }
            }
        }.runTaskLater(instance(), 5);

        // row rad = 3
        new BukkitRunnable() {
            @Override
            public void run() {
                for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.EAST,
                        BlockFace.SOUTH, BlockFace.WEST,}) {
                    spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN).getRelative(face, 2));
                    spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN).getRelative(face, 3));
                }

                for (BlockFace face : new BlockFace[]{BlockFace.NORTH_EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST, BlockFace.NORTH_WEST}) {
                    spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN).getRelative(face, 2));
                }

                for (BlockFace face : new BlockFace[]{BlockFace.NORTH_NORTH_EAST, BlockFace.NORTH_NORTH_WEST, BlockFace.EAST_NORTH_EAST,
                        BlockFace.EAST_SOUTH_EAST, BlockFace.WEST_NORTH_WEST, BlockFace.WEST_SOUTH_WEST, BlockFace.SOUTH_SOUTH_EAST,
                        BlockFace.SOUTH_SOUTH_WEST, BlockFace.WEST_SOUTH_WEST, BlockFace.WEST_NORTH_WEST}) {
                    spawnFallingBlock(location.getBlock().getRelative(BlockFace.DOWN).getRelative(face, 1));
                }
            }
        }.runTaskLater(instance(), 7);
    }

    private FallingBlock spawnFallingBlock(Block block) {
        FallingBlock fb = block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
        fb.setVelocity(new Vector(0, 0.5, 0));
        fb.setDropItem(true);
        block.setType(Material.AIR);
        return fb;
    }

    @EventHandler
    public void onFallingBlockPlace(EntityChangeBlockEvent event) {
        event.getBlock().breakNaturally();
//        event.setCancelled(true);
        event.getBlock().setType(Material.AIR);
    }
}
