package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ItemGroupRegistry {
    public static final RegistryKey<ItemGroup> GEMS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Forglory.id("gems"));

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, GEMS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.forglory.gems"))
                .icon(() -> new ItemStack(Items.QUARTZ))
                .entries(((context, entries) -> {
                    entries.add(ItemRegistry.damageGem);
                    entries.add(ItemRegistry.smiteGem);
                    entries.add(ItemRegistry.strengthGem);
                    entries.add(ItemRegistry.fireTrailGem);
                    entries.add(ItemRegistry.machineGunGem);
                    entries.add(ItemRegistry.damageSlowedGem);
                    entries.add(ItemRegistry.superShieldGem);
                    entries.add(ItemRegistry.fireworkerGem);
                    entries.add(ItemRegistry.fireZoneGem);
                    entries.add(ItemRegistry.instantKillGem);

                    entries.add(ItemRegistry.healGem);
                    entries.add(ItemRegistry.resistanceGem);
                    entries.add(ItemRegistry.healingFistGem);
                    entries.add(ItemRegistry.shieldResistanceGem);
                    entries.add(ItemRegistry.healTrailGem);
                    entries.add(ItemRegistry.lastStandGem);

                    entries.add(ItemRegistry.miscGem);
                    entries.add(ItemRegistry.arrowComboGem);
                    entries.add(ItemRegistry.bloodlustGem);
                    entries.add(ItemRegistry.dogGem);
                    entries.add(ItemRegistry.knockbackFistGem);
                    entries.add(ItemRegistry.mountainGem);
                    entries.add(ItemRegistry.beesGem);
                    entries.add(ItemRegistry.invisibleGem);
                    entries.add(ItemRegistry.undeadArmyGem);

                    entries.add(ItemRegistry.mobilityGem);
                    entries.add(ItemRegistry.noHungerGem);
                    entries.add(ItemRegistry.speedGem);
                    entries.add(ItemRegistry.dashGem);
                    entries.add(ItemRegistry.jumpBoostGem);
                    entries.add(ItemRegistry.stridersGraceGem);

                    entries.add(ItemRegistry.antiGemI);
                    entries.add(ItemRegistry.antiGemII);
                    entries.add(ItemRegistry.antiGemIII);
                    entries.add(ItemRegistry.antiGemIV);

                    if (context.hasPermissions()) {
                        entries.add(ItemRegistry.debugItem);
                    }
                }))
                .build());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.add(ItemRegistry.essence));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.add(ItemRegistry.essenceInfuser));
    }
}
