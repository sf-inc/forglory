package com.github.galatynf.forglory.gui;

import com.github.galatynf.forglory.config.constants.AdrenalinConfig;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class AdrenalinBar extends WWidget {

    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        assert MinecraftClient.getInstance().player != null;
        float adrenalin = ((IAdrenalinMixin)MinecraftClient.getInstance().player).getAdrenalin();

        int a = 255;
        int r, g;
        if (adrenalin < AdrenalinConfig.TIER4_THRESHOLD) {
            r = (int) ((adrenalin / AdrenalinConfig.TIER4_THRESHOLD) * 255);
            g = 255 - (int) ((adrenalin / AdrenalinConfig.TIER4_THRESHOLD) * 255);
        } else {
            r = 255;
            g = 0;
        }

        int color = a << 24 | r << 16 | g << 8;

        if (adrenalin > AdrenalinConfig.TIER4_THRESHOLD) {
            adrenalin = AdrenalinConfig.TIER4_THRESHOLD;
        }
        float adrenalinPercentage = adrenalin / AdrenalinConfig.TIER4_THRESHOLD;
        int heightAdrenalin = (int) ((height - 2) * adrenalinPercentage);
        Identifier bar = new Identifier("forglory", "textures/overlay/adrenalin_bar.png");

        if (heightAdrenalin > 0) {
            ScreenDrawing.coloredRect(x + 1, y - 1 + (height - heightAdrenalin), width - 2, heightAdrenalin, color);
        }
        ScreenDrawing.texturedRect(x, y, width, height, bar, -1);
    }
}
