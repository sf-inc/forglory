package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.entity.HeroEntity;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<FeatsComponent> FEATS =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("forglory:feats"), FeatsComponent.class);

    public static final ComponentKey<UndeadComponent> SUMMONED =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("forglory:summoned"), UndeadComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(FEATS, player -> new PlayerFeatsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerFor(HeroEntity.class, SUMMONED, summ -> new UndeadSummonedComponent());
    }
}
