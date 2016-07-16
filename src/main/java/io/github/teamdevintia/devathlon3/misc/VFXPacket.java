package io.github.teamdevintia.devathlon3.misc;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
@Deprecated
public interface VFXPacket {

    void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs);

}
