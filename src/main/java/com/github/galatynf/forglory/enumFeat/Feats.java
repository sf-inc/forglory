package com.github.galatynf.forglory.enumFeat;

// Class only meant to regroup enums. Should not be modified in any means
public enum Feats {
    DOG(Tier.TIER1),
    FIRE_TRAIL(Tier.TIER1),
    HEALING_FIST(Tier.TIER1),
    NO_HUNGER(Tier.TIER1),
    SMITE(Tier.TIER1),
    SPEED(Tier.TIER1),

    DASH(Tier.TIER2),
    FIRE_RESISTANCE(Tier.TIER2),
    KNOCKBACK_FIST(Tier.TIER2),
    MOUNTAIN(Tier.TIER2),
    STRENGTH(Tier.TIER2),

    COMPANION_HEAL(Tier.TIER3),
    DAMAGE_SLOWED(Tier.TIER3),
    FIRE_ZONE(Tier.TIER3),
    JUMP_BOOST(Tier.TIER3),
    SHIELD(Tier.TIER3),

    BEES(Tier.TIER4),
    INVISIBLE(Tier.TIER4),
    FIREWORKER(Tier.TIER4),
    INSTANT_KILL(Tier.TIER4),
    LAST_STAND(Tier.TIER4);

    public Tier tier;
    Feats(Tier tier) {
        this.tier = tier;
    }
}
