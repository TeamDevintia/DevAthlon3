package io.github.teamdevintia.devathlon3.items;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import io.github.teamdevintia.devathlon3.util.factory.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

/**
 * Handles the mechanics attached to the essence item.
 *
 * @author MiniDigger
 */
public class Essence implements Listener {

    public static final ItemStack ESSENCE = new ItemFactory(Material.SLIME_BALL).amount(1).displayName(ChatColor.GREEN + "Essenz")
            .itemFlags(ItemFlag.HIDE_ENCHANTS).enchantment(Enchantment.DIG_SPEED, 1, true).release();

   /* static {
        // essence item
        ItemMeta meta = ESSENCE.getItemMeta();
        meta.setDisplayName( "Essenz" );
        meta.addEnchant( Enchantment.DIG_SPEED, 1, true );
        meta.addItemFlags( ItemFlag.HIDE_ENCHANTS );
        ESSENCE.setItemMeta( meta );
    }
*/

    public Essence( Devathlon3 plugin ) {
        Bukkit.getPluginManager().registerEvents( this, plugin );
    }

    @EventHandler
    public void onDeath( EntityDeathEvent event ) {
        // spawn essences on death of some entities
        // after before wizard spawns
        if ( MagicPortal.wizardActive ) {
            Location loc = event.getEntity().getLocation();

            // 3 for witch
            if ( event.getEntityType() == EntityType.WITCH ) {
                for ( int i = 0; i < 2; i++ ) {
                    loc.getWorld().dropItemNaturally( loc, ESSENCE );
                }
            } else
                // 1 for the rest
                if ( event.getEntityType() == EntityType.ZOMBIE || event.getEntityType() == EntityType.SKELETON || event.getEntityType() == EntityType.SPIDER ) {
                    loc.getWorld().dropItemNaturally( loc, ESSENCE );
                }
        }
    }
}
