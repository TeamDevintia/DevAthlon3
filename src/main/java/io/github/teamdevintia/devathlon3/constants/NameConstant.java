package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.ChatColor;

import java.util.HashMap;

/**
 * @author Shad0wCore
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
        this.getContentMap().put("item.name.firepotion",ChatColor.RED + "FirePotion");
        this.getContentMap().put("wizard.displayname", "Urzauberer");
    }

    @Override
    public String get(String identifier) {
        return super.get(identifier);
    }

    @Override
    public HashMap<String, String> getContentMap() {
        return super.getContentMap();
    }
}
