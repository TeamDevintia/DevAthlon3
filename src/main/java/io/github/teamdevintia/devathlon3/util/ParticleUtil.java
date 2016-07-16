package io.github.teamdevintia.devathlon3.util;

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
 * @author Shad0wCore
 */
public final class ParticleUtil {

    public static void playTo(Player player, Location location, Effect effect, int id, int data, float offX, float offY, float offZ, float speed, int count, int radius) {
        player.spigot().playEffect(location, effect, id, data, offX, offY, offZ, speed, count, radius);
    }

    public static  void playColorTo(Player player, boolean longDistance, Location location, ColoredParticle coloredParticle, float red, float green, float blue, float brightness) {
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

    public static  void playIconCrackTo(Player player, boolean longDistance, Location location, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        playIconCrackTo(player, longDistance, location, material.getId(), subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playIconCrackTo(Player player, boolean longDistance, Location location, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.ITEM_CRACK, longDistance,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, particleCount, id, subID));
    }

    public static  void playBlockCrackTo(Player player, boolean longDistance, Location location, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        playBlockCrackTo(player, longDistance, location, material.getId(), subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playBlockCrackTo(Player player, boolean longDistance, Location location, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.BLOCK_CRACK, longDistance,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, particleCount, subID * 4096 + id));
    }

    public static  void playBlockDustTo(Player player, boolean longDistance, Location location, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        playBlockDustTo(player, longDistance, location, material.getId(), subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playBlockDustTo(Player player, boolean longDistance, Location location, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        sendPacket(player, new PacketPlayOutWorldParticles(EnumParticle.BLOCK_DUST, longDistance,
                (float) location.getX(), (float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, particleCount, subID * 4096 + id));
    }

    public static  void play(Location location, Effect effect, int id, int data, float offX, float offY, float offZ, float speed, int count, int radius) {
        location.getWorld().spigot().playEffect(location, effect, id, data, offX, offY, offZ, speed, count, radius);
    }

    public static  void playColor(Location location, boolean longDistance, ColoredParticle coloredParticle, float red, float green, float blue, float brightness) {
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

    public static  void playIconCrack(Location location, boolean longDistance, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playIconCrackTo(player, longDistance, location, material, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playIconCrack(Location location, boolean longDistance, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playIconCrackTo(player, longDistance, location, id, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playBlockCrack(Location location, boolean longDistance, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockCrackTo(player, longDistance, location, material, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playBlockCrack(Location location, boolean longDistance, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockCrackTo(player, longDistance, location, id, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playBlockDust(Location location, boolean longDistance, Material material, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockDustTo(player, longDistance, location, material, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  void playBlockDust(Location location, boolean longDistance, int id, int subID, float offsetX, float offsetY, float offsetZ, float speed, int particleCount) {
        for (Player player : Bukkit.getOnlinePlayers())
            playBlockDustTo(player, longDistance, location, id, subID, offsetX, offsetY, offsetZ, speed, particleCount);
    }

    public static  float floatRGB(int rgbValue) {
        return (float) (rgbValue / 255.0);
    }

    private static  void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.a().sendPacket(packet);
    }

    enum ColoredParticle {

        REDDUST,
        MOBSPELL,
        MOBSPELL_AMBIENT

    }

}
