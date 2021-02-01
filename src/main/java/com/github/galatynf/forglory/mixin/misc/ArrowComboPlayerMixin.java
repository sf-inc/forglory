package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
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
        if (combo > ModConfig.get().featConfig.combo_max) {
            combo = ModConfig.get().featConfig.combo_max;
        }
        float amount = ModConfig.get().featConfig.combo_adrenalin_gain * combo;
        MyComponents.ADRENALIN.get(this).addAdrenalin(Utils.adrenalinMultiplier((PlayerEntity) (Object) this, amount));
    }

    @Override
    public void resetCombo() {
        combo = 0;
    }
}
