package io.github.teamdevintia.devathlon3.intern.listeners;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.intern.ListenerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author Shad0wCore
 */
public class WeatherChangeListener extends ListenerHandler {

    public WeatherChangeListener(Devathlon3 instance) {
        super(instance);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        // temporary
        event.setCancelled(true);
    }

}
