package com.github.galatynf.forglory;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.entity.HeroEntity;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.*;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class Forglory implements ModInitializer {

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));

        ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer ->  {
            Tier.initThresholds();
            Feats.initCooldowns();
        });

        BlocksInit.init();
        ItemsInit.init();
        NetworkInit.init();
        SoundsInit.init();
        StatusEffectsInit.init();
        StructuresInit.init();
        EntitiesInit.init();
    }
}
