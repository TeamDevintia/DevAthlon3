package io.github.teamdevintia.devathlon3.portal;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;

import java.util.ArrayList;
import java.util.List;

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
}
