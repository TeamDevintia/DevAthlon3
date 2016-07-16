package io.github.teamdevintia.devathlon3;

import io.github.teamdevintia.devathlon3.constants.ItemConstant;
import io.github.teamdevintia.devathlon3.constants.MessageConstant;
import io.github.teamdevintia.devathlon3.constants.NameConstant;
import io.github.teamdevintia.devathlon3.constants.RecipeConstant;
import io.github.teamdevintia.devathlon3.items.Blood;
import io.github.teamdevintia.devathlon3.items.Essence;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
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

    @Override
    public void onEnable() {
        instance = this;

        nameConstant = new NameConstant(instance);
        itemConstant = new ItemConstant(instance);
        recipeConstant = new RecipeConstant(instance);
        messageConstant = new MessageConstant(instance);

        nameConstant.initializeContent();
        itemConstant.initializeContent();
        recipeConstant.initializeContent();
        messageConstant.initializeContent();

        // init portals
        new MagicPortal(this);
        // blood drop
        new Blood(this);
        // essence drop
        new Essence(this);
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

    public static Devathlon3 getInstance() {
        return instance;
    }

}
