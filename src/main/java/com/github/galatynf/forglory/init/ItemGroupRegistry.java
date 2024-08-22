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
                    entries.add(ItemRegistry.DAMAGE_GEM);
                    entries.add(ItemRegistry.SMITE_GEM);
                    entries.add(ItemRegistry.STRENGTH_GEM);
                    entries.add(ItemRegistry.FIRE_TRAIL_GEM);
                    entries.add(ItemRegistry.MACHINE_GUN_GEM);
                    entries.add(ItemRegistry.DAMAGE_SLOWED_GEM);
                    entries.add(ItemRegistry.SUPER_SHIELD_GEM);
                    entries.add(ItemRegistry.FIREWORKER_GEM);
                    entries.add(ItemRegistry.FIRE_ZONE_GEM);
                    entries.add(ItemRegistry.INSTANT_KILL_GEM);

                    entries.add(ItemRegistry.HEAL_GEM);
                    entries.add(ItemRegistry.RESISTANCE_GEM);
                    entries.add(ItemRegistry.HEALING_FIST_GEM);
                    entries.add(ItemRegistry.SHIELD_RESISTANCE_GEM);
                    entries.add(ItemRegistry.HEAL_TRAIL_GEM);
                    entries.add(ItemRegistry.LAST_STAND_GEM);

                    entries.add(ItemRegistry.MISC_GEM);
                    entries.add(ItemRegistry.ARROW_COMBO_GEM);
                    entries.add(ItemRegistry.BLOODLUST_GEM);
                    entries.add(ItemRegistry.DOG_GEM);
                    entries.add(ItemRegistry.KNOCKBACK_FIST_GEM);
                    entries.add(ItemRegistry.MOUNTAIN_GEM);
                    entries.add(ItemRegistry.BEES_GEM);
                    entries.add(ItemRegistry.INVISIBLE_GEM);
                    entries.add(ItemRegistry.UNDEAD_ARMY_GEM);

                    entries.add(ItemRegistry.MOBILITY_GEM);
                    entries.add(ItemRegistry.NO_HUNGER_GEM);
                    entries.add(ItemRegistry.SPEED_GEM);
                    entries.add(ItemRegistry.DASH_GEM);
                    entries.add(ItemRegistry.JUMP_BOOST_GEM);
                    entries.add(ItemRegistry.STRIDERS_GRACE_GEM);

                    entries.add(ItemRegistry.ANTI_GEM_I);
                    entries.add(ItemRegistry.ANTI_GEM_II);
                    entries.add(ItemRegistry.ANTI_GEM_III);
                    entries.add(ItemRegistry.ANTI_GEM_IV);

                    if (context.hasPermissions()) {
                        entries.add(ItemRegistry.DEBUG_ITEM);
                    }
                }))
                .build());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.add(ItemRegistry.ESSENCE));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.add(ItemRegistry.ESSENCE_INFUSER));
    }
}
