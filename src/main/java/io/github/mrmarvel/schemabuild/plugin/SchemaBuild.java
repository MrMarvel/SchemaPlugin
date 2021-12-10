package io.github.mrmarvel.schemabuild.plugin;

import io.github.mrmarvel.schemabuild.tests.test17.PluginState;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SchemaBuild extends JavaPlugin {



    public PlayerManagement getPlayerManagement() {
        return playerManagement;
    }


    private PlayerManagement playerManagement;
    private final PluginManager pm = Bukkit.getPluginManager();
    private PluginState status = PluginState.DISABLED;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("onEnable32 called!");
        this.playerManagement = new PlayerManagement(this);
        HandlerList.unregisterAll(this);
        registerListeners();
        registerCommands();
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("onDisable called!");
        HandlerList.unregisterAll(this);
    }


    boolean registerListeners() {
        if (status == PluginState.ENABLED) return true;
        status = PluginState.ENABLED;
        pm.registerEvents(new SchematicListener(this), this);
        pm.registerEvents(new MyListener(), this);
        return true;
    }

    boolean unregisterListeners() {
        if (status == PluginState.DISABLED) return true;
        status = PluginState.DISABLED;
        HandlerList.unregisterAll(this);
        return true;
    }

    void registerCommands() {
        Objects.requireNonNull(getCommand("status")).setExecutor(new CommandStatus(this));
        Objects.requireNonNull(getCommand("sb")).setExecutor(new CommandSb(this));
    }


    public PluginState getStatus() {
        return status;
    }


}

