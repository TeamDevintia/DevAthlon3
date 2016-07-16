package io.github.teamdevintia.devathlon3.util;

import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

/**
 * Small util to get clockwise and counter-clockwise neighbors of block faces
 *
 * @author MiniDigger
 */
public final class DirectionUtil {

    private static final List<BlockFace> faces = new ArrayList<>(4);

    static {
        faces.add(BlockFace.NORTH);
        faces.add(BlockFace.EAST);
        faces.add(BlockFace.SOUTH);
        faces.add(BlockFace.WEST);
    }

    /**
     * gets the next {@link org.bukkit.block.BlockFace} in clockwise direction
     *
     * @param face the input
     * @return the next {@link org.bukkit.block.BlockFace} in clockwise direction
     */
    public static BlockFace clockwise(BlockFace face) {
        int index = faces.indexOf(face);

        if (index == -1) {
            throw new IllegalArgumentException("Invalid face " + face.name());
        }

        return faces.get(Math.floorMod(index + 1, 4));
    }

    /**
     * gets the next {@link org.bukkit.block.BlockFace} in counter-clockwise direction
     *
     * @param face the input
     * @return the next {@link org.bukkit.block.BlockFace} in counter-clockwise direction
     */
    public static BlockFace counterClockwise(BlockFace face) {
        int index = faces.indexOf(face);

        if (index == -1) {
            throw new IllegalArgumentException("Invalid face " + face.name());
        }

        return faces.get(Math.floorMod(index - 1, 4));
    }
}
