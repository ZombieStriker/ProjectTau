package com.projecttau.defaults.commands;

import com.projecttau.bootstrap.Bootstrap;
import net.minestom.server.command.builder.Command;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SaveWorldCommand extends Command {
    public SaveWorldCommand() {
        super("saveworld");
        setDefaultExecutor((sender, context) -> {
            if(sender.isPlayer())
                if(sender.asPlayer().getPermissionLevel()<2)
                    return;
            Bootstrap.getInstanceContainer().saveChunksToStorage();
            sender.sendMessage("Saving world.");
        });
    }
}
