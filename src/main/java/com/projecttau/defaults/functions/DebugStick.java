package com.projecttau.defaults.functions;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.event.player.PlayerUseItemOnBlockEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.vanilla.StairsPlacementRule;
import net.minestom.server.item.Material;

public class DebugStick {
    public DebugStick(GlobalEventHandler handler) {
        handler.addEventCallback(PlayerUseItemOnBlockEvent.class, event -> {
            if (event.getItemStack().getMaterial() == Material.DEBUG_STICK) {
                Block block = event.getPlayer().getInstance().getBlock(event.getPosition());
            }
        });
    }
}
