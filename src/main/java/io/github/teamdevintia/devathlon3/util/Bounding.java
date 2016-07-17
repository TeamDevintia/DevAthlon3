package io.github.teamdevintia.devathlon3.util;

import org.bukkit.util.Vector;

/**
 * @author Glowstone++ Guys
 */
public final class Bounding implements Cloneable {

    private Vector minimalCorner = new Vector();
    private Vector maximalCorner = new Vector();

    public static boolean matches(Bounding firstBox, Bounding secondBox) {
        Vector minimalA = firstBox.minimalCorner, maximalA = firstBox.maximalCorner;
        Vector minimalB = secondBox.minimalCorner, maximalB = secondBox.maximalCorner;
        return maximalA.getX() >= minimalB.getX() && minimalA.getX() <= maximalB.getX() &&
                maximalA.getY() >= minimalB.getY() && minimalA.getY() <= maximalB.getY() &&
                maximalA.getZ() >= minimalB.getZ() && minimalA.getZ() <= maximalB.getZ();
    }

    public static Bounding givenCorners(Vector firstCorner, Vector secondCorner) {
        Bounding box = new Bounding();
        box.minimalCorner.setX(Math.min(firstCorner.getX(), secondCorner.getX()));
        box.minimalCorner.setY(Math.min(firstCorner.getY(), secondCorner.getY()));
        box.minimalCorner.setZ(Math.min(firstCorner.getZ(), secondCorner.getZ()));
        box.maximalCorner.setX(Math.max(firstCorner.getX(), secondCorner.getX()));
        box.maximalCorner.setY(Math.max(firstCorner.getY(), secondCorner.getY()));
        box.maximalCorner.setZ(Math.max(firstCorner.getZ(), secondCorner.getZ()));
        return box;
    }

    public static Bounding getCopy(Bounding original) {
        Bounding copiedBox = new Bounding();
        copiedBox.minimalCorner.copy(original.minimalCorner);
        copiedBox.maximalCorner.copy(original.maximalCorner);
        return copiedBox;
    }

    public Vector getSize() {
        return maximalCorner.clone().subtract(minimalCorner);
    }

    public Vector getMinimalCorner() {
        return minimalCorner;
    }

    public void setMinimalCorner(Vector minimalCorner) {
        this.minimalCorner = minimalCorner;
    }

    public Vector getMaximalCorner() {
        return maximalCorner;
    }

    public void setMaximalCorner(Vector maximalCorner) {
        this.maximalCorner = maximalCorner;
    }
}
