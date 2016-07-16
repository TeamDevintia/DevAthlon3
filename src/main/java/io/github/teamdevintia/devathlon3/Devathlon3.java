package io.github.teamdevintia.devathlon3;

import io.github.teamdevintia.devathlon3.constants.*;
import io.github.teamdevintia.devathlon3.items.Blood;
import io.github.teamdevintia.devathlon3.items.Essence;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import io.github.teamdevintia.devathlon3.portal.WizardEntity;
import io.github.teamdevintia.devathlon3.util.NMSUtil;
import net.minecraft.server.v1_10_R1.EntityVillager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class
 *
 * @author Shad0wCore
 * @author MiniDigger
 */
public final class Devathlon3 extends JavaPlugin {

    private static Devathlon3 instance;
    private NameConstant nameConstant;
    private ItemConstant itemConstant;
    private RecipeConstant recipeConstant;
    private MessageConstant messageConstant;
    private TimingConstant timingConstant;

    @Override
    public void onEnable() {
        instance = this;

        nameConstant = new NameConstant(instance);
        itemConstant = new ItemConstant(instance);
        recipeConstant = new RecipeConstant(instance);
        messageConstant = new MessageConstant(instance);
        timingConstant = new TimingConstant(instance);

        nameConstant.initializeContent();
        itemConstant.initializeContent();
        recipeConstant.initializeContent();
        messageConstant.initializeContent();
        timingConstant.initializeContent();

        // register wizard
        NMSUtil.registerEntity("Villager", 120, EntityVillager.class, WizardEntity.class);

        // init portals
        new MagicPortal(this);
        // blood drop
        new Blood(this);
        // essence drop
        new Essence(this);

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

    @Override
    public void onDisable() {
        instance = null;
        nameConstant.cleanup();
        nameConstant = null;
        itemConstant.cleanup();
        itemConstant = null;
        recipeConstant.cleanup();
        recipeConstant = null;
        messageConstant.cleanup();
        messageConstant = null;
        timingConstant.cleanup();
        timingConstant = null;
    }

    public NameConstant getNameConstant() {
        return nameConstant;
    }

    public ItemConstant getItemConstant() {
        return itemConstant;
    }

    public RecipeConstant getRecipeConstant() {
        return recipeConstant;
    }

    public MessageConstant getMessageConstant() {
        return messageConstant;
    }

    public TimingConstant getTimingConstant() {
        return timingConstant;
    }

    public static Devathlon3 getInstance() {
        return instance;
    }

}
