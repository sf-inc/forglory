package com.github.galatynf.forglory.data;

import com.github.galatynf.forglory.init.BiomeTagRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class BiomeTagProvider extends FabricTagProvider<Biome> {
    public BiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(BiomeTagRegistry.LOST_SANCTUARY_HAS_STRUCTURE)
                .forceAddTag(BiomeTags.IS_FOREST)
                .forceAddTag(BiomeTags.IS_HILL)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .add(BiomeKeys.CHERRY_GROVE)
                .add(BiomeKeys.MEADOW)
                .add(BiomeKeys.PLAINS)
                .add(BiomeKeys.SAVANNA)
                .add(BiomeKeys.SNOWY_PLAINS)
                .add(BiomeKeys.SPARSE_JUNGLE)
                .add(BiomeKeys.STONY_SHORE)
                .add(BiomeKeys.SUNFLOWER_PLAINS);
    }
}
