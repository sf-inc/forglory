package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.items.DebugItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsInit {
    private ItemsInit() {}

    public static final ItemGroup forGlory = FabricItemGroupBuilder.create(
            new Identifier("forglory", "gems"))
            .icon(() -> new ItemStack(Items.QUARTZ))
            .build();
    public static final Item.Settings settings = new Item.Settings().group(forGlory);

    public static final DebugItem debugItem = new DebugItem(settings);
    public static final Item essence = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static void init (){
        Registry.register(Registry.ITEM, new Identifier("forglory", "debug_item"), debugItem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "essence"), essence);
        GemsInit.init();
    }
}
