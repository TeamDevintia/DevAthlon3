package io.github.teamdevintia.devathlon3.intern.commands;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.intern.MagicCommand;
import io.github.teamdevintia.devathlon3.potions.MagicPotion;
import io.github.teamdevintia.devathlon3.util.TabCompleteUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shad0wCore & MiniDigger
 */
public class GivePotionCommand extends MagicCommand {

    public GivePotionCommand(Devathlon3 instance, String name, String description, String usageMessage, List<String> aliases) {
        super(instance, name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender commandSender, String command, String[] args) {
        if (args.length != 2) {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            commandSender.sendMessage(this.instance().getMessageConstant().get("command.unknownPlayer"));
            return true;
        }

        MagicPotion potion = this.instance().getPotionManager().getFromId(args[1]);
        if (potion == null) {
            commandSender.sendMessage(this.instance().getMessageConstant().get("command.unknownPotion"));
            return true;
        }

        potion.onPotionBuild(player);
        player.getInventory().addItem(potion.getItem());
        player.sendMessage(this.instance().getMessageConstant().get("command.potionGiven"));

        if (!commandSender.getName().equals(player.getName())) {
            commandSender.sendMessage(this.instance().getMessageConstant().get("command.executed"));
        }

        return true;

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length == 2) {
            return TabCompleteUtil.complete(this.instance().getPotionManager().getNames(), args[1]);
        }

        if (args.length == 1) {
            return TabCompleteUtil.complete(TabCompleteUtil.playerNames(), args[0]);
        }

        return new ArrayList<>();
    }

}
