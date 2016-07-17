package io.github.teamdevintia.devathlon3.managers;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.visuals.VPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public final class VFXManager {

    public static void triggerVFXPacket(VPacket vPacket, Player to, Location location, Object... optionalArgs) {
        vPacket.play(Devathlon3.getInstance(), location, to, optionalArgs);
    }

}
