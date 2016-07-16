package io.github.teamdevintia.devathlon3.util;

import org.bukkit.util.Vector;

/**
 * @author Shad0wCore
 */
public final class VectorUtil {

    /**
     * Generates a random vector
     * Subtracts from X and Z -0.5 otherwise the Vector wont work
     */
    public static Vector randomVector() {
        Vector vector = Vector.getRandom();
        vector.setX(vector.getX() - 0.5);
        vector.setZ(vector.getZ() - 0.5);

        return vector;
    }

}
