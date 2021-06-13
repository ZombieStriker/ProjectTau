package com.projecttau.defaults.functions;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.extras.PlacementRules;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.instance.block.rule.vanilla.StairsPlacementRule;
import net.minestom.server.listener.BlockPlacementListener;

public class FunctionManager {

    public static void init(GlobalEventHandler handler){
        for(Block block : Block.values()) {
            if(block.name().endsWith("_STAIRS")){
                MinecraftServer.getBlockManager().registerBlockPlacementRule(new StairsPlacementRule(block));
            }
        }
        new ApplySkinFunction(handler);
        new DebugStick(handler);
    }
}
