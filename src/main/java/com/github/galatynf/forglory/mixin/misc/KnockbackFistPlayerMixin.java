package com.github.galatynf.forglory.mixin.misc;

import com.github.galatynf.forglory.imixin.IKnockbackFistPlayerMixin;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class KnockbackFistPlayerMixin implements IKnockbackFistPlayerMixin {

    @Unique
    private boolean knockbackActivated = false;

    @Override
    public boolean isKnockbackActivated() {
        return knockbackActivated;
    }

    @Override
    public void setKnockBack(boolean setter) {
        knockbackActivated = setter;
    }
}
