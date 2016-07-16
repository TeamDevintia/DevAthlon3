package io.github.teamdevintia.devathlon3;

import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Devathlon3 extends JavaPlugin {

    public static final ItemStack ritualsLeuchter = new ItemStack( Material.REDSTONE_TORCH_ON );

    static {
        ItemMeta ritualsLeuchterMeta = ritualsLeuchter.getItemMeta();
        ritualsLeuchterMeta.setDisplayName( "Ritualsleuchter" );
        ritualsLeuchterMeta.addEnchant( Enchantment.DIG_SPEED, 1, true );
        ritualsLeuchterMeta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        ritualsLeuchter.setItemMeta( ritualsLeuchterMeta );
    }

    @Override
    public void onEnable() {
        // init portals
        new MagicPortal( this );
        // blood drop
        new Blood( this );

        addCraftingRecipies();
    }

    @Override
    public void onDisable() {

    }

    private void addCraftingRecipies() {
        // ritualsleuchter
        ShapedRecipe ritualsLeuchterRecipe = new ShapedRecipe( ritualsLeuchter );
        ritualsLeuchterRecipe.shape( "BBB", "BSB", "BBB" );
        ritualsLeuchterRecipe.setIngredient( 'B', Material.REDSTONE );
        ritualsLeuchterRecipe.setIngredient( 'S', Material.STICK );
        getServer().addRecipe( ritualsLeuchterRecipe );
        //TODO ritualsLeuchterRecipe should only accept blood as ingredient
    }


    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        System.out.println( "test" );
        return true;
    }
}
