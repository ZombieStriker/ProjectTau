package com.projecttau.defaults.functions;

import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

public class ApplySkinFunction {

    public ApplySkinFunction(GlobalEventHandler handler){
        handler.addEventCallback(PlayerLoginEvent.class, event -> {
            event.getPlayer().setSkin(PlayerSkin.fromUuid(event.getPlayer().getUuid().toString()));
            event.getPlayer().sendMessage("Test: Setting skin");
        });

    }
}
