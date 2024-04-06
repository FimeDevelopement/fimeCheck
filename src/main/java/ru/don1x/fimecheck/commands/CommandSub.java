package ru.don1x.fimecheck.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CommandSub {
    boolean execute(CommandSender var1, String[] var2);

    List<String> tab(CommandSender var1, String[] var2);

    String command();

    List<String> aliases();

    String permission();

    String description();
}
