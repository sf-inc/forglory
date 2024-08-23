package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class BiomeTagRegistry {
    public static final TagKey<Biome> LOST_SANCTUARY_HAS_STRUCTURE = TagKey.of(RegistryKeys.BIOME,
            Forglory.id("has_structure/lost_sanctuary"));

    public static void init() {
    }
}
