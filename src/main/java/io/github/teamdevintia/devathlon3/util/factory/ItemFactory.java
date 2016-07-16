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
 */
public final class ItemFactory {

    private ItemStack factoryItemStack;
    private ItemMeta factoryItemMeta;

    public ItemFactory(Material material) {
        this.factoryItemStack = new ItemStack(material);
        this.factoryItemMeta = this.factoryItemStack.getItemMeta();
    }

    public synchronized ItemFactory material(Material itemMaterial) {
        this.factoryItemStack.setType(itemMaterial);
        return this;
    }

    public synchronized ItemFactory amount(int itemAmount) {
        this.factoryItemStack.setAmount(itemAmount);
        return this;
    }

    public synchronized ItemFactory displayName(String itemDisplayName) {
        this.factoryItemMeta.setDisplayName(itemDisplayName);
        return this;
    }

    public synchronized ItemFactory durability(short itemDurability) {
        this.factoryItemStack.setDurability(itemDurability);
        return this;
    }

    @Deprecated
    public synchronized ItemFactory durability(int itemDurability) {
        this.factoryItemStack.setDurability((short) itemDurability);
        return this;
    }

    public synchronized ItemFactory lore(String... itemLore) {
        this.factoryItemMeta.setLore(Arrays.asList(itemLore));
        return this;
    }

    public synchronized ItemFactory lore(List<String> itemLore) {
        this.factoryItemMeta.setLore(itemLore);
        return this;
    }

    @Deprecated
    public synchronized ItemFactory data(int itemData) {
        this.factoryItemStack.setData(new MaterialData(this.factoryItemStack.getType(), (byte) itemData));
        return this;
    }

    public synchronized ItemFactory data(byte itemData) {
        this.factoryItemStack.setData(new MaterialData(this.factoryItemStack.getType(), itemData));
        return this;
    }

    public synchronized ItemFactory enchantment(Enchantment itemEnchantment, int enchantmentLevel, boolean unsafeEnchantment) {
        if (unsafeEnchantment) {
            this.factoryItemStack.addUnsafeEnchantment(itemEnchantment, enchantmentLevel);
            return this;
        }
        this.factoryItemMeta.addEnchant(itemEnchantment, enchantmentLevel, true);
        return this;
    }

    @Deprecated
    public synchronized ItemFactory enchantment(Map<Enchantment, Integer> itemEnchantmentMap, boolean unsafeEnchantment) {
        if (unsafeEnchantment) {
            this.factoryItemStack.addUnsafeEnchantments(itemEnchantmentMap);
            return this;
        }

        this.factoryItemStack.addEnchantments(itemEnchantmentMap);
        return this;
    }

    public synchronized ItemFactory unbreakable() {
        this.factoryItemMeta.spigot().setUnbreakable(true);
        return this;
    }

    public synchronized ItemFactory itemFlags(ItemFlag... itemFlags) {
        this.factoryItemMeta.addItemFlags(itemFlags);
        return this;
    }

    public synchronized ItemStack release() {
        this.factoryItemStack.setItemMeta(this.factoryItemMeta);
        return this.factoryItemStack;
    }

    public synchronized ItemFactory set(ItemStack itemStack) {
        this.factoryItemStack = itemStack;
        this.factoryItemMeta = this.factoryItemStack.getItemMeta();
        return this;
    }

    public ItemFactory rebind(Material material) {
        return new ItemFactory(material);
    }

}
