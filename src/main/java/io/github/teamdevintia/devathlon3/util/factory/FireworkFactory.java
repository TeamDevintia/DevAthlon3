package io.github.teamdevintia.devathlon3.util.factory;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Shad0wCore
 *         <p>
 *         Simple factory to create efficiently fireworks
 */
public final class FireworkFactory {

    private Location location = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    private List<FireworkEffect> effectArrayList = new ArrayList<>();
    private boolean instantDetonate = false;
    private int fireworkPower = 1;

    /**
     * Sets the location where the firework will spawn
     *
     * @param location The location for the spawn point of the firework
     */
    public FireworkFactory location(Location location) {
        this.location = location;
        return this;
    }

    /**
     * Applies a firework effect which will be displayed when
     * the firework detonates
     *
     * @param fireworkEffect The firework effect to apply
     */
    public FireworkFactory effect(FireworkEffect fireworkEffect) {
        effectArrayList.add(fireworkEffect);
        return this;
    }

    /**
     * Applies multiple firework effects which are going to be displayed
     * when the firework detonates
     *
     * @param fireworkEffects The firework effects to apply
     */
    public FireworkFactory effects(FireworkEffect... fireworkEffects) {
        effectArrayList.addAll(Arrays.asList(fireworkEffects));
        return this;
    }

    /**
     * Sets the power of the firework
     *
     * @param fireworkPower The firework power
     */
    public FireworkFactory power(int fireworkPower) {
        this.fireworkPower = fireworkPower;
        return this;
    }

    /**
     * When used, the firework will detonate immediately after spawn
     */
    public FireworkFactory instantDetonate(boolean instantDetonate) {
        this.instantDetonate = instantDetonate;
        return this;
    }

    /**
     * Launches the finished firework
     *
     * @return The finished firework
     */
    public Firework launch() {

        Firework firework = this.location.getWorld().spawn(this.location, Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffects(this.effectArrayList);
        fireworkMeta.setPower(this.fireworkPower);
        firework.setFireworkMeta(fireworkMeta);

        if (this.instantDetonate) {
            ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
            executorService.scheduleWithFixedDelay(firework::detonate, 0, 50, TimeUnit.MILLISECONDS);
        }

        return firework;
    }

    /**
     * Initializes a new instance of FireworkFactory
     * */
    public FireworkFactory rebind() {
        this.location = new Location(Bukkit.getWorld(""), 0, 0, 0);
        this.effectArrayList = new ArrayList<>();
        this.instantDetonate = false;
        this.fireworkPower = 1;
        return this;
    }

}
