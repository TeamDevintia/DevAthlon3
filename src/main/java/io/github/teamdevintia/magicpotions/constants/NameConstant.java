package io.github.teamdevintia.magicpotions.constants;

import io.github.teamdevintia.magicpotions.MagicPotions;
import org.bukkit.ChatColor;

import java.util.Map;

/**
 * This constant stores various names
 *
 * @author Shad0wCore & MiniDigger
 */
public class NameConstant extends Constant<String> {

    public NameConstant(MagicPotions instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("item.name.ritualLantern", ChatColor.RED + "RitualLaterne");
        this.getContentMap().put("item.name.blood", ChatColor.RED + "Blut");
        this.getContentMap().put("item.name.essence", ChatColor.GREEN + "Essenz");
        this.getContentMap().put("item.name.firePotion",ChatColor.RED + "FirePotion");
        this.getContentMap().put("item.name.icePotion",ChatColor.LIGHT_PURPLE + "IcePotion");
        this.getContentMap().put("item.name.timeFreezePotion",ChatColor.BLUE + "TimeFreezePotion");
        this.getContentMap().put("item.name.impulsePotion",ChatColor.DARK_GRAY + "ImpulsPotion");
        this.getContentMap().put("item.name.winterPotion",ChatColor.WHITE + "WinterPotion");
        this.getContentMap().put("item.name.sunPotion",ChatColor.GOLD + "SunPotion");
        this.getContentMap().put("item.name.explosionPotion",ChatColor.GRAY + "ExplosionPotion");
        this.getContentMap().put("wizard.displayname", "Urzauberer");
    }

    @Override
    public String get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public Map<String, String> getContentMap() {
        return super.getContentMap();
    }
}
