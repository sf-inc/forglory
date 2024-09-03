package com.github.galatynf.forglory.gui;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Tier;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class AdrenalinBar extends WWidget {
    private static final Identifier ADRENALIN_BAR_BG_TEXTURE = Forglory.id("hud/adrenalin_bar_bg");
    private static final Identifier ADRENALIN_BAR_FG_TEXTURE = Forglory.id("hud/adrenalin_bar_fg");

    @Override
    public void paint(DrawContext context, int x, int y, int mouseX, int mouseY) {
        if (MinecraftClient.getInstance().player == null) return;

        float adrenalin = MyComponents.ADRENALIN.get(MinecraftClient.getInstance().player).getAdrenalin();
        float adrenalinPercentage = getAdrenalinPercentage(adrenalin);

        int i = context.getScaledWindowWidth() / 2 - 91;
        int l = context.getScaledWindowHeight() - 29;
        context.drawGuiTexture(ADRENALIN_BAR_BG_TEXTURE, 182, 5, 0, 0, i, l, 182, 5);
        context.drawGuiTexture(ADRENALIN_BAR_FG_TEXTURE, 182, 5, 0, 0, i, l, MathHelper.floor(182 * adrenalinPercentage), 5);
    }

    private static float getAdrenalinPercentage(float adrenalin) {
        Tier minTier = null;
        for (Tier tier : Tier.values()) {
            if (adrenalin < tier.getThreshold()) break;
            minTier = tier;
        }

        int minOrdinal = minTier == null ? 0 : minTier.ordinal() + 1;
        float min = 0.f;
        float max;

        if (minTier == null) {
            max = Tier.TIER1.getThreshold();
        } else if (minTier.equals(Tier.TIER4)) {
            min = minTier.getThreshold();
            max = ModConfig.get().adrenalin.maxAmount;
        } else {
            min = minTier.getThreshold();
            max = Tier.values()[minTier.ordinal() + 1].getThreshold();
        }

        float lerpPercentage = (adrenalin - min) / (max - min);
        return (minOrdinal + lerpPercentage) / 5.f;
    }
}
