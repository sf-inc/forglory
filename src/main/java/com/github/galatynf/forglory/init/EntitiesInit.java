package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.entity.HeroEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntitiesInit {

    public static final EntityType<HeroEntity> HERO = FabricEntityTypeBuilder
            .create(SpawnGroup.MONSTER, HeroEntity::new)
            .dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build();

    public static void init() {
        Registry.register(Registry.ENTITY_TYPE,
                new Identifier("forglory", "hero"),
                HERO);
    }
}
