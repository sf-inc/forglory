package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IArrowComboMixin;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class ArrowComboPlayerMixin implements IArrowComboMixin {
    @Unique
    private int combo = 0;

    @Override
    public void incrementCombo() {
        combo++;
        if(combo > ModConfig.get().featConfig.combo_max)
            combo = ModConfig.get().featConfig.combo_max;
        ((IAdrenalinMixin)this).addAdrenalin(combo * ModConfig.get().featConfig.combo_adrenalin_gain);
    }

    @Override
    public void resetCombo() {
        combo = 0;
    }

    @Override
    public int getCombo() {
        return combo;
    }
}
