package io.github.teamdevintia.devathlon3.util;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

/**
 * Simple util to send message to nearby players
 *
 * @author MiniDigger
 */
public class ChatUtil {

    /**
     * Sends a message to all nearby player
     *
     * @param message the message to be send
     * @param range   the range in which players should receive that message
     * @param center  the center, from where the message is send
     * @return the amount of players that heard this message
     */
    public static long sendToPlayersInRange(String message, int range, Location center) {
        return center.getWorld().getNearbyEntities(center, range, range, range).stream().filter(entity -> entity.getType() == EntityType.PLAYER).peek(player -> player.sendMessage(message)).count();
    }

    /**
     * Sends a message to all nearby player
     *
     * @param message the message to be send
     * @param range   the range in which players should receive that message
     * @param center  the center, from where the message is send
     * @return the amount of players that heard this message
     */
    public static long sendToPlayersInRange(BaseComponent[] message, int range, Location center) {
        return center.getWorld().getNearbyEntities(center, range, range, range).stream().filter(entity -> entity.getType() == EntityType.PLAYER).peek(player -> ((Player) player).spigot().sendMessage(message)).count();
    }
}
