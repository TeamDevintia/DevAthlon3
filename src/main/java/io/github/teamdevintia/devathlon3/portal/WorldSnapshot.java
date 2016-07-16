package io.github.teamdevintia.devathlon3.portal;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Takes a snapshot if a area and restores it later
 */
public class WorldSnapshot {

    private List<BlockState> blocks = new ArrayList<>();

    /**
     * Takes the snapshot
     *
     * @param center  the center location
     * @param range   the range around the center that should be snapshotted
     * @param yOffset the amount of blocks we should go up
     * @param ignore  materials that should be ignore
     */
    public WorldSnapshot(Location center, int range, int yOffset, Material... ignore) {
        int fromX;
        int toX;
        if (center.getX() > 0) {
            fromX = center.getBlockX() - range;
            toX = center.getBlockX() + range;
        } else {
            fromX = center.getBlockX() + range;
            toX = center.getBlockX() - range;
        }

        int fromZ;
        int toZ;
        if (center.getZ() > 0) {
            fromZ = center.getBlockZ() - range;
            toZ = center.getBlockZ() + range;
        } else {
            fromZ = center.getBlockZ() + range;
            toZ = center.getBlockZ() - range;
        }

        int fromY = center.getBlockY();
        int toY = center.getBlockY() + yOffset;

        for (int x = fromX; x < toX; x++) {
            for (int y = fromY; y < toY; y++) {
                for (int z = fromZ; z < toZ; z++) {
                    BlockState state = center.getWorld().getBlockAt(x, y, z).getState();
                    if (!ArrayUtils.contains(ignore, state.getType())) {
                        blocks.add(state);
                    }
                }
            }
        }
    }

    /**
     * Restores the area
     *
     * @return the amount of blocks restored
     */
    public long restore() {
        return blocks.stream().peek(blockState -> blockState.update(true, true)).count();
    }

    /**
     * Restores the area which ignoring a few blocks
     *
     * @param ignore the blocks to ignore
     * @return the amount of blocks restored
     */
    public long restore(Material... ignore) {
        return blocks.stream().filter(blockState -> !ArrayUtils.contains(ignore, blockState.getType())).peek(blockState -> blockState.update(true, true)).count();
    }

    /**
     * Restores the blocks slowly
     *
     * @param time  the time (in ticks) it should take
     * @param steps the amount steps that should be done
     */
    public void restoreSlowly(double time, double steps) {
        double ticks = time / steps;
        double perIt = blocks.size() / steps;

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < perIt; i++) {
                    if (blocks.size() - 1 <= 0) {
                        cancel();
                        return;
                    }
                    int random = ThreadLocalRandom.current().nextInt(blocks.size() - 1);
                    BlockState state = blocks.remove(random);
                    if (state != null) {
                        state.update(true, true);
                    } else {
                        // restore rest
                        for (BlockState s : blocks) {
                            s.update(true, true);
                        }
                        cancel();
                        return;
                    }
                }
            }
        }.runTaskTimer(Devathlon3.getInstance(), 0, (long) ticks);
    }
}
