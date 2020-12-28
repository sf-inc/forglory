package com.github.galatynf.forglory.statusEffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class LifeStealStatusEffect extends StatusEffect {
    public LifeStealStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL, // whether beneficial or harmful for entities
                0xFF2211); // color in RGB
    }
}
