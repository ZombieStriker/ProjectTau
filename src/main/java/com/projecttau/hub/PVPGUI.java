package com.projecttau.hub;

import com.projecttau.defaults.inventory.InventoryGUI;

public final class PVPGUI extends InventoryGUI {


    static final PVPGUI GUI = new PVPGUI(); // since everything is immutable we can do that.

    private PVPGUI() {
        super("Branch types > PVP Games", 6);
    }
}
