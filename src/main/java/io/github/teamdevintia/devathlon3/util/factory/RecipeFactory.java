package io.github.teamdevintia.devathlon3.util.factory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

/**
 * @author Shad0wCore
 */
public  final class RecipeFactory {

    private ShapedRecipe shapedRecipe = null;

    public RecipeFactory(ItemStack result) {
        this.shapedRecipe = new ShapedRecipe(result);
    }

    public RecipeFactory shape(String... recipeShape) {
        this.shapedRecipe.shape(recipeShape);
        return this;
    }

    public RecipeFactory firstLayerShape(String layer) {
        this.shapedRecipe.shape(layer, this.shapedRecipe.getShape()[1], this.shapedRecipe.getShape()[2]);
        return this;
    }

    public RecipeFactory secondLayerShape(String layer) {
        this.shapedRecipe.shape(this.shapedRecipe.getShape()[0], layer, this.shapedRecipe.getShape()[2]);
        return this;
    }

    public RecipeFactory thirdLayerShape(String layer) {
        this.shapedRecipe.shape(this.shapedRecipe.getShape()[0], this.shapedRecipe.getShape()[1], layer);
        return this;
    }

    public RecipeFactory ingredient(char key, MaterialData materialData) {
        this.shapedRecipe.setIngredient(key, materialData);
        return this;
    }

    public RecipeFactory ingredient(char key, Material material) {
        this.shapedRecipe.setIngredient(key, material);
        return this;
    }

    public ShapedRecipe releaseAndRegister() {
        Bukkit.getServer().addRecipe(this.shapedRecipe);
        return this.shapedRecipe;
    }

    public ShapedRecipe release() {
        return this.shapedRecipe;
    }

}
