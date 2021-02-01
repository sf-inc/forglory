package com.github.galatynf.forglory.enumFeat;

import java.util.HashMap;

public enum FeatsClass {
    NONE,
    CENTURION,
    NINJA,
    JUGGERNAUT,
    BERSERKER,
    PYROMANIAC,
    PALADIN,
    HUNTER;


    public static FeatsClass hasClass(HashMap<Tier, Feats> feats) {
        if(feats.containsValue(Feats.NO_HUNGER)
                && feats.containsValue(Feats.KNOCKBACK_FIST)
                && feats.containsValue(Feats.DAMAGE_SLOWED)
                && feats.containsValue(Feats.UNDEAD_ARMY)) {
            return CENTURION;
        }
        if(feats.containsValue(Feats.SPEED)
            && feats.containsValue(Feats.DASH)
            && feats.containsValue(Feats.JUMP_BOOST)
            && feats.containsValue(Feats.INVISIBLE)) {
            return NINJA;
        }
        if(feats.containsValue(Feats.RESISTANCE)
            && feats.containsValue(Feats.MACHINE_GUN)
            && feats.containsValue(Feats.SHIELD_RESISTANCE)
            && feats.containsValue(Feats.INSTANT_KILL)) {
            return JUGGERNAUT;
        }
        if(feats.containsValue(Feats.BLOODLUST)
            && feats.containsValue(Feats.HEALING_FIST)
            && feats.containsValue(Feats.LAST_STAND)) {
            return BERSERKER;
        }
        if(feats.containsValue(Feats.FIRE_TRAIL)
            && feats.containsValue(Feats.STRIDERS_GRACE)
            && feats.containsValue(Feats.FIRE_ZONE)) {
            return PYROMANIAC;
        }
        if(feats.containsValue(Feats.SMITE)
            && feats.containsValue(Feats.SUPER_SHIELD)
            && feats.containsValue(Feats.HEAL_TRAIL)) {
            return PALADIN;
        }
        if(feats.containsValue(Feats.ARROW_COMBO)
            && feats.containsValue(Feats.MOUNTAIN)
            && feats.containsValue(Feats.FIREWORKER)) {
            return HUNTER;
        }
        return NONE;
    }
}
