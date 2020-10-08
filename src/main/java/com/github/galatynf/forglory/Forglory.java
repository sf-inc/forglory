package com.github.galatynf.forglory;

import com.github.galatynf.forglory.items.tier0.DamageGem;
import com.github.galatynf.forglory.items.tier0.HealGem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Forglory implements ModInitializer {
    public static final ItemGroup forGlory = FabricItemGroupBuilder.create(
            new Identifier("forglory", "gems"))
            .icon(() -> new ItemStack(Items.QUARTZ))
            .build();

    public static final DamageGem damageGem = new DamageGem(new Item.Settings().group(forGlory));
    public static final HealGem healGem = new HealGem(new Item.Settings().group(forGlory));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_gem"), damageGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_gem"), healGem);
    }
}
