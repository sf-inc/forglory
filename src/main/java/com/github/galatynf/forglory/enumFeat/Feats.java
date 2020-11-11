package com.github.galatynf.forglory.enumFeat;

public enum Feats {
    DOG(Tier.TIER1, -1),
    FIRE_TRAIL(Tier.TIER1, 0),
    HEALING_FIST(Tier.TIER1, 0), // Add cooldown
    NO_HUNGER(Tier.TIER1, 0),
    SMITE(Tier.TIER1, 0),
    SPEED(Tier.TIER1, 0),

    DASH(Tier.TIER2, 0), // Add cooldown
    FIRE_RESISTANCE(Tier.TIER2, 0),
    KNOCKBACK_FIST(Tier.TIER2, 100), // Add cooldown
    MOUNTAIN(Tier.TIER2, 0), // Add cooldown
    STRENGTH(Tier.TIER2, 0),

    COMPANION_HEAL(Tier.TIER3, 0),
    DAMAGE_SLOWED(Tier.TIER3, 0),
    FIRE_ZONE(Tier.TIER3, 0),
    JUMP_BOOST(Tier.TIER3, 0),
    SHIELD(Tier.TIER3, 0),

    BEES(Tier.TIER4, 0),
    INVISIBLE(Tier.TIER4, 0),
    FIREWORKER(Tier.TIER4, 400), // Add cooldown
    INSTANT_KILL(Tier.TIER4, 0), // Add cooldown
    LAST_STAND(Tier.TIER4, 0);

    public Tier tier;
    public Integer cooldown;
    Feats(final Tier tier, final Integer cooldown) {
        this.tier = tier;
        this.cooldown = cooldown;
    }
}
