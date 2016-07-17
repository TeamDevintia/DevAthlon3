package io.github.teamdevintia.magicpotions.command.commands;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.command.MagicCommand;
import io.github.teamdevintia.magicpotions.util.TabCompleteUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Martin on 17.07.2016.
 */
public class GiveItemCommand extends MagicCommand {

    public GiveItemCommand(MagicPotions instance, String name, String description, String usageMessage, List<String> aliases) {
        super(instance, name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender commandSender, String command, String[] args) {
        if (!commandSender.hasPermission("magicpotion.giveitem")) {
            return true;
        }

        if (args.length != 2) {
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            commandSender.sendMessage(this.instance().getMessageConstant().get("command.unknownPlayer"));
            return true;
        }

        ItemStack is = this.instance().getItemConstant().get("item." + args[1]);
        if (is == null) {
            commandSender.sendMessage(this.instance().getMessageConstant().get("command.unknownItem"));
            return true;
        }

        player.getInventory().addItem(is);
        player.sendMessage(this.instance().getMessageConstant().get("command.itemGiven"));

        if (!commandSender.getName().equals(player.getName())) {
            commandSender.sendMessage(this.instance().getMessageConstant().get("command.executed"));
        }

        return true;

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length == 2) {
            List<String> itemNames = this.instance().getItemConstant().getContentMap().keySet().stream().
                    map(key -> key.replace("item.", "")).collect(Collectors.toList());
            return TabCompleteUtil.complete(itemNames, args[1]);
        }

        if (args.length == 1) {
            return TabCompleteUtil.complete(TabCompleteUtil.playerNames(), args[0]);
        }

        return new ArrayList<>();
    }

}
