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

public class ItemGroupsInit {
    public static final RegistryKey<ItemGroup> GEMS = RegistryKey.of(RegistryKeys.ITEM_GROUP, Forglory.id("gems"));

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, GEMS, FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.forglory.gems"))
                .icon(() -> new ItemStack(Items.QUARTZ))
                .entries(((context, entries) -> {
                    entries.add(GemsInit.damageGem);
                    entries.add(GemsInit.smiteGem);
                    entries.add(GemsInit.strengthGem);
                    entries.add(GemsInit.fireTrailGem);
                    entries.add(GemsInit.machineGunGem);
                    entries.add(GemsInit.damageSlowedGem);
                    entries.add(GemsInit.superShieldGem);
                    entries.add(GemsInit.fireworkerGem);
                    entries.add(GemsInit.fireZoneGem);
                    entries.add(GemsInit.instantKillGem);

                    entries.add(GemsInit.healGem);
                    entries.add(GemsInit.resistanceGem);
                    entries.add(GemsInit.healingFistGem);
                    entries.add(GemsInit.shieldResistanceGem);
                    entries.add(GemsInit.healTrailGem);
                    entries.add(GemsInit.lastStandGem);

                    entries.add(GemsInit.miscGem);
                    entries.add(GemsInit.arrowComboGem);
                    entries.add(GemsInit.bloodlustGem);
                    entries.add(GemsInit.dogGem);
                    entries.add(GemsInit.knockbackFistGem);
                    entries.add(GemsInit.mountainGem);
                    entries.add(GemsInit.beesGem);
                    entries.add(GemsInit.invisibleGem);
                    entries.add(GemsInit.undeadArmyGem);

                    entries.add(GemsInit.mobilityGem);
                    entries.add(GemsInit.noHungerGem);
                    entries.add(GemsInit.speedGem);
                    entries.add(GemsInit.dashGem);
                    entries.add(GemsInit.jumpBoostGem);
                    entries.add(GemsInit.stridersGraceGem);

                    entries.add(GemsInit.antiGemI);
                    entries.add(GemsInit.antiGemII);
                    entries.add(GemsInit.antiGemIII);
                    entries.add(GemsInit.antiGemIV);

                    if (context.hasPermissions()) {
                        entries.add(ItemsInit.debugItem);
                    }
                }))
                .build());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.add(ItemsInit.essence));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.add(BlocksInit.essenceInfuserItem));
    }
}
