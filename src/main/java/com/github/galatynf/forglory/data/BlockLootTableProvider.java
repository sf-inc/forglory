package com.github.galatynf.forglory.data;

import com.github.galatynf.forglory.block.EssenceInfuser;
import com.github.galatynf.forglory.init.BlockRegistry;
import com.github.galatynf.forglory.init.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {
    protected BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        this.addDrop(BlockRegistry.ESSENCE_INFUSER,
                LootTable.builder()
                        .pool(this.addSurvivesExplosionCondition(
                                ItemRegistry.ESSENCE_INFUSER,
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(ItemRegistry.ESSENCE_INFUSER))))
                        .pool(this.addSurvivesExplosionCondition(
                                ItemRegistry.ESSENCE,
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(ItemRegistry.ESSENCE)
                                                .conditionally(BlockStatePropertyLootCondition.builder(BlockRegistry.ESSENCE_INFUSER)
                                                        .properties(StatePredicate.Builder.create()
                                                                .exactMatch(EssenceInfuser.CHARGED, true)
                                                                .exactMatch(EssenceInfuser.INFINITE, true)))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))))
                        .pool(this.addSurvivesExplosionCondition(
                                ItemRegistry.ESSENCE,
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(ItemRegistry.ESSENCE)
                                                .conditionally(BlockStatePropertyLootCondition.builder(BlockRegistry.ESSENCE_INFUSER)
                                                        .properties(StatePredicate.Builder.create()
                                                                .exactMatch(EssenceInfuser.CHARGED, true)
                                                                .exactMatch(EssenceInfuser.INFINITE, false)))
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))))
                );
    }
}
