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
    public void forglory$incrementCombo() {
        this.combo++;
        if (this.combo > ModConfig.get().featConfig.comboMax) {
            this.combo = ModConfig.get().featConfig.comboMax;
        }
        float amount = ModConfig.get().featConfig.comboAdrenalinGain * this.combo;
        MyComponents.ADRENALIN.get(this).addAdrenalin(Utils.adrenalinMultiplier((PlayerEntity) (Object) this, amount));
    }

    @Override
    public void forglory$resetCombo() {
        this.combo = 0;
    }
}
