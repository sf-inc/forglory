package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.constants.CooldownsConfig;

public enum Feats {
    DOG(Tier.TIER1, CooldownsConfig.UNIQUE_COOLDOWN),
    FIRE_TRAIL(Tier.TIER1, CooldownsConfig.NO_COOLDOWN),
    HEALING_FIST(Tier.TIER1, CooldownsConfig.HEALING_FIST_COOLDOWN),
    NO_HUNGER(Tier.TIER1, CooldownsConfig.NO_COOLDOWN),
    SMITE(Tier.TIER1, CooldownsConfig.NO_COOLDOWN),
    SPEED(Tier.TIER1, CooldownsConfig.NO_COOLDOWN),

    DASH(Tier.TIER2, CooldownsConfig.DASH_COOLDOWN),
    FIRE_RESISTANCE(Tier.TIER2, CooldownsConfig.NO_COOLDOWN),
    KNOCKBACK_FIST(Tier.TIER2, CooldownsConfig.KNOCKBACK_FIST_COOLDOWN),
    MOUNTAIN(Tier.TIER2, CooldownsConfig.MOUNTAIN_COOLDOWN),
    STRENGTH(Tier.TIER2, CooldownsConfig.NO_COOLDOWN),

    COMPANION_HEAL(Tier.TIER3, CooldownsConfig.NO_COOLDOWN),
    DAMAGE_SLOWED(Tier.TIER3, CooldownsConfig.NO_COOLDOWN),
    FIRE_ZONE(Tier.TIER3, CooldownsConfig.NO_COOLDOWN),
    JUMP_BOOST(Tier.TIER3, CooldownsConfig.NO_COOLDOWN),
    SHIELD(Tier.TIER3, CooldownsConfig.NO_COOLDOWN),

    BEES(Tier.TIER4, CooldownsConfig.NO_COOLDOWN),
    INVISIBLE(Tier.TIER4, CooldownsConfig.NO_COOLDOWN),
    FIREWORKER(Tier.TIER4, CooldownsConfig.FIREWORKER_COOLDOWN),
    INSTANT_KILL(Tier.TIER4, CooldownsConfig.INSTANT_KILL_COOLDOWN),
    LAST_STAND(Tier.TIER4, CooldownsConfig.NO_COOLDOWN);

    public Tier tier;
    public int cooldown;
    Feats(final Tier tier, final int cooldown) {
        this.tier = tier;
        this.cooldown = cooldown;
    }
}
