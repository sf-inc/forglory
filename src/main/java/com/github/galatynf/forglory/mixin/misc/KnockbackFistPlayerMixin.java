package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class KnockbackFistPlayerMixin extends LivingEntity implements IKnockbackFistPlayerMixin {

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Unique
    private boolean knockbackActivated = false;

    protected KnockbackFistPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean forglory$isKnockbackActivated() {
        return this.knockbackActivated;
    }

    @Override
    public void forglory$setKnockBack(boolean setter) {
        this.knockbackActivated = setter;
        if (setter && !this.getWorld().isClient()) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeIdentifier(SoundRegistry.INCRE_ID);
            if (MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.CENTURION) {
                // FIXME: Replace with world sound
                //NetworkInit.playSoundWide(SoundsInit.INCRE_ID, (ServerPlayerEntity) (Object) this, true);
            }
            // FIXME: Replace with world sound
            //NetworkInit.playSoundWide(SoundsInit.KNOCKBACK_FIST_ACT_ID, (ServerPlayerEntity) (Object) this, false);
        }
    }
}
