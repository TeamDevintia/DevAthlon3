package io.github.teamdevintia.devathlon3;

import io.github.teamdevintia.devathlon3.constants.ItemConstant;
import io.github.teamdevintia.devathlon3.constants.NameConstant;
import io.github.teamdevintia.devathlon3.constants.RecipeConstant;
import io.github.teamdevintia.devathlon3.items.Blood;
import io.github.teamdevintia.devathlon3.items.Essence;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class
 */
public final class Devathlon3 extends JavaPlugin {

    private static Devathlon3 instance;
    private NameConstant nameConstant;
    private ItemConstant itemConstant;
    private RecipeConstant recipeConstant;

    @Override
    public void onEnable() {
        this.instance = this;
        nameConstant = new NameConstant(instance);
        itemConstant = new ItemConstant(instance, nameConstant);
        recipeConstant = new RecipeConstant(instance, itemConstant);

        nameConstant.initializeContent();
        itemConstant.initializeContent();
        recipeConstant.initializeContent();

        // init portals
        new MagicPortal(this);
        // blood drop
        new Blood(this);
        // essence drop
        new Essence(this);
    }

    @Override
    public void onDisable() {

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

    public static Devathlon3 getInstance() {
        return instance;
    }

}
