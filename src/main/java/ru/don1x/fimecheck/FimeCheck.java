package ru.don1x.fimecheck;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.don1x.fimecheck.commands.*;

public final class FimeCheck extends JavaPlugin {
    private static FimeCheck plugin;
    private static CommandManager commandManager;

    public FimeCheck() {
    }

    public static FimeCheck getInstance() {
        return plugin;
    }

    public void onEnable() {
        this.saveDefaultConfig();
        plugin = this;
        commandManager = new CommandManager();
        commandManager.register(new CommandStart());
        commandManager.register(new CommandAlly());
        commandManager.register(new CommandReload());
        commandManager.register(new CommandGetLoggs());
        commandManager.register(new CommandConfess());
        commandManager.register(new CommandConfirmCheat());
        this.getCommand("check").setExecutor(commandManager);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Config.save(Config.getFile("logs.yml"), "logs.yml");
        Logger.empty("Запускаюсь..");
    }
}
