package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import com.github.galatynf.forglory.init.NetworkInit;
import com.github.galatynf.forglory.init.SoundsInit;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
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
        // "!world.isclient()" condition in theory unnecessary
        if(setter && !world.isClient()) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeIdentifier(SoundsInit.INCRE_ID);
            if(MyComponents.FEATS.get(this).getForgloryClass() == FeatsClass.CENTURION) {
                ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, NetworkInit.PLAY_INCRE_ID, buf);
            }
            ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, NetworkInit.PLAY_KNOCKBACK_FIST_ACT_ID, buf);
        }
    }
}
