package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.entity.HeroEntity;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<AdrenalinComponent> ADRENALIN =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("forglory:adrenalin"), AdrenalinComponent.class);

    public static final ComponentKey<FeatsComponent> FEATS =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("forglory:feats"), FeatsComponent.class);

    public static final ComponentKey<PlayerComponent> SUMMONED =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("forglory:summoned"), PlayerComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ADRENALIN, PlayerAdrenalinComponent::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(FEATS, player -> new PlayerFeatsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerFor(HeroEntity.class, SUMMONED, hero -> new SummonerComponent());
        registry.registerFor(BeeEntity.class, SUMMONED, bee -> new SummonerComponent());
        registry.registerFor(WolfEntity.class, SUMMONED, wolf -> new SummonerComponent());
    }
}
