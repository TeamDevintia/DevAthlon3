package io.github.teamdevintia.magicpotions.command;

import io.github.teamdevintia.magicpotions.MagicPotions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Handles registering of the commands
 *
 * @author Shad0wCore, MiniDigger
 */
public class CommandHandler {

    private MagicPotions instance;
    private Map<String, MagicCommand> commandMap;

    public CommandHandler(MagicPotions instance) {
        this.instance = instance;
        this.commandMap = new HashMap<>();
    }

    /**
     * Registers the command into the command map
     *
     * @param magicCommand the command to register
     */
    public void registerCommand(MagicCommand magicCommand) {
        commandMap.put(magicCommand.getName(), magicCommand);
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(magicCommand.getName(), magicCommand);
    }

    /**
     * registers all known commands from this plugin into the helpmap
     */
    public void registerHelp() {
        Set<HelpTopic> help = new TreeSet<>(HelpTopicComparator.helpTopicComparatorInstance());
        commandMap.keySet().stream().filter(commandLabel -> !commandLabel.contains(".")).forEach(commandLabel -> {
            Command cmd = ((CraftServer) Bukkit.getServer()).getCommandMap().getCommand(commandLabel);
            HelpTopic topic = new GenericCommandHelpTopic(cmd);
            help.add(topic);
        });
        IndexHelpTopic topic = new IndexHelpTopic(instance.getName(), "All commands for " + instance.getName(), instance.getName() + ".help", help,
                "Below is a list of all " + instance.getName() + " commands:");
        Bukkit.getServer().getHelpMap().addTopic(topic);
        help.forEach((t) -> Bukkit.getServer().getHelpMap().addTopic(t));
    }
}
