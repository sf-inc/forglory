package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class KnockbackFistPlayerMixin extends LivingEntity implements IKnockbackFistPlayerMixin {

    @Unique
    private boolean knockbackActivated = false;

    protected KnockbackFistPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isKnockbackActivated() {
        return knockbackActivated;
    }

    @Override
    public void setKnockBack(boolean setter) {
        knockbackActivated = setter;
        if(setter) {
            world.playSound((PlayerEntity) (Object) this, this.getBlockPos(), SoundsInit.incre, SoundCategory.VOICE, 1f, 1f);
        }
    }
}
