package io.github.teamdevintia.devathlon3.util.factory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Shad0wCore
 *         <p>
 *         A utility class to build items efficiently
 */
public final class ItemFactory {

    private ItemStack factoryItemStack;
    private ItemMeta factoryItemMeta;

    public ItemFactory(Material material) {
        this.factoryItemStack = new ItemStack(material);
        this.factoryItemMeta = this.factoryItemStack.getItemMeta();
    }

    /**
     * Sets the material of the item stack
     *
     * @param itemMaterial The material of the item stack
     */
    public synchronized ItemFactory material(Material itemMaterial) {
        this.factoryItemStack.setType(itemMaterial);
        return this;
    }

    /**
     * Sets the amount of the item stack
     *
     * @param itemAmount The amount of the item stack
     */
    public synchronized ItemFactory amount(int itemAmount) {
        this.factoryItemStack.setAmount(itemAmount);
        return this;
    }

    /**
     * Sets the display name of the item stack
     *
     * @param itemDisplayName The display name of the item stack
     */
    public synchronized ItemFactory displayName(String itemDisplayName) {
        this.factoryItemMeta.setDisplayName(itemDisplayName);
        return this;
    }

    /**
     * Sets the durability of the item stack
     * Basically sometimes the durability is being used as the
     * sub id of a item or block.
     *
     * @param itemDurability The durability of the item stack using short
     */
    public synchronized ItemFactory durability(short itemDurability) {
        this.factoryItemStack.setDurability(itemDurability);
        return this;
    }

    /**
     * Basically the same method as {@link #durability(short)} using integer
     */
    @Deprecated
    public synchronized ItemFactory durability(int itemDurability) {
        this.factoryItemStack.setDurability((short) itemDurability);
        return this;
    }

    /**
     * {@link #lore(String...)}
     */
    public synchronized ItemFactory lore(String... itemLore) {
        this.factoryItemMeta.setLore(Arrays.asList(itemLore));
        return this;
    }

    /**
     * Sets the lore of the item stack
     *
     * @param itemLore The lore of the item stack
     */
    public synchronized ItemFactory lore(List<String> itemLore) {
        this.factoryItemMeta.setLore(itemLore);
        return this;
    }

    /**
     * Basically the same method as {@link #data(byte)} using integer
     */
    @Deprecated
    public synchronized ItemFactory data(int itemData) {
        this.factoryItemStack.setData(new MaterialData(this.factoryItemStack.getType(), (byte) itemData));
        return this;
    }

    /**
     * Sets the data of the item stack using integer
     *
     * @param itemData The data of the item stack
     */
    public synchronized ItemFactory data(byte itemData) {
        this.factoryItemStack.setData(new MaterialData(this.factoryItemStack.getType(), itemData));
        return this;
    }

    /**
     * Applies a enchantment to a item stack
     *
     * @param itemEnchantment   The enchantment which will be applied to the item stack
     * @param enchantmentLevel  The level of the enchantment
     * @param unsafeEnchantment Allows to set the level higher than 10
     */
    public synchronized ItemFactory enchantment(Enchantment itemEnchantment, int enchantmentLevel, boolean unsafeEnchantment) {
        //TODO Fix this method @MiniDigger
        if (unsafeEnchantment) {
            this.factoryItemStack.addUnsafeEnchantment(itemEnchantment, enchantmentLevel);
            return this;
        }
        this.factoryItemMeta.addEnchant(itemEnchantment, enchantmentLevel, true);
        return this;
    }

    /**
     * Applies a multiple enchantments to a item stack
     *
     * @param itemEnchantmentMap The map containing the enchantments which will be applied to the item stack
     * @param unsafeEnchantment  Allows to set the level higher than 10
     */
    @Deprecated
    public synchronized ItemFactory enchantment(Map<Enchantment, Integer> itemEnchantmentMap, boolean unsafeEnchantment) {
        //TODO Fix this method @MiniDigger
        if (unsafeEnchantment) {
            this.factoryItemStack.addUnsafeEnchantments(itemEnchantmentMap);
            return this;
        }

        this.factoryItemStack.addEnchantments(itemEnchantmentMap);
        return this;
    }

    /**
     * Makes the item stack unbreakable
     * When it is unbreakable there will be no item stack attrition
     */
    public synchronized ItemFactory unbreakable() {
        this.factoryItemMeta.spigot().setUnbreakable(true);
        return this;
    }

    /**
     * Applies the item flags to the item stack
     *
     * @param itemFlags The item flags which are going to be applied to the item stack
     */
    public synchronized ItemFactory itemFlags(ItemFlag... itemFlags) {
        this.factoryItemMeta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Returns the finished item stack
     *
     * @return The finished item stack with given data
     */
    public synchronized ItemStack release() {
        this.factoryItemStack.setItemMeta(this.factoryItemMeta);
        return this.factoryItemStack;
    }

    /**
     * Replaces the current item stack
     *
     * @param itemStack The item stack which is going to replace the current item stack
     */
    public synchronized ItemFactory set(ItemStack itemStack) {
        this.factoryItemStack = itemStack;
        this.factoryItemMeta = this.factoryItemStack.getItemMeta();
        return this;
    }

    /**
     * Initializes a new instance of the ItemFactory class
     */
    public ItemFactory rebind(Material material) {
        return new ItemFactory(material);
    }

}
