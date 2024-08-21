package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.items.DebugItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemsInit {
    private ItemsInit() {
    }

    public static final DebugItem debugItem = new DebugItem(new Item.Settings());
    public static final Item essence = new Item(new Item.Settings());

    public static void init() {
        Registry.register(Registries.ITEM, Forglory.id("debug_item"), debugItem);
        Registry.register(Registries.ITEM, Forglory.id("essence"), essence);
        GemsInit.init();
    }
}
