package com.projecttau.hub;

import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.PluginMessagePacket;
import net.minestom.server.utils.binary.BinaryWriter;

public class ServerConnector {

    public static void connectToServer(Player player, String server){
        var writer = new BinaryWriter();

        writer.writeSizedString("Connect");
        writer.writeSizedString(server);

        PluginMessagePacket packet = new PluginMessagePacket();
        packet.write(writer);
        player.sendPacketToViewers(packet);
    }
}
