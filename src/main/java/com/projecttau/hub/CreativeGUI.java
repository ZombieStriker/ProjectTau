package com.projecttau.hub;

import com.projecttau.defaults.inventory.InventoryGUI;

public final class CreativeGUI extends InventoryGUI {


    static final CreativeGUI GUI = new CreativeGUI(); // since everything is immutable we can do that.

    private CreativeGUI() {
        super("Branch types > Creative Building Games", 6);
    }
}
