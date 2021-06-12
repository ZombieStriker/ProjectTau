package com.projecttau;

import com.projecttau.inventory.Button;
import com.projecttau.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

public final class ServersGUI extends InventoryGUI {

    private static final Button SMP = Button.button(ItemStack
            .builder(Material.GRASS_BLOCK)
            .displayName(Component.text("[SMP]").color(TextColor.color(255, 25, 255)))
            .build())
            .opens(SmpGUI.GUI)
            .cancelClick()
            .build();

    private static final Button CIVILIZATION = Button.button(ItemStack
            .builder(Material.CRAFTING_TABLE)
            .displayName(Component.text("[Civilization]").color(TextColor.color(255, 25, 255)))
            .build())
            .cancelClick()
            .opens(CivilizationGUI.GUI)
            .build();

    private static final ServersGUI GUI = new ServersGUI(); // since everything is immutable we can do that.

    private ServersGUI() {
        super("Branch types", 6);
        addButton(3, SMP);
        addButton(5, CIVILIZATION);
    }

    public static void open(@NotNull Player player) {
        GUI.display(player);
    }

}
