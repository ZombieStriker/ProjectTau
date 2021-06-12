package com.projecttau.inventory;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.condition.InventoryCondition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InventoryGUI {

    private final Component title;
    private final InventoryType type;
    private final Map<Integer, Button> buttons = new HashMap<>();
    private final List<InventoryCondition> globalActions = new ArrayList<>();

    public InventoryGUI(Component title, int rows) {
        Preconditions.checkArgument(rows >= 1 && rows <= 6, "Invalid rows count: " + rows + ". This is not slots!");
        this.title = title;
        this.type = CHEST_SLOTS.get(rows);
    }

    public InventoryGUI(String title, int rows) {
        this(Component.text(title), rows);
    }

    protected void addGlobalAction(@Nullable InventoryCondition condition) {
        if (condition == null) return;
        globalActions.add(condition);
    }

    protected void addButton(int slot, @NotNull Button button) {
        buttons.put(slot, button);
    }

    protected Inventory createInventory() {
        Inventory inventory = new Inventory(type, title);
        buttons.forEach((slot, button) -> {
            inventory.setItemStack(slot, button.getItemStack());
            inventory.addInventoryCondition((player, slot1, clickType, result) -> {
                if (slot == slot1)
                    button.onClick().forEach(it -> it.accept(player, slot, clickType, result));
            });
        });
        globalActions.forEach(inventory::addInventoryCondition);
        return inventory;
    }

    public void display(@NotNull Player player) {
        Preconditions.checkNotNull(player, "player");
        player.openInventory(createInventory());
    }

    private static final Int2ObjectMap<InventoryType> CHEST_SLOTS = new Int2ObjectArrayMap<>();

    static {
        CHEST_SLOTS.put(1, InventoryType.CHEST_1_ROW);
        CHEST_SLOTS.put(2, InventoryType.CHEST_2_ROW);
        CHEST_SLOTS.put(3, InventoryType.CHEST_3_ROW);
        CHEST_SLOTS.put(4, InventoryType.CHEST_4_ROW);
        CHEST_SLOTS.put(5, InventoryType.CHEST_5_ROW);
        CHEST_SLOTS.put(6, InventoryType.CHEST_6_ROW);
    }

}
