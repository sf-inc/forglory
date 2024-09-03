package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.Utils;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.imixin.IArrowComboMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ArrowComboPlayerMixin extends LivingEntity implements IArrowComboMixin {
    @Unique
    private int combo = 0;
    @Unique
    private int delay = 0;

    protected ArrowComboPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void resetComboWhenTimeout(CallbackInfo ci) {
        if (!this.getWorld().isClient()
                && this.combo > 0
                && ++this.delay > ModConfig.get().featConfig.arrowCombo.maxDelay) {
            this.forglory$resetCombo();
        }
    }

    @Override
    public void forglory$incrementCombo() {
        this.combo++;
        this.delay = 0;
        if (this.combo > ModConfig.get().featConfig.arrowCombo.comboMax) {
            this.combo = ModConfig.get().featConfig.arrowCombo.comboMax;
        }
        float amount = ModConfig.get().featConfig.arrowCombo.adrenalinGain * this.combo;
        MyComponents.ADRENALIN.get(this).addAdrenalin(Utils.adrenalinMultiplier((PlayerEntity) (Object) this, amount));
    }

    @Override
    public void forglory$resetCombo() {
        this.combo = 0;
        this.delay = 0;
    }
}
