package com.projecttau.hub;

import com.projecttau.defaults.inventory.Button;
import com.projecttau.defaults.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public final class MinigameGUI extends InventoryGUI {

    private static final Button FARMCRAFT = Button.button(ItemStack
            .builder(Material.CRAFTING_TABLE)
            .displayName(Component.text("[FarmCraft]").color(TextColor.color(54, 255, 57)))
            .build())
            .cancelClick()
            .connectTo("farmcraft")
            .build();

    static final MinigameGUI GUI = new MinigameGUI(); // since everything is immutable we can do that.

    private MinigameGUI() {
        super("Branch types > Minigames", 6);
        addButton(0, FARMCRAFT);
    }
}
