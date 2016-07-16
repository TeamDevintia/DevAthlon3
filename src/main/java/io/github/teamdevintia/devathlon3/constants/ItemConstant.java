package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.factory.ItemFactory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * @author Shad0wCore
 */
public class ItemConstant extends Constant<ItemStack> {

    public ItemConstant(Devathlon3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("item.ritualLantern", new ItemFactory(Material.REDSTONE_TORCH_ON).amount(1)
                .displayName(instance.getNameConstant().get("item.name.ritualLantern")).enchantment(Enchantment.DIG_SPEED, 1, true)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());

        this.getContentMap().put("item.blood", new ItemFactory(Material.REDSTONE).amount(1)
                .displayName(instance.getNameConstant().get("item.name.blood")).enchantment(Enchantment.DIG_SPEED, 1, true)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());

        this.getContentMap().put("item.essence", new ItemFactory(Material.SLIME_BALL).amount(1)
                .displayName(instance.getNameConstant().get("item.name.essence")).enchantment(Enchantment.DIG_SPEED, 1, true)
                .itemFlags(ItemFlag.HIDE_ENCHANTS).release());
    }

    @Override
    public ItemStack get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public HashMap<String, ItemStack> getContentMap() {
        return super.getContentMap();
    }
}
