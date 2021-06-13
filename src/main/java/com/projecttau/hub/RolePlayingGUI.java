package com.projecttau.hub;

import com.projecttau.defaults.inventory.InventoryGUI;

public final class RolePlayingGUI extends InventoryGUI {


    static final RolePlayingGUI GUI = new RolePlayingGUI(); // since everything is immutable we can do that.

    private RolePlayingGUI() {
        super("Branch types > Roleplaying Games", 6);
    }
}
