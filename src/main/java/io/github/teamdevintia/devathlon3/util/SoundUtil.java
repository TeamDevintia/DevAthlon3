package io.github.teamdevintia.devathlon3.util;

import io.github.teamdevintia.devathlon3.enums.SoundSource;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_10_R1.Packet;
import net.minecraft.server.v1_10_R1.PacketDataSerializer;
import net.minecraft.server.v1_10_R1.PacketPlayOutCustomPayload;
import net.minecraft.server.v1_10_R1.PacketPlayOutCustomSoundEffect;
import net.minecraft.server.v1_10_R1.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_10_R1.SoundCategory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.CraftSound;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class SoundUtil {

    /**
     * Plays a sound from a custom resource pack to a single player with given arguments
     *
     * @param player    The player which will be able to hear the sound
     * @param soundPath The path to the sound
     * @param source    The sound category
     * @param volume    The volume of the sound
     * @param pitch     The pitch of the sound
     */
    public static void playCustomSoundTo(Player player, String soundPath, SoundSource source, float volume, float pitch) {
        sendPacket(player, new PacketPlayOutCustomSoundEffect(soundPath, SoundCategory.a(source.source()),
                player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), volume, pitch));
    }

    /**
     * Plays a sound from a custom resource pack at a location to a single player with given arguments
     *
     * @param player        The player which will be able to hear the sound
     * @param soundPath     The path to the sound
     * @param source        The sound category
     * @param volume        The volume of the sound
     * @param pitch         The pitch of the sound
     * @param soundLocation The location where the sound will be played at
     */
    public static void playCustomSoundTo(Player player, String soundPath, SoundSource source, float volume, float pitch, Location soundLocation) {
        sendPacket(player, new PacketPlayOutCustomSoundEffect(soundPath, SoundCategory.a(source.source()),
                soundLocation.getX(), soundLocation.getY(), soundLocation.getZ(), volume, pitch));
    }

    /**
     * Plays a sound to a single player with given arguments
     *
     * @param player The player which will be able to hear the sound
     * @param sound  The sound which will be played
     * @param source The sound category
     * @param volume The volume of the sound
     * @param pitch  The pitch of the sound
     */
    public static void playSoundTo(Player player, Sound sound, SoundSource source, float volume, float pitch) {
        sendPacket(player, new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect(CraftSound.getSound(sound)),
                SoundCategory.a(source.source()), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), volume, pitch));
    }

    /**
     * Plays a sound at a location to a single player with given arguments
     *
     * @param player        The player which will be able to hear the sound
     * @param sound         The sound which will be played
     * @param source        The sound category
     * @param volume        The volume of the sound
     * @param pitch         The pitch of the sound
     * @param soundLocation The location where the sound will be played at
     */
    public static void playSoundTo(Player player, Sound sound, SoundSource source, float volume, float pitch, Location soundLocation) {
        sendPacket(player, new PacketPlayOutNamedSoundEffect(CraftSound.getSoundEffect(CraftSound.getSound(sound)),
                SoundCategory.a(source.source()), soundLocation.getX(), soundLocation.getY(), soundLocation.getZ(), volume, pitch));
    }

    /**
     * Plays a sound from a custom resource pack globally
     * Only players with the same custom resource pack are able to
     * hear the sound.
     * <p>
     * {@link #playCustomSoundTo(Player, String, SoundSource, float, float)}
     */
    public static void playCustomSound(String soundPath, SoundSource source, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers())
            playCustomSoundTo(player, soundPath, source, volume, pitch);
    }

    /**
     * Plays a sound from a custom resource pack globally at a location
     * Only players with the same custom resource pack are able to
     * hear the sound.
     * <p>
     * {@link #playCustomSoundTo(Player, String, SoundSource, float, float, Location)}
     */
    public static void playCustomSound(String soundPath, SoundSource source, float volume, float pitch, Location soundLocation) {
        for (Player player : Bukkit.getOnlinePlayers())
            playCustomSoundTo(player, soundPath, source, volume, pitch, soundLocation);
    }

    /**
     * Plays a sound globally
     * {@link #playSoundTo(Player, Sound, SoundSource, float, float)}
     */
    public static void playSound(Sound sound, SoundSource source, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers())
            playSoundTo(player, sound, source, volume, pitch);
    }

    /**
     * Plays a sound globally at a location
     * {@link #playSoundTo(Player, Sound, SoundSource, float, float, Location)}
     */
    public static void playSound(Sound sound, SoundSource source, float volume, float pitch, Location soundLocation) {
        for (Player player : Bukkit.getOnlinePlayers())
            playSoundTo(player, sound, source, volume, pitch, soundLocation);
    }

    /**
     * Cancel a sound which is currently playing to a player
     *
     * @param player The player where the sound is going to be cancelled
     * @param sound  The sound which is playing right now
     * @param source The sound category of the sound
     */
    public static void cancelPlayingSound(Player player, Sound sound, SoundSource source) {
        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(source.source());
        packetDataSerializer.a(CraftSound.getSound(sound));

        sendPacket(player, new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
    }

    /**
     * Cancel a sound which is currently playing to a player
     *
     * @param player    The player where the sound is going to be cancelled
     * @param soundPath The sound path of the sound which is playing right now
     * @param source    The sound category of the sound
     */
    public static void cancelPlayingSound(Player player, String soundPath, SoundSource source) {
        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(source.source());
        packetDataSerializer.a(soundPath);

        sendPacket(player, new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
    }

    /**
     * Cancel currently to player played sounds
     *
     * @param player The player where the sounds are going to be cancelled
     * @param source The sound category
     */
    public static void cancelPlayingSound(Player player, SoundSource source) {
        PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
        packetDataSerializer.a(source.source());
        packetDataSerializer.a("");

        sendPacket(player, new PacketPlayOutCustomPayload("MC|StopSound", packetDataSerializer));
    }

    /**
     * Cancel a specific sound globally
     * {@link #cancelPlayingSound(Player, Sound, SoundSource)}
     */
    public static void cancelPlayingSounds(Sound sound, SoundSource source) {
        for (Player player : Bukkit.getOnlinePlayers())
            cancelPlayingSound(player, sound, source);
    }

    /**
     * Cancel a specific sound by path globally
     * {@link #cancelPlayingSound(Player, String, SoundSource)}
     */
    public static void cancelPlayingSounds(String soundPath, SoundSource source) {
        for (Player player : Bukkit.getOnlinePlayers())
            cancelPlayingSound(player, soundPath, source);
    }

    /**
     * Cancel all sounds played to players globally
     * {@link #cancelPlayingSound(Player, SoundSource)}
     */
    public static void cancelPlayingSoundsGlobal(SoundSource source) {
        for (Player player : Bukkit.getOnlinePlayers())
            cancelPlayingSound(player, source);
    }

    private static void sendPacket(Player player, Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.a().sendPacket(packet);
    }

}
