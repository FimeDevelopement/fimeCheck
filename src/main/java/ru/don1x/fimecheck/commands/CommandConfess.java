package ru.don1x.fimecheck.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.don1x.fimecheck.Cooldowns;
import ru.don1x.fimecheck.Utils;

import java.util.List;

public class CommandConfess implements CommandSub {
    public CommandConfess() {
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            return false;
        } else if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, Utils.getMessage("only-players"));
            return true;
        } else {
            Player player = (Player)sender;
            if (!Utils.isCheck(player.getName())) {
                Utils.sendMessage(player, Utils.getMessage("confess.already"));
                return true;
            } else {
                Utils.getStringList("settings.confess-commands").forEach((x) -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), x.replace("%player%", sender.getName()));
                });
                Utils.removeCheck(player.getName());
                Cooldowns.removeCooldown(player.getName());
                return true;
            }
        }
    }

    public List<String> tab(CommandSender p0, String[] p1) {
        return null;
    }

    public String command() {
        return "confess";
    }

    public List<String> aliases() {
        return null;
    }

    public String permission() {
        return "fimecheck.confess";
    }

    public String description() {
        return Utils.getMessage("confess.usage");
    }
}
