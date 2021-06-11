package com.projecttau.bootstrap;

import net.minestom.server.command.builder.Command;

public class TestSaveCommand extends Command {

    public TestSaveCommand() {
        super("save");

        // Executed if no other executor can be used
        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("You executed the command to save the world");
            Bootstrap.getInstanceContainer().saveChunksToStorage();
        });
    }
}
