package io.github.mrmarvel.schemabuild.tests.test21;


import org.bukkit.entity.Player;

public class GFSender<T extends Player> {
    public void sendFMessage(T aproxPlayer, String msg) {
        aproxPlayer.sendMessage("SchemaPlugin: " + msg);
    }
    public void sendFError(T aproxPlayer,  String msg) {
        sendFMessage(aproxPlayer, "ERROR: "+msg);
    }
}
