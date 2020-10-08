package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import java.util.HashMap;

@Mixin(PlayerEntity.class)
public class FeatsMixin {
    @Unique
    protected HashMap<Tier, Feats> feats = new HashMap<>();
}
