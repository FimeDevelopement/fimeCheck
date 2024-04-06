package ru.don1x.fimecheck.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.don1x.fimecheck.Config;
import ru.don1x.fimecheck.Cooldowns;
import ru.don1x.fimecheck.Utils;

import java.util.ArrayList;
import java.util.List;

public class CommandStart implements CommandSub {
    FileConfiguration data = Config.getFile("logs.yml");

    public CommandStart() {
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            return false;
        } else if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, Utils.getMessage("only-players"));
            return true;
        } else {
            Player player = (Player)sender;
            if (!Utils.has(player, "fimecheck.check")) {
                return true;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && target.isOnline()) {
                    if (Utils.isCheck(target.getName())) {
                        Utils.sendMessage(player, Utils.getMessage("start.already"));
                        return true;
                    } else if (target.hasPermission("fimecheck.bypass")) {
                        Utils.sendMessage(player, Utils.getMessage("start.bypass"));
                        return true;
                    } else {
                        Utils.sendMessage(player, Utils.getMessage("start.successfully").replace("%player%", target.getName()));
                        Cooldowns.addCooldown(target.getName());
                        Utils.addCheck(target.getName());
//                        Utils.startSchulder(target, player);
                        Utils.startSchulder(target);
                        String format = Utils.getString("format-logs.start");
                        List<String> logs = this.data.getStringList("players." + target.getName() + ".logs");
                        if (logs == null) {
                            logs = new ArrayList();
                        }

                        ((List)logs).add(format.replace("%date%", Utils.getDate()).replace("%target%", target.getName()).replace("%player%", player.getName()));
                        if (Utils.getString("players." + target.getName() + ".logs") == null) {
                            this.data.set("players." + target.getName() + ".logs", logs);
                        }

                        Config.save(this.data, "logs.yml");
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
        return "start";
    }

    public List<String> aliases() {
        return null;
    }

    public String permission() {
        return "fimecheck.check";
    }

    public String description() {
        return Utils.getMessage("start.usage");
    }
}
