package com.projecttau.bootstrap;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.color.Color;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class GUIManager {

    public static Inventory mainGUI;
    public static Inventory civilizationsGUI;
    public static Inventory smpGUI;

    public static void init() {
        mainGUI = new Inventory(InventoryType.CHEST_6_ROW, "Branch types");
        civilizationsGUI = new Inventory(InventoryType.CHEST_6_ROW, "Branch types > Civilization");
        smpGUI = new Inventory(InventoryType.CHEST_6_ROW, "Branch types > SMPs");

       addMainInventoryCategory(getSmpGUI(),3, ItemStack.builder(Material.GRASS_BLOCK).displayName(Component.text("[SMP]").color(TextColor.color(255, 25, 255))).build());

        addMainInventoryCategory(getCivilizationsGUI(), 5, ItemStack.builder(Material.CRAFTING_TABLE).displayName(Component.text("[Civilization]").color(TextColor.color(255, 25, 255))).build());


        addSMPServer("labcraft", 0, ItemStack.builder(Material.WHITE_CONCRETE).displayName(Component.text("[LabCraft]").color(TextColor.color(255, 133, 20))).build());

        addCivilizationServer("civilization", 0, ItemStack.builder(Material.CRAFTING_TABLE).displayName(Component.text("[Civilizations]").color(TextColor.color(255, 113, 23))).build());
    }

    ;

    public static void addMainInventoryCategory(Inventory branchCatagory, int slot, ItemStack itemstack) {
        getMainGUI().setItemStack(slot, itemstack);
        getMainGUI().addInventoryCondition(((player, slot1, clickType, inventoryConditionResult) -> {
            if (slot1 == slot) {
                inventoryConditionResult.setCancel(true);
                player.openInventory(branchCatagory);
            }
        }));
    }

    public static void addSMPServer(String smpServer, int slot, ItemStack itemstack) {
        getSmpGUI().setItemStack(slot, itemstack);
        getSmpGUI().addInventoryCondition(((player, slot1, clickType, inventoryConditionResult) -> {
            if (slot1 == slot) {
                inventoryConditionResult.setCancel(true);
                ServerConnector.connectToServer(player, smpServer);
            }
        }));
    }

    public static void addCivilizationServer(String civilizationsserver, int slot, ItemStack itemstack) {
        getCivilizationsGUI().setItemStack(slot, itemstack);
        getCivilizationsGUI().addInventoryCondition(((player, slot1, clickType, inventoryConditionResult) -> {
            if (slot1 == slot) {
                inventoryConditionResult.setCancel(true);
                ServerConnector.connectToServer(player, civilizationsserver);
            }
        }));
    }

    public static Inventory getCivilizationsGUI() {
        return civilizationsGUI;
    }

    public static Inventory getMainGUI() {
        return mainGUI;
    }

    public static Inventory getSmpGUI() {
        return smpGUI;
    }
}
