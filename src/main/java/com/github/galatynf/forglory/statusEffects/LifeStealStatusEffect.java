package com.github.galatynf.forglory.statusEffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

// FIXME: Replace with inlined definition
public class LifeStealStatusEffect extends StatusEffect {
    public LifeStealStatusEffect() {
        super(
                StatusEffectCategory.BENEFICIAL, // whether beneficial or harmful for entities
                0xFF2211); // color in RGB
    }
}
