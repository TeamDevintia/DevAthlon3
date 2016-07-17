package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

/**
 * This constant stores chat messages
 *
 * @author MiniDigger
 */
public class MessageConstant extends Constant<String> {

    public MessageConstant(Devathlon3 instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("wizard.spawn.1", "Ich bin der Urzauberer!");
        this.getContentMap().put("wizard.spawn.2", "Du hast mit dieser Konstruktion das Ritual des Blutes durchgeführt.");
        this.getContentMap().put("wizard.spawn.3", "Dadurch hast du Magie in diese Welt eindringen lassen und auch mich kurz manifestiert!");
        this.getContentMap().put("wizard.spawn.4", "Diese Magie dringt in die Monster dieser Welt ein, jedoch kannst auch du einiges damit machen.");
        this.getContentMap().put("wizard.spawn.5", "Nimm dieses Buch, es wird dich in der Magie lehren.");
        this.getContentMap().put("wizard.spawn.6", "Jetzt aber wird sich der Schrein zerstören, viel Glück!");
        this.getContentMap().put("command.unknownPlayer", ChatColor.RED + "Dieser Spieler ist nicht online!");
        this.getContentMap().put("command.unknownPotion", ChatColor.RED + "Unbekannter Zaubertrank!");
        this.getContentMap().put("command.unknownItem", ChatColor.RED + "Unbekanntes Item!");
        this.getContentMap().put("command.potionGiven", ChatColor.GREEN + "Hier is dein Potion!");
        this.getContentMap().put("command.itemGiven", ChatColor.GREEN + "Hier is dein Item!");
        this.getContentMap().put("command.excuted", ChatColor.GREEN + "Ausgeführt!");
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