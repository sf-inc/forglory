package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.block.EssenceInfuser;
import com.github.galatynf.forglory.block.QuickFireBlock;
import com.github.galatynf.forglory.block.WittyDirt;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class BlockRegistry {
    private BlockRegistry() {
    }

    public static final Block essenceInfuser = new EssenceInfuser(AbstractBlock.Settings.create()
            .mapColor(MapColor.LIGHT_GRAY)
            .requiresTool()
            .strength(50.0F, 6.0F)
            .luminance(state -> 8)
            .sounds(BlockSoundGroup.LANTERN));
    // TODO: Give item rarity
    public static final Item essenceInfuserItem = new BlockItem(essenceInfuser, new Item.Settings());

    public static final QuickFireBlock quickFireBlock = new QuickFireBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .replaceable()
            .noCollision()
            .breakInstantly()
            .luminance(state -> 15)
            .sounds(BlockSoundGroup.WOOL)
            .pistonBehavior(PistonBehavior.DESTROY));

    public static final Block wittyDirt = new WittyDirt(AbstractBlock.Settings.create()
            .mapColor(MapColor.DIRT_BROWN)
            .requiresTool()
            .strength(0.7F)
            .sounds(BlockSoundGroup.GRAVEL)
            .luminance(state -> 1)
            .ticksRandomly());

    public static void init() {
        Registry.register(Registries.BLOCK, Forglory.id("essence_infuser"), essenceInfuser);
        Registry.register(Registries.ITEM, Forglory.id("essence_infuser"), essenceInfuserItem);

        Registry.register(Registries.BLOCK, Forglory.id("quick_fire"), quickFireBlock);
        Registry.register(Registries.BLOCK, Forglory.id("witty_dirt"), wittyDirt);
    }
}
