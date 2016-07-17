package io.github.teamdevintia.devathlon3.intern;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.commands.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Shad0wCore
 */
public class EventBus {

    private PluginManager pluginManager;
    private HashMap<String, HandlerList> handlerListHashMap;
    private Devathlon3 instance;
    private Map<String, CommandHandler> commandMap;

    public EventBus(Devathlon3 instance) {
        this.instance = instance;
        this.handlerListHashMap = new HashMap<>();
        this.pluginManager = Bukkit.getPluginManager();
        this.commandMap = new HashMap<>();
    }

    public void registerStaticEvent(Listener listener) {
        this.pluginManager.registerEvents(listener, this.instance);
    }

    //TODO clean up stuff later that we don't need

    public void registerStaticEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            this.registerStaticEvent(listener);
        }
    }

    public void linkEventMap(String identifier, Listener... listeners) throws IllegalArgumentException {
        if (this.handlerListHashMap.get(identifier) != null) {
            throw new IllegalArgumentException("HandlerList with ID: " + identifier + " has been already registered");
        }

        HandlerList handlerList = new HandlerList();

        for (Listener listener : listeners) {
            handlerList.register(new RegisteredListener(listener, (preListener, event) -> {
            }, EventPriority.NORMAL, this.instance, false));
            this.registerStaticEvent(listener);
        }

        this.handlerListHashMap.put(identifier, handlerList);
    }

    public void releaseEventSection(String identifier) throws IllegalArgumentException {
        if (this.handlerListHashMap.get(identifier) == null) {
            throw new IllegalArgumentException("HandlerList with ID: " + identifier + " is not registered");
        }

        for (RegisteredListener registeredListener : this.handlerListHashMap.get(identifier).getRegisteredListeners()) {
            HandlerList.unregisterAll(registeredListener.getListener());
        }
    }

    /**
     * Registers the command into the command map
     *
     * @param commandHandler the command to register
     */
    public void registerCommand(CommandHandler commandHandler) {
        commandMap.put(commandHandler.getName(), commandHandler);
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(commandHandler.getName(), commandHandler);
    }

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
