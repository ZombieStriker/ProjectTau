package com.projecttau.defaults.commands;

import net.minestom.server.MinecraftServer;

public class CommandManager {

    public static void init(){
        MinecraftServer.getCommandManager().register(new GiveCommand());
        MinecraftServer.getCommandManager().register(new OpCommand());
        MinecraftServer.getCommandManager().register(new GamemodeCommand());
        MinecraftServer.getCommandManager().register(new SaveWorldCommand());
    }
}
