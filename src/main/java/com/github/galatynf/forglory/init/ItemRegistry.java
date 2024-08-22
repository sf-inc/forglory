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

    // TODO: Give item rarity
    public static final Item essenceInfuser = new BlockItem(BlockRegistry.essenceInfuser, new Item.Settings());

    public static final DebugItem debugItem = new DebugItem(new Item.Settings());
    public static final Item essence = new Item(new Item.Settings());

    public static final AntiGem antiGemI = new AntiGem(new Item.Settings().maxDamage(1), Tier.TIER1);
    public static final AntiGem antiGemII = new AntiGem(new Item.Settings().maxDamage(2), Tier.TIER2);
    public static final AntiGem antiGemIII = new AntiGem(new Item.Settings().maxDamage(3), Tier.TIER3);
    public static final AntiGem antiGemIV = new AntiGem(new Item.Settings().maxDamage(4), Tier.TIER4);

    public static final Item damageGem = new Item(new Item.Settings());
    public static final PoweredGem smiteGem = new PoweredGem(new Item.Settings(), Feats.SMITE, damageGem);
    public static final PoweredGem strengthGem = new PoweredGem(new Item.Settings(), Feats.STRENGTH, damageGem);
    public static final PoweredGem fireTrailGem = new PoweredGem(new Item.Settings(), Feats.FIRE_TRAIL, damageGem);
    public static final PoweredGem machineGunGem = new PoweredGem(new Item.Settings(), Feats.MACHINE_GUN, damageGem);
    public static final PoweredGem damageSlowedGem = new PoweredGem(new Item.Settings(), Feats.DAMAGE_SLOWED, damageGem);
    public static final PoweredGem superShieldGem = new PoweredGem(new Item.Settings(), Feats.SUPER_SHIELD, damageGem);
    public static final PoweredGem fireworkerGem = new PoweredGem(new Item.Settings(), Feats.FIREWORKER, damageGem);
    public static final PoweredGem fireZoneGem = new PoweredGem(new Item.Settings(), Feats.FIRE_ZONE, damageGem);
    public static final PoweredGem instantKillGem = new PoweredGem(new Item.Settings(), Feats.INSTANT_KILL, damageGem);

    public static final Item healGem = new Item(new Item.Settings());
    public static final PoweredGem resistanceGem = new PoweredGem(new Item.Settings(), Feats.RESISTANCE, healGem);
    public static final PoweredGem healingFistGem = new PoweredGem(new Item.Settings(), Feats.HEALING_FIST, healGem);
    public static final PoweredGem shieldResistanceGem = new PoweredGem(new Item.Settings(), Feats.SHIELD_RESISTANCE, healGem);
    public static final PoweredGem healTrailGem = new PoweredGem(new Item.Settings(), Feats.HEAL_TRAIL, healGem);
    public static final PoweredGem lastStandGem = new PoweredGem(new Item.Settings(), Feats.LAST_STAND, healGem);

    public static final Item miscGem = new Item(new Item.Settings());
    public static final PoweredGem arrowComboGem = new PoweredGem(new Item.Settings(), Feats.ARROW_COMBO, miscGem);
    public static final PoweredGem bloodlustGem = new PoweredGem(new Item.Settings(), Feats.BLOODLUST, miscGem);
    public static final PoweredGem dogGem = new PoweredGem(new Item.Settings(), Feats.DOG, miscGem);
    public static final PoweredGem knockbackFistGem = new PoweredGem(new Item.Settings(), Feats.KNOCKBACK_FIST, miscGem);
    public static final PoweredGem mountainGem = new PoweredGem(new Item.Settings(), Feats.MOUNTAIN, miscGem);
    public static final PoweredGem beesGem = new PoweredGem(new Item.Settings(), Feats.BEES, miscGem);
    public static final PoweredGem invisibleGem = new PoweredGem(new Item.Settings(), Feats.INVISIBLE, miscGem);
    public static final PoweredGem undeadArmyGem = new PoweredGem(new Item.Settings(), Feats.UNDEAD_ARMY, miscGem);

    public static final Item mobilityGem = new Item(new Item.Settings());
    public static final PoweredGem noHungerGem = new PoweredGem(new Item.Settings(), Feats.NO_HUNGER, mobilityGem);
    public static final PoweredGem speedGem = new PoweredGem(new Item.Settings(), Feats.SPEED, mobilityGem);
    public static final PoweredGem dashGem = new PoweredGem(new Item.Settings(), Feats.DASH, mobilityGem);
    public static final PoweredGem jumpBoostGem = new PoweredGem(new Item.Settings(), Feats.JUMP_BOOST, mobilityGem);
    public static final PoweredGem stridersGraceGem = new PoweredGem(new Item.Settings(), Feats.STRIDERS_GRACE, mobilityGem);

    public static void init() {
        Registry.register(Registries.ITEM, Forglory.id("essence_infuser"), essenceInfuser);

        Registry.register(Registries.ITEM, Forglory.id("debug_item"), debugItem);
        Registry.register(Registries.ITEM, Forglory.id("essence"), essence);

        Registry.register(Registries.ITEM, Forglory.id("anti_gem1"), antiGemI);
        Registry.register(Registries.ITEM, Forglory.id("anti_gem2"), antiGemII);
        Registry.register(Registries.ITEM, Forglory.id("anti_gem3"), antiGemIII);
        Registry.register(Registries.ITEM, Forglory.id("anti_gem4"), antiGemIV);

        Registry.register(Registries.ITEM, Forglory.id("damage_gem"), damageGem);
        Registry.register(Registries.ITEM, Forglory.id("smite_gem"), smiteGem);
        Registry.register(Registries.ITEM, Forglory.id("strength_gem"), strengthGem);
        Registry.register(Registries.ITEM, Forglory.id("fire_trail_gem"), fireTrailGem);
        Registry.register(Registries.ITEM, Forglory.id("machine_gun_gem"), machineGunGem);
        Registry.register(Registries.ITEM, Forglory.id("damage_slowed_gem"), damageSlowedGem);
        Registry.register(Registries.ITEM, Forglory.id("super_shield_gem"), superShieldGem);
        Registry.register(Registries.ITEM, Forglory.id("fireworker_gem"), fireworkerGem);
        Registry.register(Registries.ITEM, Forglory.id("fire_zone_gem"), fireZoneGem);
        Registry.register(Registries.ITEM, Forglory.id("instant_kill_gem"), instantKillGem);

        Registry.register(Registries.ITEM, Forglory.id("heal_gem"), healGem);
        Registry.register(Registries.ITEM, Forglory.id("resistance_gem"), resistanceGem);
        Registry.register(Registries.ITEM, Forglory.id("healing_fist_gem"), healingFistGem);
        Registry.register(Registries.ITEM, Forglory.id("shield_resistance_gem"), shieldResistanceGem);
        Registry.register(Registries.ITEM, Forglory.id("heal_trail_gem"), healTrailGem);
        Registry.register(Registries.ITEM, Forglory.id("last_stand_gem"), lastStandGem);

        Registry.register(Registries.ITEM, Forglory.id("misc_gem"), miscGem);
        Registry.register(Registries.ITEM, Forglory.id("arrow_combo_gem"), arrowComboGem);
        Registry.register(Registries.ITEM, Forglory.id("bloodlust_gem"), bloodlustGem);
        Registry.register(Registries.ITEM, Forglory.id("dog_gem"), dogGem);
        Registry.register(Registries.ITEM, Forglory.id("knockback_fist_gem"), knockbackFistGem);
        Registry.register(Registries.ITEM, Forglory.id("mountain_gem"), mountainGem);
        Registry.register(Registries.ITEM, Forglory.id("bees_gem"), beesGem);
        Registry.register(Registries.ITEM, Forglory.id("invisible_gem"), invisibleGem);
        Registry.register(Registries.ITEM, Forglory.id("undead_army_gem"), undeadArmyGem);

        Registry.register(Registries.ITEM, Forglory.id("mobility_gem"), mobilityGem);
        Registry.register(Registries.ITEM, Forglory.id("no_hunger_gem"), noHungerGem);
        Registry.register(Registries.ITEM, Forglory.id("speed_gem"), speedGem);
        Registry.register(Registries.ITEM, Forglory.id("dash_gem"), dashGem);
        Registry.register(Registries.ITEM, Forglory.id("jump_boost_gem"), jumpBoostGem);
        Registry.register(Registries.ITEM, Forglory.id("striders_grace_gem"), stridersGraceGem);
    }
}
