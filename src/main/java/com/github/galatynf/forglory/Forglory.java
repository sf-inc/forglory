package com.github.galatynf.forglory;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class Forglory implements ModInitializer {
    public static final String MOD_ID = "forglory";

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
        AutoConfig.getConfigHolder(ModConfig.class).registerSaveListener((configHolder, config) -> {
            Feats.init(config.cooldowns);
            Tier.init(config.adrenalin);
            return ActionResult.SUCCESS;
        });

        Feats.init(ModConfig.get().cooldowns);
        Tier.init(ModConfig.get().adrenalin);

        BiomeTagRegistry.init();
        BlockRegistry.init();
        ItemRegistry.init();
        ItemGroupRegistry.init();
        NetworkRegistry.init();
        SoundRegistry.init();
        StatusEffectRegistry.init();
        EntityRegistry.init();
    }

    public static Identifier id(final String path) {
        return Identifier.of(MOD_ID, path);
    }
}
