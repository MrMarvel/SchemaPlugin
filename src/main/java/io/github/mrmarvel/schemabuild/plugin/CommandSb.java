package io.github.mrmarvel.schemabuild.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class CommandSb implements CommandExecutor {
    private SchemaBuild sb;

    public CommandSb(SchemaBuild sb) {
        this.sb = sb;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage(helpMessage());
            return false;
        }
        switch(args[0].toLowerCase(Locale.ROOT)) {
            case "status":
                sender.sendMessage("Current status: "+sb.getStatus());
                return true;
            case "enable":
                sender.sendMessage("Trying to enable.");
                sb.registerListeners();
                return true;
            case "disable":
                sender.sendMessage("Trying to disable.");
                sb.unregisterListeners();
                return true;
        }
        return true;
    }

    public String helpMessage() {
        return "SchemaBuild\n" +
                "/sb status - get status of plugin main listeners\n" +
                "/sb disable - disable listeners\n" +
                "/sb enable - enabled listeners";
    }
}
