package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

/**
 * This constant stores various names
 *
 * @author Shad0wCore & MiniDigger
 */
public class NameConstant extends Constant<String> {

    public NameConstant(Devathlon3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("item.name.ritualLantern", ChatColor.RED + "Ritualsleuchter");
        this.getContentMap().put("item.name.blood", ChatColor.RED + "Blut");
        this.getContentMap().put("item.name.essence", ChatColor.GREEN + "Essenz");
        this.getContentMap().put("item.name.firePotion",ChatColor.RED + "FirePotion");
        this.getContentMap().put("item.name.icePotion",ChatColor.BLUE + "IcePotion");
        this.getContentMap().put("item.name.timeFreezePotion",ChatColor.BLUE + "TimeFreezePotion");
        this.getContentMap().put("item.name.impulsePotion",ChatColor.BLUE + "ImpulsPotion");
        this.getContentMap().put("item.name.winterPotion",ChatColor.BLUE + "WinterPotion");
        this.getContentMap().put("item.name.sunPotion",ChatColor.BLUE + "SunPotion");
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
