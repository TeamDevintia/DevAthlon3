package io.github.teamdevintia.magicpotions;

import io.github.teamdevintia.magicpotions.util.DirectionUtil;
import org.bukkit.block.BlockFace;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for the direction util class
 *
 * @author MiniDigger
 */
public class DirectionUtilTest {

    @Test
    public void testClockwise() {
        assertThat(DirectionUtil.clockwise(BlockFace.NORTH), is(BlockFace.EAST));
        assertThat(DirectionUtil.clockwise(BlockFace.EAST), is(BlockFace.SOUTH));
        assertThat(DirectionUtil.clockwise(BlockFace.SOUTH), is(BlockFace.WEST));
        assertThat(DirectionUtil.clockwise(BlockFace.WEST), is(BlockFace.NORTH));
    }


    @Test
    public void testCounterClockwise() {
        assertThat(DirectionUtil.counterClockwise(BlockFace.NORTH), is(BlockFace.WEST));
        assertThat(DirectionUtil.counterClockwise(BlockFace.WEST), is(BlockFace.SOUTH));
        assertThat(DirectionUtil.counterClockwise(BlockFace.SOUTH), is(BlockFace.EAST));
        assertThat(DirectionUtil.counterClockwise(BlockFace.EAST), is(BlockFace.NORTH));
    }
}
