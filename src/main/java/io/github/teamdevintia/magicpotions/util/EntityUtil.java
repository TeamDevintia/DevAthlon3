package io.github.teamdevintia.magicpotions.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

/**
 * util for common entity related methods
 *
 * @author MiniDigger
 */
public final class EntityUtil {

    /**
     * Pushes all entities in a certain radius away from one point
     *
     * @param center the center point
     * @param range  the range in which entities should be affected
     * @param force  the force that should be applied
     */
    public static void pushAway(Location center, double range, double force) {
        Vector centerV = center.toVector();
        for (Entity entity : center.getWorld().getNearbyEntities(center, range, range, range)) {
            Vector entityV = entity.getLocation().toVector();
            Vector velocity = entityV.subtract(centerV);
            velocity.setX(velocity.getX() + 0.5);
            velocity.setZ(velocity.getZ() + 0.5);
            velocity = velocity.multiply(force);
            velocity.setY(0.5);
            entity.setVelocity(velocity);
        }
    }
}
