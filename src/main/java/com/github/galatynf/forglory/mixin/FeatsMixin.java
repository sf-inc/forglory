package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(PlayerEntity.class)
public class FeatsMixin implements IFeatsMixin {
    @Unique
    private final HashMap<Tier, Feats> forglory_feats = new HashMap<>();
    @Unique
    private final HashMap<Tier, Integer> forglory_cooldowns = new HashMap<>();

    @Override
    public Feats getFeat(final Tier tier) {
        return forglory_feats.get(tier);
    }

    @Override
    public Integer getCooldown(final Tier tier) {
        return forglory_cooldowns.get(tier);
    }

    @Override
    public void addOrUpdateFeat(final Feats feat) {
        forglory_feats.put(feat.tier, feat);
        forglory_cooldowns.put(feat.tier, 0);
    }

    @Override
    public void resetCooldown(final Tier tier) {
        Feats feat = forglory_feats.get(tier);
        forglory_cooldowns.put(tier, feat.cooldown);
    }

    @Override
    public void setUniqueCooldown(final Tier tier) {
        forglory_cooldowns.put(tier, 0);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void updateCooldown(CallbackInfo ci) {
        for (Tier tier : Tier.values()) {
            Integer cooldown = forglory_cooldowns.get(tier);
            if (cooldown != null && cooldown > 0)
                forglory_cooldowns.put(tier, cooldown-1);
        }
    }
}
