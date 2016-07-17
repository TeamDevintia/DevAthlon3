package io.github.teamdevintia.magicpotions;

import io.github.teamdevintia.magicpotions.constants.*;
import io.github.teamdevintia.magicpotions.entities.EvilWitch;
import io.github.teamdevintia.magicpotions.entities.WizardEntity;
import io.github.teamdevintia.magicpotions.command.CommandHandler;
import io.github.teamdevintia.magicpotions.command.commands.GiveItemCommand;
import io.github.teamdevintia.magicpotions.command.commands.GivePotionCommand;
import io.github.teamdevintia.magicpotions.listeners.MobSpawnListener;
import io.github.teamdevintia.magicpotions.listeners.WeatherChangeListener;
import io.github.teamdevintia.magicpotions.items.Blood;
import io.github.teamdevintia.magicpotions.items.Essence;
import io.github.teamdevintia.magicpotions.managers.PotionManager;
import io.github.teamdevintia.magicpotions.portal.MagicPortal;
import io.github.teamdevintia.magicpotions.util.NMSUtil;
import net.minecraft.server.v1_10_R1.EntityVillager;
import net.minecraft.server.v1_10_R1.EntityWitch;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

/**
 * Main class
 *
 * @author Shad0wCore
 * @author MiniDigger
 */
public final class MagicPotions extends JavaPlugin implements Listener {

    private static MagicPotions instance;

    private NameConstant nameConstant;
    private ItemConstant itemConstant;
    private RecipeConstant recipeConstant;
    private MessageConstant messageConstant;
    private TimingConstant timingConstant;

    private CommandHandler commandHandler;
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
        this.registerListeners();
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

    public CommandHandler getCommandHandler() {
        return this.commandHandler;
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

        this.commandHandler = new CommandHandler(this);

        this.potionManager = new PotionManager(this);
        this.potionManager.registerPotions();
    }

    private void initialization() {
        // Register wizard entity
        NMSUtil.registerEntity("Villager", 120, EntityVillager.class, WizardEntity.class);
        NMSUtil.registerEntity("Witch", 66, EntityWitch.class, EvilWitch.class);
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
        this.commandHandler.registerCommand(new GivePotionCommand(this, "givepotion",
                "Gives the specified player the specified potion", "/<command> <player> <potion>", Collections.singletonList("givepotion")));
        this.commandHandler.registerCommand(new GiveItemCommand(this, "giveitem",
                "Gives the specified player the specified item", "/<command> <player> <item>", Collections.singletonList("givepotion")));

        this.commandHandler.registerHelp();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(), this);
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
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

    public static MagicPotions getInstance() {
        return instance;
    }

}
