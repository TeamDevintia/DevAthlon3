package io.github.teamdevintia.magicpotions.visuals.throne;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.portal.LocationTuple;
import io.github.teamdevintia.magicpotions.visuals.VPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * @author Shad0wCore
 */
public class BloodTrailVPacket implements VPacket {

    @Override
    public void play(MagicPotions instance, Location location, Player toPlayer, Object... optionalArgs) {
        for (LocationTuple tuple : (List<LocationTuple>) optionalArgs[0]) {
            //calc direction
            Vector diff = tuple.loc2.toVector().subtract(tuple.loc1.toVector());
            double distance = diff.length();

            // calc step size
            double stepSize = 0.5;
            double dx = (diff.getX() / distance) * stepSize;
            double dy = (diff.getY() / distance) * stepSize;
            double dz = (diff.getZ() / distance) * stepSize;

            // 0.5 offset to center the particle
            Location temp = tuple.loc1.clone().add(0.5, 0.5, 0.5);
            // play the line
            for (double d = 0; d <= distance; d += stepSize) {
                temp.add(dx, dy, dz);

                temp.getWorld().playEffect(temp, Effect.TILE_BREAK, Material.REDSTONE_BLOCK.getId());
            }
        }

    }

}
