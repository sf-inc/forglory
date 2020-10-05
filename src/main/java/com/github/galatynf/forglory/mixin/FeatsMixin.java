package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.Feats;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class FeatsMixin {
    @Unique
    protected Feats.tier1 feat1 = Feats.tier1.NONE;
    @Unique
    protected Feats.tier2 feat2 = Feats.tier2.NONE;
    @Unique
    protected Feats.tier3 feat3 = Feats.tier3.NONE;
    @Unique
    protected Feats.tier4 feat4 = Feats.tier4.NONE;
}
