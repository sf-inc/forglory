package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.config.ConstantsConfig;

public enum Feats {
    DOG(Tier.TIER1),
    FIRE_TRAIL(Tier.TIER1),
    HEALING_FIST(Tier.TIER1),
    NO_HUNGER(Tier.TIER1),
    SMITE(Tier.TIER1),
    SPEED(Tier.TIER1),
    RESISTANCE(Tier.TIER1),
    BLOODLUST(Tier.TIER1),

    DASH(Tier.TIER2),
    KNOCKBACK_FIST(Tier.TIER2),
    MOUNTAIN(Tier.TIER2),
    STRENGTH(Tier.TIER2),

    COMPANION_HEAL(Tier.TIER3),
    DAMAGE_SLOWED(Tier.TIER3),
    FIRE_ZONE(Tier.TIER3),
    JUMP_BOOST(Tier.TIER3),
    SHIELD(Tier.TIER3),
    SHIELD_RESISTANCE(Tier.TIER3),
    STRIDERS_GRACE(Tier.TIER3),

    BEES(Tier.TIER4),
    INVISIBLE(Tier.TIER4),
    FIREWORKER(Tier.TIER4),
    INSTANT_KILL(Tier.TIER4),
    LAST_STAND(Tier.TIER4),
    HEAL_TRAIL(Tier.TIER4);

    public Tier tier;
    public int cooldown;
    Feats(final Tier tier) {
        this.tier = tier;
        this.cooldown = ConstantsConfig.NO_COOLDOWN;
    }

    public static void initCooldowns () {
        DOG.cooldown = ConstantsConfig.UNIQUE_COOLDOWN;

        HEALING_FIST.cooldown = ModConfig.get().cooldownConfig.healing_fist_cooldown;
        DASH.cooldown = ModConfig.get().cooldownConfig.dash_cooldown;
        KNOCKBACK_FIST.cooldown = ModConfig.get().cooldownConfig.knockback_fist_cooldown;
        MOUNTAIN.cooldown = ModConfig.get().cooldownConfig.mountain_cooldown;
        FIREWORKER.cooldown = ModConfig.get().cooldownConfig.fireworker_cooldown;
        INSTANT_KILL.cooldown = ModConfig.get().cooldownConfig.instant_kill_cooldown;
    }
}
