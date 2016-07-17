package io.github.teamdevintia.devathlon3.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * util that contains useful method for tab completion
 */
public final class TabCompleteUtil {

    /**
     * @return a list with the names of all online players
     */
    public static List<String> playerNames() {
        return Bukkit.getOnlinePlayers().stream().map((Function<Player, String>) HumanEntity::getName).collect(Collectors.toList());
    }

    /**
     * computes the list for tab completion
     *
     * @param list   the list with all possible inputs
     * @param prefix the prefix that was already entered
     * @return the computed list, ready to be thrown at Bukkit
     */
    public static List<String> complete(final Collection<String> list, final String prefix) {
        return list.stream().filter(s -> s.toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toList());
    }
}
