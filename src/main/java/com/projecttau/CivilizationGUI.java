package com.projecttau;

import com.projecttau.inventory.Button;
import com.projecttau.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public final class CivilizationGUI extends InventoryGUI {

    private static final Button CIVILIZATION = Button.button(ItemStack
            .builder(Material.CRAFTING_TABLE)
            .displayName(Component.text("[Civilizations]").color(TextColor.color(255, 113, 23)))
            .build())
            .cancelClick()
            .connectTo("civilization")
            .build();

    static final CivilizationGUI GUI = new CivilizationGUI(); // since everything is immutable we can do that.

    private CivilizationGUI() {
        super("Branch types > Civilization", 6);
        addButton(0, CIVILIZATION);
    }
}
