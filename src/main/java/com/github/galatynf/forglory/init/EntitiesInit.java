package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.entity.HeroEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntitiesInit {
    private EntitiesInit() {
    }

    public static final EntityType<HeroEntity> HERO = EntityType.Builder
            .create(HeroEntity::new, SpawnGroup.MONSTER)
            .dimensions(0.6F, 1.95F)
            .build();

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, Forglory.id("hero"), HERO);
        FabricDefaultAttributeRegistry.register(HERO, ZombieEntity.createZombieAttributes());
    }
}
