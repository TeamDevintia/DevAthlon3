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
 */
public final class FireworkFactory {

    private Location location = new Location(Bukkit.getWorld(""), 0, 0, 0);
    private List<FireworkEffect> effectArrayList = new ArrayList<>();
    private boolean instantDetonate = false;
    private int fireworkPower = 1;

    public FireworkFactory location(Location location) {
        this.location = location;
        return this;
    }

    public FireworkFactory effect(FireworkEffect fireworkEffect) {
        effectArrayList.add(fireworkEffect);
        return this;
    }

    public FireworkFactory effects(FireworkEffect... fireworkEffects) {
        effectArrayList.addAll(Arrays.asList(fireworkEffects));
        return this;
    }

    public FireworkFactory power(int fireworkPower) {
        this.fireworkPower = fireworkPower;
        return this;
    }

    public FireworkFactory instantDetonate(boolean instantDetonate) {
        this.instantDetonate = instantDetonate;
        return this;
    }

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

    public FireworkFactory rebind() {
        this.location = new Location(Bukkit.getWorld(""), 0, 0, 0);
        this.effectArrayList = new ArrayList<>();
        this.instantDetonate = false;
        this.fireworkPower = 1;
        return this;
    }

}
