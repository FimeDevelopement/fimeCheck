package ru.don1x.fimecheck;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    private static FileConfiguration config;
    private static int taskId;
    private static ArrayList<String> list = new ArrayList();
    private static Calendar nextMidnight;
    private static FileConfiguration data;

    public Utils() {
    }

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("config.yml"));
    }

    public static List<String> replaceList(List<String> list, String replace, String to) {
        return (List)list.stream().map((x) -> {
            return x.replace(replace, to);
        }).collect(Collectors.toList());
    }

    public static Set<String> getSection(String path) {
        return getConfig().getConfigurationSection(path).getKeys(false);
    }

    public static String getString(String path) {
        return getConfig().getString(path);
    }

    public static int getInt(String path) {
        return getConfig().getInt(path);
    }

    public static double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    public static boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void reloadConfig() {
        config = Config.getFile("config.yml");
    }

    public static List<String> color(List<String> text) {
        List<String> list = new ArrayList();
        text.forEach((x) -> {
            list.add(color(x));
        });
        return list;
    }

    public static boolean has(CommandSender player, String permission) {
        if (!player.hasPermission(permission)) {
            sendMessage(player, getMessage("no-permission"));
            return false;
        } else {
            return true;
        }
    }

    public static boolean isInventoryFull(Player player) {
        return !Arrays.asList(player.getInventory().getStorageContents()).contains((Object)null);
    }

    public static void giveItem(Player player, ItemStack item) {
        if (isInventoryFull(player)) {
            Location loc = player.getLocation();
            loc.getWorld().dropItem(loc, item);
        } else {
            player.getInventory().addItem(new ItemStack[]{item});
        }

    }

    public static List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }

    public static String getMessage(String s) {
        return getConfig().getString("messages." + s);
    }

    public static String format(int time) {
        int days = time / 86400;
        int hours = time % 86400 / 3600;
        int minutes = time % 3600 / 60;
        int seconds = time % 60;
        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            builder.append(getString("time.days").replace("%size%", "" + days)).append(" ");
        }

        if (hours > 0) {
            builder.append(getString("time.hours").replace("%size%", "" + hours)).append(" ");
        }

        if (minutes > 0) {
            builder.append(getString("time.minutes").replace("%size%", "" + minutes)).append(" ");
        }

        if (seconds > 0) {
            builder.append(getString("time.seconds").replace("%size%", "" + seconds)).append(" ");
        }

        String format;
        return !(format = builder.toString().trim()).isEmpty() ? format : getString("time.now");
    }

    public static void sendMessage(CommandSender player, String text) {
        String[] var2 = text.split(";");
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String line = var2[var4];
            line = line.replace("<player>", player.getName());
            if (line.startsWith("title:")) {
                if (player instanceof Player) {
                    Title.sendTitle((Player)player, line.split("title:")[1]);
                }
            } else if (line.startsWith("actionbar:")) {
                if (player instanceof Player) {
                    ActionBar.sendActionBar((Player)player, line.split("actionbar:")[1]);
                }
            } else {
                player.sendMessage(color(getMessage("prefix") + line));
            }
        }

    }

    public static void sendMessageNoPrefix(CommandSender player, String text) {
        String[] var2 = text.split(";");
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String line = var2[var4];
            line = line.replace("<player>", player.getName());
            if (line.startsWith("title:")) {
                if (player instanceof Player) {
                    Title.sendTitle((Player)player, line.split("title:")[1]);
                }
            } else if (line.startsWith("actionbar:")) {
                if (player instanceof Player) {
                    ActionBar.sendActionBar((Player)player, line.split("actionbar:")[1]);
                }
            } else {
                player.sendMessage(color(line));
            }
        }

    }

    public static boolean equalsCommand(String message, List<String> text) {
        return text.stream().anyMatch((x) -> {
            return message.equalsIgnoreCase("/" + x) || message.toLowerCase().startsWith("/" + x.toLowerCase() + " ");
        });
    }

    public static String convertSeconds(int totalSecs) {
        return (totalSecs % 3600 / 60 < 10 ? "0" + totalSecs % 3600 / 60 : totalSecs % 3600 / 60) + ":" + (totalSecs % 60 < 10 ? "0" + totalSecs % 60 : totalSecs % 60);
    }

    private static void setMidnight() {
        (nextMidnight = Calendar.getInstance()).set(11, 0);
        nextMidnight.set(12, 0);
        nextMidnight.set(13, 0);
        nextMidnight.set(14, 0);
        nextMidnight.add(5, 1);
    }

    public static int getTimeLeftInSeconds() {
        if (nextMidnight == null) {
            setMidnight();
        }

        int totalSecs = (int)((nextMidnight.getTimeInMillis() - System.currentTimeMillis()) / 1000L);
        if ((long)totalSecs <= 0L) {
            setMidnight();
        }

        return totalSecs;
    }

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static void execute(String command) {
        Bukkit.getScheduler().runTask(FimeCheck.getInstance(), () -> {
        });
    }

    public static void execute(List<String> commands) {
        commands.forEach(Utils::execute);
    }

    public static FileConfiguration getLogs() {
        return data != null ? data : (data = Config.getFile("logs.yml"));
    }

    public static void saveLogs() {
        Config.save(data, "logs.yml");
    }

    public static boolean addCheck(String playerName) {
        return list.add(playerName);
    }

    public static boolean isCheck(String playerName) {
        return list.contains(playerName);
    }

    public static boolean removeCheck(String playerName) {
        return list.remove(playerName);
    }

//    public static void startSchulder(Player player, Player sender) {
//        Bukkit.getScheduler().runTaskTimer(FimeCheck.getInstance(), new (player, sender), 1L, (long)getInt("settings.message-delay") * 20L);
//    }

    public static void startSchulder(Player player) {
        taskId = Bukkit.getScheduler().runTaskTimer(FimeCheck.getInstance(), () -> {
            if (!Cooldowns.hasCooldown(player.getName()) && isCheck(player.getName())) {
                getStringList("settings.timer-commands").forEach((x) -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), x.replace("%player%", player.getName()));
                    Bukkit.getScheduler().cancelTask(taskId);
                });
            }

            if (Cooldowns.hasCooldown(player.getName()) && isCheck(player.getName())) {
                player.sendActionBar(color(getString("check.actionbar").replace("%time%", format(Cooldowns.getCooldown(player.getName())))));
            }

        }, 1L, 1L).getTaskId();
    }
}
