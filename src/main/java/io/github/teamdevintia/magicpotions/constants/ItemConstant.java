package io.github.teamdevintia.magicpotions.constants;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.util.factory.ItemFactory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * This constant stores all custom items
 *
 * @author Shad0wCore & MiniDigger
 */
public class ItemConstant extends Constant<ItemStack> {

    public ItemConstant(MagicPotions instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("item.ritualLantern", new ItemFactory(Material.REDSTONE_TORCH_ON).amount(1)
                .displayName(instance.getNameConstant().get("item.name.ritualLantern")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.blood", new ItemFactory(Material.REDSTONE).amount(1)
                .displayName(instance.getNameConstant().get("item.name.blood")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.essence", new ItemFactory(Material.SLIME_BALL).amount(1)
                .displayName(instance.getNameConstant().get("item.name.essence")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        //TODO change the colors of the potions around, talk to designers
        this.getContentMap().put("item.firePotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.firePotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.icePotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.icePotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.timeFreezePotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.timeFreezePotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.impulsePotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.impulsePotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.winterPotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.winterPotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.sunPotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.sunPotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
        this.getContentMap().put("item.explosionPotion", new ItemFactory(Material.SPLASH_POTION).amount(1)
                .displayName(instance.getNameConstant().get("item.name.explosionPotion")).enchantment(Enchantment.DIG_SPEED, 1, false)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
    }

    @Override
    public ItemStack get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public Map<String, ItemStack> getContentMap() {
        return super.getContentMap();
    }
}
