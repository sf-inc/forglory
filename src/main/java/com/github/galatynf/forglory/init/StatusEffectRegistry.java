package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.effect.LifeStealStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class StatusEffectRegistry {
    private StatusEffectRegistry() {
    }

    public static final RegistryEntry<StatusEffect> lifeStealStatusEffect = register("effect.life_steal",
            new LifeStealStatusEffect());

    public static void init() {
    }

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Forglory.id(id), statusEffect);
    }
}
