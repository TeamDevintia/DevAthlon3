package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.factory.RecipeFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;
import java.util.Map;

/**
 * This constant stores all custom recipes
 *
 * @author Shad0wCore
 */
public class RecipeConstant extends Constant<ShapedRecipe> {

    public RecipeConstant(Devathlon3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("recipe.ritualLantern", new RecipeFactory(instance.getItemConstant().get("item.ritualLantern"))
                .shape("BBB", "BSB", "BBB").ingredient('B', Material.REDSTONE).ingredient('S', Material.STICK).releaseAndRegister());

        // potions, only release, not register!
        this.getContentMap().put("recipe.potion.fire", new RecipeFactory(instance.getItemConstant().get("item.firePotion"))
                .shape("ESE", "SGS", "ESE").ingredient('E', instance.getItemConstant().get("item.essence").getType()).ingredient('S', Material.BLAZE_POWDER)
                .ingredient('G', Material.GLASS_BOTTLE).release());
        this.getContentMap().put("recipe.potion.ice", new RecipeFactory(instance.getItemConstant().get("item.icePotion"))
                .shape("ESE", "SGS", "ESE").ingredient('E', instance.getItemConstant().get("item.essence").getType()).ingredient('S', Material.SNOW_BALL)
                .ingredient('G', Material.GLASS_BOTTLE).release());
        this.getContentMap().put("recipe.potion.timeFreeze", new RecipeFactory(instance.getItemConstant().get("item.timeFreezePotion"))
                .shape("ESE", "SGS", "ESE").ingredient('E', instance.getItemConstant().get("item.essence").getType()).ingredient('S', Material.ENDER_PEARL)
                .ingredient('G', Material.GLASS_BOTTLE).release());
        this.getContentMap().put("recipe.potion.impulse", new RecipeFactory(instance.getItemConstant().get("item.impulsePotion"))
                .shape("ESE", "SGS", "ESE").ingredient('E', instance.getItemConstant().get("item.essence").getType()).ingredient('S', Material.SULPHUR) // SULPHUR = gunpower, wtf bukkit
                .ingredient('G', Material.GLASS_BOTTLE).release());
        this.getContentMap().put("recipe.potion.winter", new RecipeFactory(instance.getItemConstant().get("item.winterPotion"))
                .shape("ESE", "SGS", "ESE").ingredient('E', instance.getItemConstant().get("item.essence").getType()).ingredient('S', Material.IRON_BLOCK)
                .ingredient('G', Material.GLASS_BOTTLE).release());
    }

    @Override
    public ShapedRecipe get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public Map<String, ShapedRecipe> getContentMap() {
        return super.getContentMap();
    }
}
