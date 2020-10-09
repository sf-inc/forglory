package com.github.galatynf.forglory;

import com.github.galatynf.forglory.blocks.EssenceInfuser;
import com.github.galatynf.forglory.items.tier0.DamageGem;
import com.github.galatynf.forglory.items.tier0.HealGem;
import com.github.galatynf.forglory.items.tier0.MiscGem;
import com.github.galatynf.forglory.items.tier0.MobilityGem;
import com.github.galatynf.forglory.items.tier1.SpeedGem;
import com.github.galatynf.forglory.items.tier2.StrengthGem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Forglory implements ModInitializer {
    public static final ItemGroup forGlory = FabricItemGroupBuilder.create(
            new Identifier("forglory", "gems"))
            .icon(() -> new ItemStack(Items.QUARTZ))
            .build();

    public static final Block essenceInfuser = new EssenceInfuser(FabricBlockSettings.of(Material.METAL).hardness(50.0f).lightLevel(15).sounds(BlockSoundGroup.GLASS));
    public static final Item essenceInfuserItem = new BlockItem(essenceInfuser, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final DamageGem damageGem = new DamageGem(new Item.Settings().group(forGlory));
    public static final HealGem healGem = new HealGem(new Item.Settings().group(forGlory));
    public static final MiscGem miscGem = new MiscGem(new Item.Settings().group(forGlory));
    public static final MobilityGem mobilityGem = new MobilityGem(new Item.Settings().group(forGlory));

    public static final SpeedGem speedGem = new SpeedGem(new Item.Settings().group(forGlory));
    
    public static final StrengthGem strengthGem = new StrengthGem(new Item.Settings().group(forGlory));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("forglory", "essence_infuser"), essenceInfuser);
        Registry.register(Registry.ITEM, new Identifier("forglory", "essence_infuser"), new BlockItem(essenceInfuser, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_gem"), damageGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "heal_gem"), healGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "misc_gem"), miscGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "mobility_gem"), mobilityGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "speed_gem"), speedGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "strength_gem"), strengthGem);
    }
}
