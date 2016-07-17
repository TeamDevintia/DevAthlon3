package io.github.teamdevintia.devathlon3.intern;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.commands.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

import java.util.HashMap;

/**
 * @author Shad0wCore
 */
public class EventBus {

    private PluginManager pluginManager;
    private HashMap<String, HandlerList> handlerListHashMap;
    private Devathlon3 instance;

    public EventBus(Devathlon3 instance) {
        this.instance = instance;
        this.handlerListHashMap = new HashMap<>();
        this.pluginManager = Bukkit.getPluginManager();
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

    public void registerCommand(CommandHandler commandHandler) {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register(commandHandler.getName(), commandHandler);
    }

}
