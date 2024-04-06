package ru.don1x.fimecheck.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import ru.don1x.fimecheck.Config;
import ru.don1x.fimecheck.Utils;

import java.util.List;

public class CommandGetLoggs implements CommandSub {
    FileConfiguration data = Config.getFile("logs.yml");

    public CommandGetLoggs() {
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length > 1) {
            return false;
        } else {
            String player = args[0];
            if (this.data.getString("players." + player) == null) {
                Utils.sendMessage(sender, Utils.getMessage("not-found"));
                return true;
            } else {
                this.data.getStringList("players." + player + ".logs").forEach((x) -> {
                    Utils.sendMessageNoPrefix(sender, x);
                });
                Utils.sendMessage(sender, Utils.getMessage("getloggs.successfully").replace("%player%", player));
                return true;
            }
        }
    }

    public List<String> tab(CommandSender p0, String[] p1) {
        return null;
    }

    public String command() {
        return "getloggs";
    }

    public List<String> aliases() {
        return null;
    }

    public String permission() {
        return "fimecheck.getloggs";
    }

    public String description() {
        return Utils.getMessage("getloggs.usage");
    }
}
