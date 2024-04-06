package ru.don1x.fimecheck.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.don1x.fimecheck.Config;
import ru.don1x.fimecheck.Cooldowns;
import ru.don1x.fimecheck.Utils;

import java.util.List;

public class CommandAlly implements CommandSub {
    FileConfiguration data = Config.getFile("data.yml");

    public CommandAlly() {
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length > 1) {
            return false;
        } else if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, Utils.getMessage("only-players"));
            return true;
        } else {
            Player player = (Player)sender;
            if (!Utils.has(player, "AkyloffCheck.ally")) {
                return true;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && target.isOnline()) {
                    if (!Utils.isCheck(target.getName())) {
                        Utils.sendMessage(player, Utils.getMessage("ally.already"));
                        return true;
                    } else {
                        Utils.sendMessage(player, Utils.getMessage("ally.successfully").replace("%player%", target.getName()));
                        Utils.sendMessage(target, Utils.getMessage("ally.successfully-target"));
                        Utils.removeCheck(target.getName());
                        Cooldowns.removeCooldown(target.getName());
                        return true;
                    }
                } else {
                    Utils.sendMessage(player, Utils.getMessage("not-found"));
                    return true;
                }
            }
        }
    }

    public List<String> tab(CommandSender p0, String[] p1) {
        return null;
    }

    public String command() {
        return "ally";
    }

    public List<String> aliases() {
        return null;
    }

    public String permission() {
        return "fimecheck.ally";
    }

    public String description() {
        return Utils.getMessage("ally.usage");
    }
}
