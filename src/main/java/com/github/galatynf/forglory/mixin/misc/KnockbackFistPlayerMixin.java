package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class KnockbackFistPlayerMixin extends LivingEntity implements IKnockbackFistPlayerMixin {

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

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
        System.out.println("INCRE");
        playSound(SoundEvents.BLOCK_ANVIL_LAND, 1, 1);
        world.playSound((PlayerEntity) (Object)this, this.getBlockPos(), SoundEvents.ENTITY_DONKEY_ANGRY, SoundCategory.AMBIENT, 1, 1);
        playSound(SoundsInit.incre, 1F, 1F);
        if(setter) {
            playSound(SoundsInit.incre, 1F, 1F);
        }
    }
}
