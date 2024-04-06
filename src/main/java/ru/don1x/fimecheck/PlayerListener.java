package ru.don1x.fimecheck;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    public PlayerListener() {
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Utils.getBoolean("settings.abilities.move") && Utils.isCheck(e.getPlayer().getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        if (Utils.getBoolean("settings.abilities.drop") && Utils.isCheck(e.getPlayer().getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Utils.getBoolean("settings.abilities.chat") && Utils.isCheck(e.getPlayer().getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (Utils.getBoolean("settings.abilities.inventory") && Utils.isCheck(e.getWhoClicked().getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        if (Utils.getBoolean("settings.abilities.break") && Utils.isCheck(e.getPlayer().getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        if (Utils.getBoolean("settings.abilities.place") && Utils.isCheck(e.getPlayer().getName())) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent e) {
        if (Utils.isCheck(e.getPlayer().getName())) {
            Utils.getStringList("settings.quit-commands").forEach((x) -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), x.replace("%player%", e.getPlayer().getName()));
            });
            Utils.removeCheck(e.getPlayer().getName());
            Cooldowns.removeCooldown(e.getPlayer().getName());
        }

    }
}
