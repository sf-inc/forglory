package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.statusEffects.LifeStealStatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatusEffectsInit {
    private StatusEffectsInit() {
    }

    public static final LifeStealStatusEffect lifeStealStatusEffect = new LifeStealStatusEffect();

    public static void init() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier("forglory", "life_steal_status_effect"), lifeStealStatusEffect);
    }
}
