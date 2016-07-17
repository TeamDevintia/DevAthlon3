package io.github.teamdevintia.magicpotions.managers;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.visuals.VPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Plays VPacket effects
 *
 * @author Shad0wCore
 */
public final class VFXManager {

    /**
     * Plays a effect packet
     *
     * @param vPacket      the visual packet to play
     * @param to           the player it should be played to, null for global
     * @param location     the location it should be emitted from, null for global
     * @param optionalArgs defined by implementation
     */
    public static void triggerVFXPacket(VPacket vPacket, Player to, Location location, Object... optionalArgs) {
        vPacket.play(MagicPotions.getInstance(), location, to, optionalArgs);
    }

}
