package com.projecttau.hub;

import com.projecttau.defaults.inventory.Button;
import com.projecttau.defaults.inventory.InventoryGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

public final class ServersGUI extends InventoryGUI {

    private static final Button SMP = Button.button(ItemStack
            .builder(Material.GRASS_BLOCK)
            .displayName(Component.text("[SMP]").color(TextColor.color(170, 255, 173)))
            .build())
            .opens(SmpGUI.GUI)
            .cancelClick()
            .build();

    private static final Button CIVILIZATION = Button.button(ItemStack
            .builder(Material.CRAFTING_TABLE)
            .displayName(Component.text("[Civilization]").color(TextColor.color(242, 252, 255)))
            .build())
            .cancelClick()
            .opens(CivilizationGUI.GUI)
            .build();

    private static final Button MINIGAMES = Button.button(ItemStack
            .builder(Material.NOTE_BLOCK)
            .displayName(Component.text("[MiniGames]").color(TextColor.color(255, 130, 22)))
            .build())
            .cancelClick()
            .opens(MinigameGUI.GUI)
            .build();
    private static final Button RPGS = Button.button(ItemStack
            .builder(Material.MOJANG_BANNER_PATTERN)
            .displayName(Component.text("[Roleplaying Games (RPGs)]").color(TextColor.color(194, 167, 255)))
            .build())
            .cancelClick()
            .opens(RolePlayingGUI.GUI)
            .build();
    private static final Button PVP = Button.button(ItemStack
            .builder(Material.DIAMOND_SWORD)
            .displayName(Component.text("[PVP]").color(TextColor.color(21, 68, 255)))
            .build())
            .cancelClick()
            .opens(PVPGUI.GUI)
            .build();
    private static final Button CREATIVE = Button.button(ItemStack
            .builder(Material.OAK_PLANKS)
            .displayName(Component.text("[Creative Building]").color(TextColor.color(111, 238, 255)))
            .build())
            .cancelClick()
            .opens(CreativeGUI.GUI)
            .build();
    private static final Button ANARCHY = Button.button(ItemStack
            .builder(Material.OBSIDIAN)
            .displayName(Component.text("[Anarchy Servers]").color(TextColor.color(255, 15, 49)))
            .build())
            .cancelClick()
            .opens(AnarchyGUI.GUI)
            .build();
    private static final ServersGUI GUI = new ServersGUI(); // since everything is immutable we can do that.

    private ServersGUI() {
        super("Branch types", 6);
        addButton(10, ANARCHY);
        addButton(11, PVP);
        addButton(12, SMP);
        addButton(13, MINIGAMES);
        addButton(14, CIVILIZATION);
        addButton(15, RPGS);
        addButton(16, CREATIVE);
    }

    public static void open(@NotNull Player player) {
        GUI.display(player);
    }

}
