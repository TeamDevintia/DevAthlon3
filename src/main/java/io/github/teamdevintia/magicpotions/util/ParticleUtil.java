package io.github.teamdevintia.magicpotions.util;

import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.Packet;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * util to spawn particles
 *
 * @author Shad0wCore
 */
public final class ParticleUtil {

    /**
     * Displays a effect to a single player with given arguments
     *
     * @param player   The player which is able to see the effect
     * @param location The location where the effect should be displayed
     * @param effect   The effect which will be displayed to the player
     * @param id       The item/block/data id for the effect
     * @param data     The data value of the block/item for the effect
     * @param offX     The amount to be randomly offset by in the X axis
     * @param offY     The amount to be randomly offset by in the Y axis
     * @param offZ     The amount to be randomly offset by in the Z axis
     * @param speed    The speed of the particles
     * @param count    The number of particles to be displayed
     * @param radius   The radius where the particles are visible
     */
    public static void playTo(Player player, Location location, Effect effect, int id, int data, float offX, float offY, float offZ, float speed, int count, int radius) {
        player.spigot().playEffect(location, effect, id, data, offX, offY, offZ, speed, count, radius);
    }

    /**
     * Displays colored particle to a single player with given arguments
     * Beside the fact that you are not able to set the radius when it comes to
     * colored particle(s) you can use the longDistance boolean to say the colored particle(s)
     * are visible after a distance of 16 blocks
     *
     * @param player          The player which is able to see the colored particle
     * @param longDistance    To set colored particle visible after a distance of 16 blocks
     * @param location        The location where the colored particle should be displayed
     * @param coloredParticle The type of colorable particle should be displayed
     * @param red             Float value of Red {@link ParticleUtil#floatRGB(int)}
     * @param green           Float value of Green {@link ParticleUtil#floatRGB(int)}
     * @param blue            Float value of Blue {@link ParticleUtil#floatRGB(int)}
     * @param brightness      Float value for the brightness
     */
    public static void playColorTo(Player player, boolean longDistance, Location location, ColoredParticle coloredParticle, float red, float green, float blue, float brightness) {
        switch (coloredParticle) {
            case REDDUST:
                sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, longDistance,
                        (float) location.getX(), (float) location.getY(), (float) location.getZ(), red, green, blue, brightness, 0));
                break;
            case MOBSPELL:
                sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.SPELL_MOB, true,
                        (float) location.getX(), (float) location.getY(), (float) location.getZ(), red, green, blue, brightness, 0));
                break;
            case MOBSPELL_AMBIENT:
                sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.SPELL_MOB_AMBIENT, true,
                        (float) location.getX(), (float) location.getY(), (float) location.getZ(), red, green, blue, brightness, 0));
                break;
        }
    }

    /**
     * Displays a icon crack (item break) to a single player with given arguments
     * The material and subID are representing the item.
     *
     * @param player        The player which is able to see the icon crack
     * @param longDistance  To set icon crack visible after a distance of 16 blocks
     * @param location      The location where the icon crack should be displayed
     * @param material      The material of the icon crack
     * @param subID         The subID of the icon crack
     * @param offX          The amount to be randomly offset by in the X axis
     * @param offY          The amount to be randomly offset by in the Y axis
     * @param offZ          The amount to be randomly offset by in the Z axis
     * @param speed         The speed of the particles
     * @param particleCount The number of particles to be displayed
     */
    public static void playIconCrackTo(Player player, boolean longDistance, Location location, Material material, int subID, float offX, float offY, float offZ, float speed, int particleCount) {
        playIconCrackTo(player, longDistance, location, material.getId(), subID, offX, offY, offZ, speed, particleCount);
    }

    /**
     * Displays a icon crack (item break) to a single player with given arguments
     * The id and subID are representing the item.
     *
     * @param player        The player which is able to see the icon crack
     * @param longDistance  To set icon crack visible after a distance of 16 blocks
     * @param location      The location where the icon crack should be displayed
     * @param id            The item id of the icon crack
     * @param subID         The subID of the icon crack
     * @param offX          The amount to be randomly offset by in the X axis
     * @param offY          The amount to be randomly offset by in the Y axis
     * @param offZ          The amount to be randomly offset by in the Z axis
     * @param speed         The speed of the particles
     * @param particleCount The number of particles to be displayed
     */
    public static void playIconCrackTo(Player player, boolean longDistance, Location location, int id, int subID, float offX, float offY, float offZ, float speed, int particleCount) {
        sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.ITEM_CRACK, longDistance,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(), offX, offY, offZ, speed, particleCount, id, subID));
    }

    /**
     * Displays a block crack (block break) to a single player with given arguments
     * The material and subID are representing the block.
     *
     * @param player        The player which is able to see the block crack
     * @param longDistance  To set icon crack visible after a distance of 16 blocks
     * @param location      The location where the block crack should be displayed
     * @param material      The material of the block crack
     * @param subID         The subID of the block crack
     * @param offX          The amount to be randomly offset by in the X axis
     * @param offY          The amount to be randomly offset by in the Y axis
     * @param offZ          The amount to be randomly offset by in the Z axis
     * @param speed         The speed of the particles
     * @param particleCount The number of particles to be displayed
     */
    public static void playBlockCrackTo(Player player, boolean longDistance, Location location, Material material, int subID, float offX, float offY, float offZ, float speed, int particleCount) {
        playBlockCrackTo(player, longDistance, location, material.getId(), subID, offX, offY, offZ, speed, particleCount);
    }

    /**
     * Displays a block crack (block break) to a single player with given arguments
     * The id and subID are representing the block.
     *
     * @param player        The player which is able to see the block crack
     * @param longDistance  To set icon crack visible after a distance of 16 blocks
     * @param location      The location where the block crack should be displayed
     * @param id            The item id of the block crack
     * @param subID         The subID of the block crack
     * @param offX          The amount to be randomly offset by in the X axis
     * @param offY          The amount to be randomly offset by in the Y axis
     * @param offZ          The amount to be randomly offset by in the Z axis
     * @param speed         The speed of the particles
     * @param particleCount The number of particles to be displayed
     */
    public static void playBlockCrackTo(Player player, boolean longDistance, Location location, int id, int subID, float offX, float offY, float offZ, float speed, int particleCount) {
        sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.BLOCK_CRACK, longDistance,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(), offX, offY, offZ, speed, particleCount, subID * 4096 + id));
    }

    /**
     * Displays block dust (when someone mines a block and those particles fall down or basically
     * particles are affected by gravity) to a single player with given arguments
     * The material and subID are representing the block.
     *
     * @param player        The player which is able to see the block dust
     * @param longDistance  To set block dust visible after a distance of 16 blocks
     * @param location      The location where the block dust should be displayed
     * @param material      The material of the block dust
     * @param subID         The subID of the block dust
     * @param offX          The amount to be randomly offset by in the X axis
     * @param offY          The amount to be randomly offset by in the Y axis
     * @param offZ          The amount to be randomly offset by in the Z axis
     * @param speed         The speed of the particles
     * @param particleCount The number of particles to be displayed
     */
    public static void playBlockDustTo(Player player, boolean longDistance, Location location, Material material, int subID, float offX, float offY, float offZ, float speed, int particleCount) {
        playBlockDustTo(player, longDistance, location, material.getId(), subID, offX, offY, offZ, speed, particleCount);
    }

    /**
     * Displays block dust (when someone mines a block and those particles fall down or basically
     * particles are affected by gravity) to a single player with given arguments
     * The material and subID are representing the block.
     *
     * @param player        The player which is able to see the block dust
     * @param longDistance  To set block dust visible after a distance of 16 blocks
     * @param location      The location where the block dust should be displayed
     * @param id            The material of the block dust
     * @param subID         The subID of the block dust
     * @param offX          The amount to be randomly offset by in the X axis
     * @param offY          The amount to be randomly offset by in the Y axis
     * @param offZ          The amount to be randomly offset by in the Z axis
     * @param speed         The speed of the particles
     * @param particleCount The number of particles to be displayed
     */
    public static void playBlockDustTo(Player player, boolean longDistance, Location location, int id, int subID, float offX, float offY, float offZ, float speed, int particleCount) {
        sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.BLOCK_DUST, longDistance,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(), offX, offY, offZ, speed, particleCount, subID * 4096 + id));
    }

    /**
     * Displays globally a effect
     * {@link #playTo(Player, Location, Effect, int, int, float, float, float, float, int, int)}
     */
    public static void play(Location location, Effect effect, int id, int data, float offX, float offY, float offZ, float speed, int count, int radius) {
        location.getWorld().spigot().playEffect(location, effect, id, data, offX, offY, offZ, speed, count, radius);
    }

    /**
     * Displays globally a colored particle
     * {@link #playColorTo(Player, boolean, Location, ColoredParticle, float, float, float, float)}
     */
    public static void playColor(Location location, boolean longDistance, ColoredParticle coloredParticle, float red, float green, float blue, float brightness) {
        switch (coloredParticle) {
            case REDDUST:
                for (Player player : Bukkit.getOnlinePlayers())
                    playColorTo(player, longDistance, location, coloredParticle, red, green, blue, brightness);
                break;
            case MOBSPELL:
                for (Player player : Bukkit.getOnlinePlayers())
                    playColorTo(player, longDistance, location, coloredParticle, red, green, blue, brightness);
                break;
            case MOBSPELL_AMBIENT:
                for (Player player : Bukkit.getOnlinePlayers())
                    playColorTo(player, longDistance, location, coloredParticle, red, green, blue, brightness);
                break;
        }
    }

    /**
     * Displays globally a icon crack
     * {@link #playIconCrack(Location, boolean, Material, int, float, float, float, float, int)}
     */
    public static void playIconCrack(Location location, boolean longDistance, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playIconCrackTo(player, longDistance, location, material, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    /**
     * Displays globally a icon crack
     * {@link #playIconCrack(Location, boolean, int, int, float, float, float, float, int)}
     */
    public static void playIconCrack(Location location, boolean longDistance, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playIconCrackTo(player, longDistance, location, id, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    /**
     * Displays globally a block crack
     * {@link #playBlockCrackTo(Player, boolean, Location, Material, int, float, float, float, float, int)}
     */
    public static void playBlockCrack(Location location, boolean longDistance, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockCrackTo(player, longDistance, location, material, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    /**
     * Displays globally a block crack
     * {@link #playBlockCrackTo(Player, boolean, Location, int, int, float, float, float, float, int)}
     */
    public static void playBlockCrack(Location location, boolean longDistance, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockCrackTo(player, longDistance, location, id, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    /**
     * Displays globally block dust
     * {@link #playBlockDustTo(Player, boolean, Location, Material, int, float, float, float, float, int)}
     */
    public static void playBlockDust(Location location, boolean longDistance, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockDustTo(player, longDistance, location, material, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    /**
     * Displays globally block dust
     * {@link #playBlockDustTo(Player, boolean, Location, int, int, float, float, float, float, int)}
     */
    public static void playBlockDust(Location location, boolean longDistance, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockDustTo(player, longDistance, location, id, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    /**
     * Returns a float value from the given RGB value
     * floatRGB(120) would return for instance the red value
     * of a green in a float value which can be used by Minecraft OpenGL
     * <p>
     * Using normal RGB at {@link #playColor(Location, boolean, ColoredParticle, float, float, float, float)} or
     * {@link #playColorTo(Player, boolean, Location, ColoredParticle, float, float, float, float)} would not work
     * due the fact that the colored particle will have a random color
     *
     * @param rgbValue Single RGB value
     */

    public static float floatRGB(int rgbValue) {
        return (float) (rgbValue / 255.0);
    }

    private static void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.a().sendPacket(packet);
    }

    /**
     * Particle types which are able to be displayed with colors
     */
    public enum ColoredParticle {

        REDDUST,
        MOBSPELL,
        MOBSPELL_AMBIENT

    }

}
