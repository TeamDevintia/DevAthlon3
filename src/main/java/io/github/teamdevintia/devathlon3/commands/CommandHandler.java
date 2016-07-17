package io.github.teamdevintia.devathlon3.commands;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Shad0wCore
 */
public abstract class CommandHandler extends BukkitCommand {
    private Devathlon3 instance;

    public CommandHandler(Devathlon3 instance, String name) {
        super(name);
        this.instance = instance;
    }

    public CommandHandler(Devathlon3 instance, String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
        this.instance = instance;
    }

    public final Devathlon3 instance() {
        return this.instance;
    }

    public final Player fromCommandSender(CommandSender commandSender) {
        return ((Player) commandSender);
    }

    public final boolean isNotCommandSender(CommandSender commandSender) {
        return commandSender instanceof Player;
    }


}
