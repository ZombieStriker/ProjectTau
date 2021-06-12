package com.projecttau;

import com.projecttau.inventory.Button;
import com.projecttau.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public final class SmpGUI extends InventoryGUI {

    private static final Button LAB_CRAFT = Button.button(ItemStack
            .builder(Material.WHITE_CONCRETE)
            .displayName(Component.text("[LabCraft]").color(TextColor.color(255, 133, 20)))
            .build())
            .cancelClick()
            .connectTo("labcraft")
            .build();

    static final SmpGUI GUI = new SmpGUI(); // since everything is immutable we can do that.

    private SmpGUI() {
        super("Branch types > SMPs", 6);
        addButton(0, LAB_CRAFT);
    }
}
