package com.github.galatynf.forglory.mixin;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.imixin.ILastStandMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class LastStandRenderMixin {
    @Shadow protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Shadow @Final private MinecraftClient client;

    @Unique
    private static final Identifier LAST_STAND_OUTLINE = Forglory.id("textures/misc/last_stand.png");

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"))
    private void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (this.client.player == null) return;

        if (((ILastStandMixin) client.player).forglory$isBerserk()) {
            this.renderOverlay(context, LAST_STAND_OUTLINE, 1.0f);
        }
    }
}
