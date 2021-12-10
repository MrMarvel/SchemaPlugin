package io.github.mrmarvel.schemabuild.plugin;

import io.github.mrmarvel.schemabuild.tests.test18.ExceptionTest;
import io.github.mrmarvel.schemabuild.tests.test19.ExceptionTest2;
import io.github.mrmarvel.schemabuild.tests.test19.SchematicNotLoadedException;
import io.github.mrmarvel.schemabuild.tests.test20.TimeChecker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.ParseException;
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
            case "test":
                return onTest(sender, command, label, args);
        }
        return true;
    }

    public boolean onTest(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage(helpMessageTest());
            return false;
        }
        switch(args[1].toLowerCase(Locale.ROOT)) {
            case "17":
                sender.sendMessage("To test use \"/sb enable\" and \"/sb disable\"");
                return true;
            case "18":
                String file = "";
                try {
                    file = args[2];
                    ExceptionTest.test(sb.getDataFolder()+"/schematics/"+file);
                } catch (IOException e) {
                    sender.sendMessage(String.format("There is no file %s", file));
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("There is no 3rd argument - filename.format");
                    return true;
                }
                sender.sendMessage("All is good");
                return true;
            case "19":
                try {
                    ExceptionTest2.test();
                } catch (SchematicNotLoadedException e) {
                    sender.sendMessage("Custom exception works :)");
                }
                return true;
            case "20":
                if (args.length < 4) {
                    sender.sendMessage("§4Not enough arguments\n" +
                            "Converts Moscow date to Vladivostok date\n" +
                            "Example Format: 08.11.2021 15:52");
                    return false;
                }
                String mb_date = args[2]+" "+args[3];
                try {
                    TimeChecker tc = new TimeChecker(mb_date);
                    sender.sendMessage(tc.getCurrentTime());
                } catch (ParseException e) {
                    sender.sendMessage("String "+mb_date+" is not valid date with format dd.MM.yyyy HH:mm");
                    return false;
                }
                return true;
        }
        sender.sendMessage(helpMessageTest());
        return false;
    }

    public String helpMessage() {
        return "SchemaBuild\n" +
                "/sb status - get status of plugin main listeners\n" +
                "/sb disable - disable listeners\n" +
                "/sb enable - enabled listeners\n" +
                "/sb test <test>";
    }

    public String helpMessageTest() {
        return "SchemaBuild Tests\n" +
                "/sb test <test>\n" +
                "/sb test 17-20\n" +
                "/sb test 18 <filename.format>\n" +
                "/sb test 20 <date> <time>";
    }
}
