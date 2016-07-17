package io.github.teamdevintia.devathlon3;

import io.github.teamdevintia.devathlon3.commands.GivePotionCommand;
import io.github.teamdevintia.devathlon3.constants.*;
import io.github.teamdevintia.devathlon3.intern.EventBus;
import io.github.teamdevintia.devathlon3.items.Blood;
import io.github.teamdevintia.devathlon3.items.Essence;
import io.github.teamdevintia.devathlon3.managers.PotionManager;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import io.github.teamdevintia.devathlon3.portal.WizardEntity;
import io.github.teamdevintia.devathlon3.util.NMSUtil;
import net.minecraft.server.v1_10_R1.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Main class
 *
 * @author Shad0wCore
 * @author MiniDigger
 */
public final class Devathlon3 extends JavaPlugin implements Listener {

    private static Devathlon3 instance;

    private NameConstant nameConstant;
    private ItemConstant itemConstant;
    private RecipeConstant recipeConstant;
    private MessageConstant messageConstant;
    private TimingConstant timingConstant;

    private EventBus eventBus;
    private PotionManager potionManager;

    @Override
    public void onEnable() {
        this.instance = this;

        this.preInitialization();
        this.initialization();
        this.postInitialization();

        this.applyWorldSettings();
        this.applyWorldSettings();

        this.registerCommands();
    }

    @Override
    public void onDisable() {
        this.instance = null;
        this.nameConstant.cleanup();
        this.nameConstant = null;
        this.itemConstant.cleanup();
        this.itemConstant = null;
        this.recipeConstant.cleanup();
        this.recipeConstant = null;
        this.messageConstant.cleanup();
        this.messageConstant = null;
        this.timingConstant.cleanup();
        this.timingConstant = null;
    }

    public NameConstant getNameConstant() {
        return this.nameConstant;
    }

    public ItemConstant getItemConstant() {
        return this.itemConstant;
    }

    public RecipeConstant getRecipeConstant() {
        return this.recipeConstant;
    }

    public MessageConstant getMessageConstant() {
        return this.messageConstant;
    }

    public TimingConstant getTimingConstant() {
        return timingConstant;
    }

    public PotionManager getPotionManager() {
        return this.potionManager;
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    private void preInitialization() {
        this.nameConstant = new NameConstant(instance);
        this.itemConstant = new ItemConstant(instance);
        this.recipeConstant = new RecipeConstant(instance);
        this.messageConstant = new MessageConstant(instance);
        this.timingConstant = new TimingConstant(instance);

        this.nameConstant.initializeContent();
        this.itemConstant.initializeContent();
        this.recipeConstant.initializeContent();
        this.messageConstant.initializeContent();
        this.timingConstant.initializeContent();

        this.eventBus = new EventBus(this);

        this.potionManager = new PotionManager(this);
        this.potionManager.registerPotions();
    }

    private void initialization() {
        // Register wizard entity
        NMSUtil.registerEntity("Villager", 120, EntityVillager.class, WizardEntity.class);
        // events
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private void postInitialization() {
        // Initializes portals
        new MagicPortal(this);
        // Initializes blood drops
        new Blood(this);
        // Initializes essence drops
        new Essence(this);
    }

    private void registerCommands() {
        this.eventBus.registerCommand(new GivePotionCommand(this, "givepotion",
                "Gives the specified player the specified potion", "/<command> <player> <potion>", Arrays.asList("givepotion")));
    }

    private void applyWorldSettings() {
        // always day, always sunny!
        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setTime(6000);
            world.setThundering(false);
            world.setThunderDuration(0);
            world.setStorm(false);
            world.setWeatherDuration(1000000000);
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    public static Devathlon3 getInstance() {
        return instance;
    }

}
