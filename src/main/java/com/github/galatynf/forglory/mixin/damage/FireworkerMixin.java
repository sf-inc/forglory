package com.github.galatynf.forglory.mixin.damage;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CrossbowItem.class)
public class FireworkerMixin {

    @ModifyVariable(method = "shoot", at = @At(value = "NEW", target = "net/minecraft/util/math/Quaternion"))
    private static ProjectileEntity setFireworkProjectile(ProjectileEntity projectileEntity2, World world, LivingEntity shooter, Hand hand,
                                                          ItemStack crossbow, ItemStack projectile, float soundPitch,
                                                          boolean creative, float speed, float divergence, float simulated) {
        if (!(shooter instanceof PlayerEntity)) return projectileEntity2;
        Feats feat = ((IFeatsMixin)shooter).getFeat(Tier.TIER4);
        if (feat == null) return projectileEntity2;
        if (feat.equals(Feats.FIREWORKER)) {
            if (((IAdrenalinMixin)shooter).getAdrenalin() > Tier.TIER4.threshold &&
                    ((IFeatsMixin)shooter).getCooldown(Tier.TIER4) == 0) {
                ((IFeatsMixin)shooter).resetCooldown(Tier.TIER4);
                projectileEntity2 = new FireworkRocketEntity(world, projectile, shooter, shooter.getX(),
                        shooter.getEyeY() - 0.15000000596046448D, shooter.getZ(), true);
            }
        }

        return projectileEntity2;
    }
}
