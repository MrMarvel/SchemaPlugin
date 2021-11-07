package io.github.mrmarvel.schemabuild.plugin;

import io.github.mrmarvel.schemabuild.schematic.Schematic;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManagement {

    private final JavaPlugin plugin;

    private final Map<UUID, Schematic> building = new HashMap<>();

    public PlayerManagement(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setBuilding(UUID uuid, Schematic schematic) {
        if (!building.containsKey(uuid)) {
            building.put(uuid, schematic);
        } else {
            building.replace(uuid, schematic);
        }
    }

    public boolean isBuilding(UUID uuid) {
        return building.containsKey(uuid);
    }

    public Schematic getBuilding(UUID uuid) {
        return building.get(uuid);
    }

    public void removeBuilding(UUID uuid) {
        building.remove(uuid);
    }
}