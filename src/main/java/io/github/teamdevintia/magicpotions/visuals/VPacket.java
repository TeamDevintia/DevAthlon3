package io.github.teamdevintia.magicpotions.visuals;

import io.github.teamdevintia.magicpotions.MagicPotions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * a VPacket is a particles effect that can be played to on or many players
 *
 * @author Shad0wCore
 */
public interface VPacket {

    void play(MagicPotions magicPotions, Location location, Player toPlayer, Object... optionalArgs);

}
