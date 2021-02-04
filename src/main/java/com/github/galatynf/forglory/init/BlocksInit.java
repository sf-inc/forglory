package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.blocks.EssenceInfuser;
import com.github.galatynf.forglory.blocks.QuickFireBlock;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksInit {
    private BlocksInit() {
    }

    public static final Block essenceInfuser = new EssenceInfuser(FabricBlockSettings
            .of(Material.STONE, MaterialColor.LIGHT_GRAY).requiresTool().strength(50.F, 6.F).luminance(8).sounds(BlockSoundGroup.LANTERN));
    public static final Item essenceInfuserItem = new BlockItem(essenceInfuser, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final QuickFireBlock quickFireBlock = new QuickFireBlock(FabricBlockSettings
            .of(Material.FIRE, MaterialColor.PURPLE).breakInstantly().noCollision().luminance(15).sounds(BlockSoundGroup.WOOL));

    public static void initClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(quickFireBlock, RenderLayer.getCutout());
    }

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("forglory", "essence_infuser"), essenceInfuser);
        Registry.register(Registry.ITEM, new Identifier("forglory", "essence_infuser"), essenceInfuserItem);

        Registry.register(Registry.BLOCK, new Identifier("forglory", "quick_fire"), quickFireBlock);
    }
}
