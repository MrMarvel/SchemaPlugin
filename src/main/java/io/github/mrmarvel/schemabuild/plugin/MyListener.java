package io.github.mrmarvel.schemabuild.plugin;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

import java.util.Random;

class MyListener implements Listener {
    private final Random r = new Random();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.sendMessage(String.format("Привет %s! Вас приветствует SchemaBuild!", p.getName()));
        if (p.getName().equals("Serega_3010"))
            if (r.nextBoolean() | r.nextBoolean() | r.nextBoolean()) return;
        p.kickPlayer("ЗАБАНЕН!\nБАНАНЫ ЗАПРЕЩЕНЫ!");
        System.out.println("ТЕСТ");
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
        Material t = Material.BEDROCK;
        if (b.getBlockData().getMaterial().equals(t)) {
            Player p = e.getPlayer();
            p.sendMessage(String.format("Ты хочешь постsss222авить %s?", t));
            e.setCancelled(true);
            showDestroyedBlockBelowPlayer(p);
            showStructure(p);
        }
    }

    private void showDestroyedBlockBelowPlayer(Player p) {
        Location pos = p.getLocation();
        pos.add(new Vector(0,-1,0));
        p.sendBlockDamage(pos, 1.f);
    }

    private void showStructure(Player p) {
        p.sendMessage("showStructureCalled!");
    }

}