package io.github.teamdevintia.devathlon3.commands;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.constants.MessageConstant;
import io.github.teamdevintia.devathlon3.potions.MagicPotion;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Shad0wCore
 */
public class GivePotionCommand extends CommandHandler {

    private MessageConstant messageConstant;

    public GivePotionCommand(Devathlon3 instance, String name, String description, String usageMessage, List<String> aliases) {
        super(instance, name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender commandSender, String command, String[] args) {
        if (command.equalsIgnoreCase("givepotion")) {
            if (args.length != 2) {
                return false;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                commandSender.sendMessage(this.messageConstant.get("command.unknownPlayer"));
                return true;
            }

            MagicPotion potion = this.instance().getPotionManager().getFromId(args[1]);
            if (potion == null) {
                commandSender.sendMessage(this.messageConstant.get("command.unknownPotion"));
                return true;
            }

            potion.onPotionBuild(player);
            player.getInventory().addItem(potion.getItem());
            return true;
        }

        return false;
    }

}
