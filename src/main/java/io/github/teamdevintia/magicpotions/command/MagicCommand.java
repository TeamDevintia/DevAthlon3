package io.github.teamdevintia.magicpotions.command;

import io.github.teamdevintia.magicpotions.MagicPotions;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Shad0wCore
 */
public abstract class MagicCommand extends BukkitCommand {
    private MagicPotions instance;

    public MagicCommand(MagicPotions instance, String name) {
        super(name);
        this.instance = instance;
    }

    public MagicCommand(MagicPotions instance, String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
        this.instance = instance;
    }

    public final MagicPotions instance() {
        return this.instance;
    }

    public final Player fromCommandSender(CommandSender commandSender) {
        return ((Player) commandSender);
    }

    public final boolean isNotCommandSender(CommandSender commandSender) {
        return commandSender instanceof Player;
    }


}
