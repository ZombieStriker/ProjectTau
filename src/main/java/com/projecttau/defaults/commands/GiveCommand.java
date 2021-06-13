package com.projecttau.defaults.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.item.ItemStack;

public class GiveCommand extends Command {

    public GiveCommand(){
        super("give");
        setDefaultExecutor((sender, context) -> {
            if(sender.isPlayer()){
                if(sender.asPlayer().getPermissionLevel()<2)
                    return;
            }
            sender.sendMessage("Usage: /give <item> <Optional: Player>");
        });

        var item = ArgumentType.ItemStack("itemstack");

        addSyntax((sender, context) -> {
            if(sender.isPlayer()) {
                if (sender.asPlayer().getPermissionLevel() < 2)
                    return;
                final ItemStack itemStack = context.get(item);
                if (sender.isPlayer()) {
                    sender.asPlayer().getInventory().addItemStack(itemStack);
                    sender.sendMessage("Giving " + sender.asPlayer().getName() + " 1x " + itemStack.getMaterial().getName());
                } else {
                    sender.sendMessage("Must be a player to use this command");
                }
            }
        });

    }

}
