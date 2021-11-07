package io.github.mrmarvel.schemabuild.plugin;

import io.github.mrmarvel.schemabuild.schematic.Scheduler;
import io.github.mrmarvel.schemabuild.schematic.Schematic;
import io.github.mrmarvel.schemabuild.schematic.SchematicNotLoadedException;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.org.apache.maven.model.Build;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Per-player task that previews schematics async
 * @author SamB440
 */
public class BuildTask {

    private SchemaBuild plugin;

    private Player player;

    private Map<UUID, List<Location>> cache = new HashMap<>();

    public BuildTask(SchemaBuild plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public BuildTask() {}

    @SuppressWarnings("deprecation")
    public BuildTask start() {
        Scheduler scheduler = new Scheduler();
        scheduler.setTask(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            PlayerManagement pm = plugin.getPlayerManagement();
            if (pm.isBuilding(player.getUniqueId())) {
                Schematic schematic = pm.getBuilding(player.getUniqueId());
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ChatColor.RED +
                        "Left Click to cancel" + ChatColor.GRAY + " : " + ChatColor.GREEN + "Right Click to place").create());
                try {
                    List<Location> locations = new ArrayList<>(schematic.pasteSchematic(player.getTargetBlock(null, 10).getLocation().add(0, 1, 0), player, Schematic.Options.PREVIEW, Schematic.Options.IGNORE_TRANSPARENT));
                    if (cache.containsKey(player.getUniqueId()) && !cache.get(player.getUniqueId()).equals(locations)) {
                        cache.get(player.getUniqueId()).forEach(location -> player.sendBlockChange(location, location.getBlock().getBlockData()));
                        cache.remove(player.getUniqueId());
                    }
                    cache.put(player.getUniqueId(), locations);
                } catch (SchematicNotLoadedException e) {
                    e.printStackTrace();
                }
            } else if (!pm.isBuilding(player.getUniqueId())) {
                for (Location location : cache.get(player.getUniqueId())) {
                    player.sendBlockChange(location, location.getBlock().getBlockData());
                }
                scheduler.cancel();
            }
        }, 5L, 1L).getTaskId());
        return this;
    }
}
