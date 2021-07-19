package com.bilicraft.factorylimit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.loot.LootTables;
import org.bukkit.loot.Lootable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FactoryLimit extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler(ignoreCancelled = true)
    public void chestOpening(PlayerInteractEvent event) {
        // 只操作方块
        if(event.getClickedBlock() != null) {
            BlockState state = event.getClickedBlock().getState();
            if (state instanceof Lootable && state instanceof InventoryHolder) {
                Lootable lootable = (Lootable) state;
                if(lootable.getLootTable() != null) {
                    lootable.setLootTable(LootTables.EMPTY.getLootTable());
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void chestOpening(PlayerInteractEntityEvent event) {
        // 只操作实体
        if(event.getRightClicked() instanceof Lootable && event.getRightClicked() instanceof Minecart){
            Lootable lootable = (Lootable) event.getRightClicked();
            if(lootable.getLootTable() != null) {
                lootable.setLootTable(LootTables.EMPTY.getLootTable());
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void blockMining(BlockBreakEvent event) {
        Material mat = event.getBlock().getType();
        boolean hit = Tag.COAL_ORES.isTagged(mat);
        if (!hit && Tag.IRON_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.GOLD_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.DIAMOND_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.EMERALD_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.COPPER_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.LAPIS_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.REDSTONE_ORES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && Tag.DEEPSLATE_ORE_REPLACEABLES.isTagged(mat)) {
            hit = true;
        }
        if (!hit && mat == Material.NETHER_QUARTZ_ORE) {
            hit = true;
        }
        if (!hit && mat == Material.ANCIENT_DEBRIS) {
            hit = true;
        }
        if (hit) {
            event.setDropItems(false);
            event.getPlayer().sendMessage(ChatColor.RED + "异界工厂世界线无法采集矿物，物品将不会掉落");
        }
    }
}
