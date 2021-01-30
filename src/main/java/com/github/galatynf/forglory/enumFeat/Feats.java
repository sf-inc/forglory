package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.config.ConstantsConfig;

public enum Feats {
    NO_FEAT(Tier.TIER1),

    ARROW_COMBO(Tier.TIER1),
    BLOODLUST(Tier.TIER1),
    NO_HUNGER(Tier.TIER1),
    RESISTANCE(Tier.TIER1),
    SMITE(Tier.TIER1),
    SPEED(Tier.TIER1),
    STRENGTH(Tier.TIER1),

    DASH(Tier.TIER2),
    DOG(Tier.TIER2),
    FIRE_TRAIL(Tier.TIER2),
    HEALING_FIST(Tier.TIER2),
    KNOCKBACK_FIST(Tier.TIER2),
    MACHINE_GUN(Tier.TIER2),
    MOUNTAIN(Tier.TIER2),

    COMPANION_HEAL(Tier.TIER3),
    DAMAGE_SLOWED(Tier.TIER3),
    JUMP_BOOST(Tier.TIER3),
    SHIELD_RESISTANCE(Tier.TIER3),
    STRIDERS_GRACE(Tier.TIER3),
    SUPER_SHIELD(Tier.TIER3),

    BEES(Tier.TIER4),
    FIREWORKER(Tier.TIER4),
    FIRE_ZONE(Tier.TIER4),
    HEAL_TRAIL(Tier.TIER4),
    INSTANT_KILL(Tier.TIER4),
    INVISIBLE(Tier.TIER4),
    LAST_STAND(Tier.TIER4),
    UNDEAD_ARMY(Tier.TIER4);

    public Tier tier;
    public int cooldown;
    Feats(final Tier tier) {
        this.tier = tier;
        this.cooldown = ConstantsConfig.NO_COOLDOWN;
    }

    public static void initCooldowns () {
        HEALING_FIST.cooldown = ModConfig.get().cooldownConfig.healing_fist_cooldown;
        DASH.cooldown = ModConfig.get().cooldownConfig.dash_cooldown;
        KNOCKBACK_FIST.cooldown = ModConfig.get().cooldownConfig.knockback_fist_cooldown;
        MACHINE_GUN.cooldown = ModConfig.get().cooldownConfig.machine_gun_cooldown;
        MOUNTAIN.cooldown = ModConfig.get().cooldownConfig.mountain_cooldown;
        FIREWORKER.cooldown = ModConfig.get().cooldownConfig.fireworker_cooldown;
        INSTANT_KILL.cooldown = ModConfig.get().cooldownConfig.instant_kill_cooldown;
    }
}
