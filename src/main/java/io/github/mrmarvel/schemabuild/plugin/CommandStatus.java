package io.github.mrmarvel.schemabuild.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandStatus implements CommandExecutor {
    private SchemaBuild sb;

    public CommandStatus(SchemaBuild sb) {
        this.sb = sb;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        sender.sendMessage("Current status of plugin - " + sb.getStatus());
        return false;
    }
}
