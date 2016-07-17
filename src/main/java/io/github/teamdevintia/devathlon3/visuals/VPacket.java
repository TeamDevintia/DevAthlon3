package io.github.teamdevintia.devathlon3.visuals;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Shad0wCore
 */
public interface VPacket {

    void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs);

}
