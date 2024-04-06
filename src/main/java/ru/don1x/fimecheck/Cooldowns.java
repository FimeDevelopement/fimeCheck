package ru.don1x.fimecheck;

import java.util.HashMap;

public class Cooldowns {
    private static HashMap<String, Double> cooldowns = new HashMap();

    public Cooldowns() {
    }

    public static void addCooldown(String name, double time) {
        double delay = (double)System.currentTimeMillis() + time * 1000.0;
        cooldowns.put(name, delay);
    }

    public static void addCooldown(String name) {
        addCooldown(name, (double)Utils.getInt("settings.cooldown"));
    }

    public static boolean hasCooldown(String name) {
        if (cooldowns.get(name) != null) {
            if ((Double)cooldowns.get(name) > (double)System.currentTimeMillis()) {
                return true;
            }

            cooldowns.remove(name);
        }

        return false;
    }

    public static int getCooldown(String name) {
        return cooldowns.get(name) != null ? (int)((Double)cooldowns.get(name) - (double)System.currentTimeMillis()) / 1000 : 0;
    }

    public static boolean removeCooldown(String name) {
        if (cooldowns.get(name) != null) {
            cooldowns.remove(name);
        }

        return false;
    }
}
