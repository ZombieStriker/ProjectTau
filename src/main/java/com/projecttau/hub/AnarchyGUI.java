package com.projecttau.hub;

import com.projecttau.defaults.inventory.InventoryGUI;

public final class AnarchyGUI extends InventoryGUI {


    static final AnarchyGUI GUI = new AnarchyGUI(); // since everything is immutable we can do that.

    private AnarchyGUI() {
        super("Branch types > Anarchy Games", 6);
    }
}
