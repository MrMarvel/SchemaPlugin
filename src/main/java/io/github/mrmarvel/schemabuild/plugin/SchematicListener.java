package io.github.mrmarvel.schemabuild.plugin;

import io.github.mrmarvel.schemabuild.schematic.Scheduler;
import io.github.mrmarvel.schemabuild.schematic.Schematic;
import io.github.mrmarvel.schemabuild.schematic.SchematicNotLoadedException;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class SchematicListener implements Listener {
    private final SchemaBuild plugin;

    SchematicListener(SchemaBuild plugin) {
        this.plugin = plugin;
    }

    public SchemaBuild plugin() {
        return plugin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        SchematicListener that = (SchematicListener) obj;
        return Objects.equals(this.plugin, that.plugin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plugin);
    }

    @Override
    public String toString() {
        return "SchematicListener[" +
                "plugin=" + plugin + ']';
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent pie) {
        if (pie.getHand() == EquipmentSlot.OFF_HAND) return;

        Player player = pie.getPlayer();
        if (plugin.getPlayerManagement().isBuilding(player.getUniqueId())) {
            switch (pie.getAction()) {
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    try {
                        player.sendMessage(ChatColor.GREEN + "You are now building the schematic; " + plugin.getPlayerManagement().getBuilding(player.getUniqueId()) + "!");
                        Collection<Location> locationCollection = plugin.getPlayerManagement()
                                .getBuilding(player.getUniqueId())
                                .pasteSchematic(player.getTargetBlock(null, 10)
                                        .getLocation().add(0, 1, 0), player, 1, Schematic.Options.IGNORE_TRANSPARENT);
                        if (locationCollection != null) {
                            List<Location> locations = new ArrayList<>(locationCollection);
                            Scheduler scheduler = new Scheduler();
                            scheduler.setTask(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                                for (Location location : locations) {
                                    if (locations.get(locations.size() - 1).getBlock().getType() != Material.AIR) {
                                        scheduler.cancel();
                                    } else {
                                        if (location.getBlock().getType() == Material.AIR) {
                                            location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.getX() + 0.5D, location.getY(), location.getZ() + 0.5D, 2);
                                        }
                                    }
                                }
                            }, 0L, 40L));
                            plugin.getPlayerManagement().removeBuilding(player.getUniqueId());
                        } else {
                            player.sendMessage(ChatColor.RED + "You can't place the schematic here, you need to clear the area first!");
                        }
                    } catch (SchematicNotLoadedException e) {
                        e.printStackTrace();
                    }
                    break;
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                    plugin.getPlayerManagement().removeBuilding(player.getUniqueId());
                    player.sendMessage(ChatColor.RED + "Cancelled building placement.");
                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent pje) {
        Player player = pje.getPlayer();
        player.getTargetBlockExact(1);
        File file = new File(plugin.getDataFolder() + "/schematics/tree.schem");
        if (!file.exists()) {
            player.sendMessage(ChatColor.RED + "Not creating schematic preview since example file does not exist!");
        } else {
            System.out.println(1);
            Schematic s = null;
            try {
                s = new Schematic();
                s = new Schematic(plugin, file);
            } catch (Exception ignored) {}
            System.out.println(2);
            s.loadSchematic();
            s.previewSchematic(player);
            player.sendMessage(ChatColor.GREEN + "Now previewing schematic!");
        }
    }
}
