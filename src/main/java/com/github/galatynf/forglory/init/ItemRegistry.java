package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.item.AntiGem;
import com.github.galatynf.forglory.item.DebugItem;
import com.github.galatynf.forglory.item.PoweredGem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemRegistry {
    private ItemRegistry() {
    }

    public static final Item ESSENCE_INFUSER = new BlockItem(BlockRegistry.ESSENCE_INFUSER, new Item.Settings());

    public static final DebugItem DEBUG_ITEM = new DebugItem(new Item.Settings());
    public static final Item ESSENCE = new Item(new Item.Settings());

    public static final AntiGem ANTI_GEM_I = new AntiGem(new Item.Settings().maxDamage(1), Tier.TIER1);
    public static final AntiGem ANTI_GEM_II = new AntiGem(new Item.Settings().maxDamage(2), Tier.TIER2);
    public static final AntiGem ANTI_GEM_III = new AntiGem(new Item.Settings().maxDamage(3), Tier.TIER3);
    public static final AntiGem ANTI_GEM_IV = new AntiGem(new Item.Settings().maxDamage(4), Tier.TIER4);

    public static final Item DAMAGE_GEM = new Item(new Item.Settings());
    public static final PoweredGem SMITE_GEM = new PoweredGem(new Item.Settings(), Feats.SMITE, DAMAGE_GEM);
    public static final PoweredGem STRENGTH_GEM = new PoweredGem(new Item.Settings(), Feats.STRENGTH, DAMAGE_GEM);
    public static final PoweredGem FIRE_TRAIL_GEM = new PoweredGem(new Item.Settings(), Feats.FIRE_TRAIL, DAMAGE_GEM);
    public static final PoweredGem MACHINE_GUN_GEM = new PoweredGem(new Item.Settings(), Feats.MACHINE_GUN, DAMAGE_GEM);
    public static final PoweredGem DAMAGE_SLOWED_GEM = new PoweredGem(new Item.Settings(), Feats.DAMAGE_SLOWED, DAMAGE_GEM);
    public static final PoweredGem SUPER_SHIELD_GEM = new PoweredGem(new Item.Settings(), Feats.SUPER_SHIELD, DAMAGE_GEM);
    public static final PoweredGem FIREWORKER_GEM = new PoweredGem(new Item.Settings(), Feats.FIREWORKER, DAMAGE_GEM);
    public static final PoweredGem FIRE_ZONE_GEM = new PoweredGem(new Item.Settings(), Feats.FIRE_ZONE, DAMAGE_GEM);
    public static final PoweredGem INSTANT_KILL_GEM = new PoweredGem(new Item.Settings(), Feats.INSTANT_KILL, DAMAGE_GEM);

    public static final Item HEAL_GEM = new Item(new Item.Settings());
    public static final PoweredGem RESISTANCE_GEM = new PoweredGem(new Item.Settings(), Feats.RESISTANCE, HEAL_GEM);
    public static final PoweredGem HEALING_FIST_GEM = new PoweredGem(new Item.Settings(), Feats.HEALING_FIST, HEAL_GEM);
    public static final PoweredGem SHIELD_RESISTANCE_GEM = new PoweredGem(new Item.Settings(), Feats.SHIELD_RESISTANCE, HEAL_GEM);
    public static final PoweredGem HEAL_TRAIL_GEM = new PoweredGem(new Item.Settings(), Feats.HEAL_TRAIL, HEAL_GEM);
    public static final PoweredGem LAST_STAND_GEM = new PoweredGem(new Item.Settings(), Feats.LAST_STAND, HEAL_GEM);

    public static final Item MISC_GEM = new Item(new Item.Settings());
    public static final PoweredGem ARROW_COMBO_GEM = new PoweredGem(new Item.Settings(), Feats.ARROW_COMBO, MISC_GEM);
    public static final PoweredGem BLOODLUST_GEM = new PoweredGem(new Item.Settings(), Feats.BLOODLUST, MISC_GEM);
    public static final PoweredGem DOG_GEM = new PoweredGem(new Item.Settings(), Feats.DOG, MISC_GEM);
    public static final PoweredGem KNOCKBACK_FIST_GEM = new PoweredGem(new Item.Settings(), Feats.KNOCKBACK_FIST, MISC_GEM);
    public static final PoweredGem MOUNTAIN_GEM = new PoweredGem(new Item.Settings(), Feats.MOUNTAIN, MISC_GEM);
    public static final PoweredGem BEES_GEM = new PoweredGem(new Item.Settings(), Feats.BEES, MISC_GEM);
    public static final PoweredGem INVISIBLE_GEM = new PoweredGem(new Item.Settings(), Feats.INVISIBLE, MISC_GEM);
    public static final PoweredGem UNDEAD_ARMY_GEM = new PoweredGem(new Item.Settings(), Feats.UNDEAD_ARMY, MISC_GEM);

    public static final Item MOBILITY_GEM = new Item(new Item.Settings());
    public static final PoweredGem NO_HUNGER_GEM = new PoweredGem(new Item.Settings(), Feats.NO_HUNGER, MOBILITY_GEM);
    public static final PoweredGem SPEED_GEM = new PoweredGem(new Item.Settings(), Feats.SPEED, MOBILITY_GEM);
    public static final PoweredGem DASH_GEM = new PoweredGem(new Item.Settings(), Feats.DASH, MOBILITY_GEM);
    public static final PoweredGem JUMP_BOOST_GEM = new PoweredGem(new Item.Settings(), Feats.JUMP_BOOST, MOBILITY_GEM);
    public static final PoweredGem STRIDERS_GRACE_GEM = new PoweredGem(new Item.Settings(), Feats.STRIDERS_GRACE, MOBILITY_GEM);

    public static void init() {
        Registry.register(Registries.ITEM, Forglory.id("essence_infuser"), ESSENCE_INFUSER);

        Registry.register(Registries.ITEM, Forglory.id("debug_item"), DEBUG_ITEM);
        Registry.register(Registries.ITEM, Forglory.id("essence"), ESSENCE);

        Registry.register(Registries.ITEM, Forglory.id("anti_gem1"), ANTI_GEM_I);
        Registry.register(Registries.ITEM, Forglory.id("anti_gem2"), ANTI_GEM_II);
        Registry.register(Registries.ITEM, Forglory.id("anti_gem3"), ANTI_GEM_III);
        Registry.register(Registries.ITEM, Forglory.id("anti_gem4"), ANTI_GEM_IV);

        Registry.register(Registries.ITEM, Forglory.id("damage_gem"), DAMAGE_GEM);
        Registry.register(Registries.ITEM, Forglory.id("smite_gem"), SMITE_GEM);
        Registry.register(Registries.ITEM, Forglory.id("strength_gem"), STRENGTH_GEM);
        Registry.register(Registries.ITEM, Forglory.id("fire_trail_gem"), FIRE_TRAIL_GEM);
        Registry.register(Registries.ITEM, Forglory.id("machine_gun_gem"), MACHINE_GUN_GEM);
        Registry.register(Registries.ITEM, Forglory.id("damage_slowed_gem"), DAMAGE_SLOWED_GEM);
        Registry.register(Registries.ITEM, Forglory.id("super_shield_gem"), SUPER_SHIELD_GEM);
        Registry.register(Registries.ITEM, Forglory.id("fireworker_gem"), FIREWORKER_GEM);
        Registry.register(Registries.ITEM, Forglory.id("fire_zone_gem"), FIRE_ZONE_GEM);
        Registry.register(Registries.ITEM, Forglory.id("instant_kill_gem"), INSTANT_KILL_GEM);

        Registry.register(Registries.ITEM, Forglory.id("heal_gem"), HEAL_GEM);
        Registry.register(Registries.ITEM, Forglory.id("resistance_gem"), RESISTANCE_GEM);
        Registry.register(Registries.ITEM, Forglory.id("healing_fist_gem"), HEALING_FIST_GEM);
        Registry.register(Registries.ITEM, Forglory.id("shield_resistance_gem"), SHIELD_RESISTANCE_GEM);
        Registry.register(Registries.ITEM, Forglory.id("heal_trail_gem"), HEAL_TRAIL_GEM);
        Registry.register(Registries.ITEM, Forglory.id("last_stand_gem"), LAST_STAND_GEM);

        Registry.register(Registries.ITEM, Forglory.id("misc_gem"), MISC_GEM);
        Registry.register(Registries.ITEM, Forglory.id("arrow_combo_gem"), ARROW_COMBO_GEM);
        Registry.register(Registries.ITEM, Forglory.id("bloodlust_gem"), BLOODLUST_GEM);
        Registry.register(Registries.ITEM, Forglory.id("dog_gem"), DOG_GEM);
        Registry.register(Registries.ITEM, Forglory.id("knockback_fist_gem"), KNOCKBACK_FIST_GEM);
        Registry.register(Registries.ITEM, Forglory.id("mountain_gem"), MOUNTAIN_GEM);
        Registry.register(Registries.ITEM, Forglory.id("bees_gem"), BEES_GEM);
        Registry.register(Registries.ITEM, Forglory.id("invisible_gem"), INVISIBLE_GEM);
        Registry.register(Registries.ITEM, Forglory.id("undead_army_gem"), UNDEAD_ARMY_GEM);

        Registry.register(Registries.ITEM, Forglory.id("mobility_gem"), MOBILITY_GEM);
        Registry.register(Registries.ITEM, Forglory.id("no_hunger_gem"), NO_HUNGER_GEM);
        Registry.register(Registries.ITEM, Forglory.id("speed_gem"), SPEED_GEM);
        Registry.register(Registries.ITEM, Forglory.id("dash_gem"), DASH_GEM);
        Registry.register(Registries.ITEM, Forglory.id("jump_boost_gem"), JUMP_BOOST_GEM);
        Registry.register(Registries.ITEM, Forglory.id("striders_grace_gem"), STRIDERS_GRACE_GEM);
    }
}
