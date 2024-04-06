package ru.don1x.fimecheck.commands;

import org.bukkit.command.CommandSender;
import ru.don1x.fimecheck.Utils;

import java.util.List;

public class CommandReload implements CommandSub {
    public CommandReload() {
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length > 1) {
            return false;
        } else {
            Utils.reloadConfig();
            Utils.sendMessage(sender, Utils.getMessage("reload.successfully"));
            return true;
        }
    }

    public List<String> tab(CommandSender p0, String[] p1) {
        return null;
    }

    public String command() {
        return "reload";
    }

    public List<String> aliases() {
        return null;
    }

    public String permission() {
        return "fimecheck.reload";
    }

    public String description() {
        return Utils.getMessage("reload.usage");
    }
}
