package io.github.teamdevintia.magicpotions.util.factory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

/**
 * @author Shad0wCore
 *         <p>
 *         A utility class to build items efficiently
 */
public final class RecipeFactory {

    private ShapedRecipe shapedRecipe = null;

    public RecipeFactory(ItemStack result) {
        this.shapedRecipe = new ShapedRecipe(result);
    }

    /**
     * Defines the shape of this recipe
     * @param recipeShape like in bukkit
     */
    public RecipeFactory shape(String... recipeShape) {
        this.shapedRecipe.shape(recipeShape);
        return this;
    }

    /**
     * Defines the first layer
     * @param layer like in bukkit
     */
    public RecipeFactory firstLayerShape(String layer) {
        this.shapedRecipe.shape(layer, this.shapedRecipe.getShape()[1], this.shapedRecipe.getShape()[2]);
        return this;
    }

    /**
     * Defines the second layer
     * @param layer like in bukkit
     */
    public RecipeFactory secondLayerShape(String layer) {
        this.shapedRecipe.shape(this.shapedRecipe.getShape()[0], layer, this.shapedRecipe.getShape()[2]);
        return this;
    }

    /**
     * Defines the third layer
     * @param layer like in bukkit
     */
    public RecipeFactory thirdLayerShape(String layer) {
        this.shapedRecipe.shape(this.shapedRecipe.getShape()[0], this.shapedRecipe.getShape()[1], layer);
        return this;
    }

    /**
     * maps a ingredient key to a material data instance
     * @param key the key
     * @param materialData the material data instance that should be mapped to the key
     */
    public RecipeFactory ingredient(char key, MaterialData materialData) {
        this.shapedRecipe.setIngredient(key, materialData);
        return this;
    }

    /**
     * maps a ingredient key to a material
     * @param key the key
     * @param material  the material that should be mapped to the key
     */
    public RecipeFactory ingredient(char key, Material material) {
        this.shapedRecipe.setIngredient(key, material);
        return this;
    }

    /**
     * Returns the finished recipe AND registers it to the server
     * @return the finished recipe
     */
    public ShapedRecipe releaseAndRegister() {
        Bukkit.getServer().addRecipe(this.shapedRecipe);
        return this.shapedRecipe;
    }

    /**
     * Returns the finished recipe WITHOUT registering it to the server
     * @return the finished recipe
     */
    public ShapedRecipe release() {
        return this.shapedRecipe;
    }

}
