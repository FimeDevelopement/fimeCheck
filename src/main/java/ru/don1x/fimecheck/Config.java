package ru.don1x.fimecheck;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public Config() {
    }

    public static FileConfiguration getFile(String s) {
        File file = new File(FimeCheck.getInstance().getDataFolder(), s);
        if (FimeCheck.getInstance().getResource(s) == null) {
            return save(YamlConfiguration.loadConfiguration(file), s);
        } else {
            if (!file.exists()) {
                FimeCheck.getInstance().saveResource(s, false);
            }

            return YamlConfiguration.loadConfiguration(file);
        }
    }

    public static FileConfiguration save(FileConfiguration fileConfiguration, String s) {
        try {
            fileConfiguration.save(new File(FimeCheck.getInstance().getDataFolder(), s));
        } catch (IOException var3) {
            IOException ex = var3;
            ex.printStackTrace();
        }

        return fileConfiguration;
    }
}
