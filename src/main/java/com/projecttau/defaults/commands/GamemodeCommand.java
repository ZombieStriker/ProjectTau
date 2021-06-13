package com.projecttau.defaults.commands;

import com.projecttau.bootstrap.Bootstrap;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GamemodeCommand extends Command {


    public GamemodeCommand() {
        super("gamemode","g");

        // Executed if no other executor can be used
        setDefaultExecutor((sender, context) -> {
            if(sender.isPlayer()){
                Player player = sender.asPlayer();
                if(player.getPermissionLevel() >= 2){
                    if(player.isCreative()){
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("Setting you back to survival mode");
                    }else{
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("Setting you to creative mode.");
                    }
                }
            }
        });
    }
}
