package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.factory.RecipeFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import java.util.HashMap;

/**
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
    }

    @Override
    public ShapedRecipe get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public HashMap<String, ShapedRecipe> getContentMap() {
        return super.getContentMap();
    }
}
