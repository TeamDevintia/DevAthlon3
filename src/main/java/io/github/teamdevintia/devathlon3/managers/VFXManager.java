package io.github.teamdevintia.devathlon3.managers;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.visuals.VFXPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class VFXManager {

    public static void triggerVFXPacket(VFXPacket vfxPacket, Player to, Location location, Object... optionalArgs) {
        vfxPacket.play(Devathlon3.getInstance(), location, to, optionalArgs);
    }

}
