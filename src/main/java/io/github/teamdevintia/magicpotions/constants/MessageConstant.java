package io.github.teamdevintia.magicpotions.constants;

import io.github.teamdevintia.magicpotions.MagicPotions;
import org.bukkit.ChatColor;

import java.util.Map;

/**
 * This constant stores chat messages
 *
 * @author MiniDigger
 */
public class MessageConstant extends Constant<String> {

    public MessageConstant(MagicPotions instance) {
        super(instance);
    }

    @Override
    public void initializeContent() {
        this.getContentMap().put("wizard.spawn.1", ChatColor.GREEN + "Urzauberer>" + ChatColor.WHITE + "Ich bin der Urzauberer!");
        this.getContentMap().put("wizard.spawn.2", ChatColor.GREEN + "Urzauberer>" + ChatColor.WHITE + "Du hast mit dieser Konstruktion das Ritual des Blutes durchgeführt.");
        this.getContentMap().put("wizard.spawn.3", ChatColor.GREEN + "Urzauberer>" + ChatColor.WHITE + "Dadurch hast du Magie in diese Welt eindringen lassen und auch mich kurz manifestiert!");
        this.getContentMap().put("wizard.spawn.4", ChatColor.GREEN + "Urzauberer>" + ChatColor.WHITE + "Diese Magie dringt in die Monster dieser Welt ein, jedoch kannst auch du einiges damit machen.");
        this.getContentMap().put("wizard.spawn.5", ChatColor.GREEN + "Urzauberer>" + ChatColor.WHITE + "Jetzt aber wird sich der Schrein zerstören, viel Glück!");
        this.getContentMap().put("command.unknownPlayer", ChatColor.RED + "Dieser Spieler ist nicht online!");
        this.getContentMap().put("command.unknownPotion", ChatColor.RED + "Unbekannter Zaubertrank!");
        this.getContentMap().put("command.unknownItem", ChatColor.RED + "Unbekanntes Item!");
        this.getContentMap().put("command.potionGiven", ChatColor.GREEN + "Hier is dein Potion!");
        this.getContentMap().put("command.itemGiven", ChatColor.GREEN + "Hier is dein Item!");
        this.getContentMap().put("command.executed", ChatColor.GREEN + "Ausgeführt!");
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