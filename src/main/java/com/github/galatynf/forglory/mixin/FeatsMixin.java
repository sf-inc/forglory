package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import java.util.HashMap;

@Mixin(PlayerEntity.class)
public class FeatsMixin implements IFeatsMixin {
    @Unique
    private final HashMap<Tier, Feats> feats = new HashMap<>();

    @Override
    public Feats getFeat(final Tier tier) {
        return feats.get(tier);
    }

    @Override
    public void addOrUpdateFeat(final Feats feat) {
        feats.put(feat.tier, feat);
    }
}
