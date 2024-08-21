package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.entity.HeroEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.WolfEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class MyComponents implements EntityComponentInitializer {
    public static final ComponentKey<AdrenalinComponent> ADRENALIN =
            ComponentRegistry.getOrCreate(Forglory.id("adrenalin"), AdrenalinComponent.class);

    public static final ComponentKey<FeatsComponent> FEATS =
            ComponentRegistry.getOrCreate(Forglory.id("feats"), FeatsComponent.class);

    public static final ComponentKey<PlayerComponent> SUMMONED =
            ComponentRegistry.getOrCreate(Forglory.id("summoned"), PlayerComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ADRENALIN, PlayerAdrenalinComponent::new, RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(FEATS, PlayerFeatsComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerFor(HeroEntity.class, SUMMONED, hero -> new SummonerComponent());
        registry.registerFor(BeeEntity.class, SUMMONED, bee -> new SummonerComponent());
        registry.registerFor(WolfEntity.class, SUMMONED, wolf -> new SummonerComponent());
    }
}
