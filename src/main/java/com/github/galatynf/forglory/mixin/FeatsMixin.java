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
    private final HashMap<Tier, Feats> feats = new HashMap<>();
    @Unique
    private final HashMap<Tier, Integer> cooldowns = new HashMap<>();

    @Override
    public Feats getFeat(final Tier tier) {
        return feats.get(tier);
    }

    @Override
    public Integer getCooldown(final Tier tier) {
        return cooldowns.get(tier);
    }

    @Override
    public void addOrUpdateFeat(final Feats feat) {
        feats.put(feat.tier, feat);
    }

    @Override
    public void resetCooldown(final Tier tier) {
        Feats feat = feats.get(tier);
        cooldowns.put(tier, feat.cooldown);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void updateCooldown(CallbackInfo ci) {
        for (Tier tier : Tier.values()) {
            Integer cooldown = cooldowns.get(tier);
            if (cooldown != null && cooldown > 0)
                cooldowns.put(tier, cooldown-1);
        }
    }
}
