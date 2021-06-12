package com.projecttau.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.projecttau.ServerConnector;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.condition.InventoryCondition;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Represents a button in a GUI menu
 */
public final class Button {

    /**
     * An immutable list containing all actions for this button
     */
    private final ImmutableList<InventoryCondition> onClick;

    /**
     * The item stack representing this button
     */
    private final ItemStack itemStack;

    /**
     * Creates a new Button with the actions and the item stack.
     *
     * @param onClick   Button actions
     * @param itemStack The item stack
     */
    public Button(@NotNull ImmutableList<InventoryCondition> onClick, @NotNull ItemStack itemStack) {
        this.onClick = Preconditions.checkNotNull(onClick, "onClick");
        this.itemStack = Preconditions.checkNotNull(itemStack, "itemStack");
    }

    /**
     * Creates a new button builder with the specified {@link ItemStack}
     *
     * @param itemStack the button item stack
     * @return A new builder
     */
    public static Builder button(@NotNull ItemStack itemStack) {
        Preconditions.checkNotNull(itemStack, "itemStack");
        return new Builder(itemStack);
    }

    /**
     * Creates a button that does nothing
     *
     * @param itemStack the button item stack
     * @return The created button
     */
    public static Button nothing(@NotNull ItemStack itemStack) {
        Preconditions.checkNotNull(itemStack);
        return new Button(ImmutableList.of(), itemStack);
    }

    /**
     * Returns the button's item stack
     *
     * @return The button's item
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Returns all the actions of the button
     *
     * @return The button actions
     */
    public ImmutableList<InventoryCondition> onClick() {
        return onClick;
    }

    /**
     * Represents a button builder
     */
    public static class Builder {

        /**
         * The button actions
         */
        private final ImmutableList.Builder<InventoryCondition> onClick = ImmutableList.builder();

        /**
         * The button item
         */
        private final ItemStack item;

        /**
         * Creates a new Builder
         *
         * @param item The item stack of the button
         */
        private Builder(ItemStack item) {
            this.item = item;
        }

        /**
         * Adds an action to handle this button click
         *
         * @param condition The condition callback
         * @return This builder
         */
        public Builder handle(@NotNull InventoryCondition condition) {
            Preconditions.checkNotNull(condition, "condition");
            onClick.add(condition);
            return this;
        }

        /**
         * Opens another GUI menu when this is clicked
         *
         * @param inventoryGUI GUI to open
         * @return This builder
         */
        public Builder opens(@NotNull InventoryGUI inventoryGUI) {
            Preconditions.checkNotNull(inventoryGUI, "inventoryGUI");
            return handle((player, slot, clickType, result) -> inventoryGUI.display(player));
        }


        /**
         * Opens another GUI menu when this is clicked
         *
         * @param inventoryGUI supplier of the GUI to open
         * @return This builder
         */
        public Builder opens(@NotNull Supplier<InventoryGUI> inventoryGUI) {
            Preconditions.checkNotNull(inventoryGUI, "inventoryGUI");
            return handle((player, slot, clickType, result) -> inventoryGUI.get().display(player));
        }


        /**
         * Opens another inventory menu when this is clicked
         *
         * @param inventory Inventory to open
         * @return This builder
         */
        public Builder opens(@NotNull Inventory inventory) {
            Preconditions.checkNotNull(inventory, "inventory");
            return handle((player, slot, clickType, result) -> player.openInventory(inventory));
        }

        /**
         * Connects to another server when clicked
         *
         * @param serverName Server name to warp to
         * @return This builder
         */
        public Builder connectTo(@NotNull String serverName) {
            Preconditions.checkNotNull(serverName, "serverName");
            return handle((player, slot, clickType, inventoryConditionResult) -> ServerConnector.connectToServer(player, serverName));
        }

        /**
         * Closes the GUI on click
         *
         * @return This builder
         */
        public Builder closeOnClick() {
            return handle((player, slot, clickType, inventoryConditionResult) -> player.closeInventory());
        }

        /**
         * Cancels the click to make sure the player doesn't get the item
         * from the GUI.
         *
         * @return This builder
         */
        public Builder cancelClick() {
            return handle((player, slot, clickType, inventoryConditionResult) -> inventoryConditionResult.setCancel(true));
        }

        /**
         * Builds the button
         *
         * @return The newly created button
         */
        public @NotNull Button build() {
            return new Button(onClick.build(), item);
        }

    }
}
