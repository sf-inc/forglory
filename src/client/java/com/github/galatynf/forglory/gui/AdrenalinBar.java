package com.github.galatynf.forglory.gui;

import com.github.galatynf.forglory.Forglory;
import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Tier;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AdrenalinBar extends WWidget {
    private static final Identifier ADRENALIN_BAR_BG_TEXTURE = Forglory.id("hud/adrenalin_bar_bg");
    private static final Identifier ADRENALIN_BAR_FG_TEXTURE = Forglory.id("hud/adrenalin_bar_fg");

    @Override
    public void paint(DrawContext context, int x, int y, int mouseX, int mouseY) {
        if (MinecraftClient.getInstance().player == null) return;

        float adrenalin = MyComponents.ADRENALIN.get(MinecraftClient.getInstance().player).getAdrenalin();
        float adrenalinPercentage = Math.min(adrenalin / Tier.TIER4.getThreshold(), 1.f);

        int i = context.getScaledWindowWidth() / 2 - 91;
        int l = context.getScaledWindowHeight() - 29;
        context.drawGuiTexture(ADRENALIN_BAR_BG_TEXTURE, 182, 5, 0, 0, i, l, 182, 5);
        context.drawGuiTexture(ADRENALIN_BAR_FG_TEXTURE, 182, 5, 0, 0, i, l, (int) Math.floor(182 * adrenalinPercentage), 5);
    }
}
