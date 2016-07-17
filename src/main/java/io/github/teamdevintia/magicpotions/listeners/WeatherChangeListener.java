package io.github.teamdevintia.magicpotions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author Shad0wCore
 */
public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        // temporary
        event.setCancelled(true);
    }
}
