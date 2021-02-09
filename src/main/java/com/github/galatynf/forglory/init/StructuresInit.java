package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.structures.MyFeature;
import com.github.galatynf.forglory.structures.MyGenerator;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Arrays;
import java.util.List;

public class StructuresInit {
    private StructuresInit() {
    }

    public static final StructurePieceType MY_PIECE = MyGenerator.MyPiece::new;

    private static final StructureFeature<DefaultFeatureConfig> MY_STRUCTURE = new MyFeature(DefaultFeatureConfig.CODEC);

    private static final ConfiguredStructureFeature<?, ?> MY_CONFIGURED = MY_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    private static final List<Biome.Category> blacklistedCategories = Arrays
            .asList(Biome.Category.NONE,
                    Biome.Category.NETHER,
                    Biome.Category.THEEND,
                    Biome.Category.OCEAN,
                    Biome.Category.RIVER,
                    Biome.Category.DESERT,
                    Biome.Category.MESA,
                    Biome.Category.EXTREME_HILLS,
                    Biome.Category.ICY,
                    Biome.Category.MUSHROOM);

    public static void init() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("forglory", "my_piece"), MY_PIECE);
        FabricStructureBuilder.create(new Identifier("forglory", "lost_sanctuary"), MY_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(ModConfig.get().generalConfig.struct_max_distance, ModConfig.get().generalConfig.struct_min_distance, 42685)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN,
                new Identifier("forglory", "my_structure"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), MY_CONFIGURED);

        BiomeModifications
                .create(new Identifier("forglory", "lost_sanctuary"))
                .add(
                        ModificationPhase.ADDITIONS,
                        context -> !blacklistedCategories.contains(context.getBiome().getCategory()),
                        context -> context.getGenerationSettings().addBuiltInStructure(MY_CONFIGURED));
    }
}
