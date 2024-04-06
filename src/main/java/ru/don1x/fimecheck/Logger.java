package ru.don1x.fimecheck;

import org.bukkit.Bukkit;

public class Logger {
    public Logger() {
    }

    public static void empty(String text) {
        Bukkit.getConsoleSender().sendMessage(Utils.color(text));
    }

    public static void info(String s) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&6[" + FimeCheck.getInstance().getName() + "/INFO] " + s));
    }

    public static void warn(String s) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e[" + FimeCheck.getInstance().getName() + "/WARN] " + s));
    }

    public static void error(String s) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&c[" + FimeCheck.getInstance().getName() + "/ERROR] " + s));
    }
}
