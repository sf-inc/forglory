package com.github.galatynf.forglory.gui;

import com.github.galatynf.forglory.config.constants.AdrenalinConfig;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;

@Environment(EnvType.CLIENT)
public class AdrenalinBar extends WWidget {

    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.coloredRect(x, y, width, height, 0xFF_FFFFFF);

        assert MinecraftClient.getInstance().player != null;
        float adrenalin = ((IAdrenalinMixin)MinecraftClient.getInstance().player).getAdrenalin() / AdrenalinConfig.MAX_AMOUNT;
        int widthAdrenalin = (int) (width * adrenalin);
        ScreenDrawing.coloredRect(x, y, widthAdrenalin, height, 0xFF_FF0000);

        int th1 = (int) ((AdrenalinConfig.TIER1_THRESHOLD / (float) AdrenalinConfig.MAX_AMOUNT) * width);
        int th2 = (int) ((AdrenalinConfig.TIER2_THRESHOLD / (float) AdrenalinConfig.MAX_AMOUNT) * width);
        int th3 = (int) ((AdrenalinConfig.TIER3_THRESHOLD / (float) AdrenalinConfig.MAX_AMOUNT) * width);
        int th4 = (int) ((AdrenalinConfig.TIER4_THRESHOLD / (float) AdrenalinConfig.MAX_AMOUNT) * width);
        ScreenDrawing.coloredRect(x + th1, y, 1, height, 0xFF_000000);
        ScreenDrawing.coloredRect(x + th2, y, 1, height, 0xFF_000000);
        ScreenDrawing.coloredRect(x + th3, y, 1, height, 0xFF_000000);
        ScreenDrawing.coloredRect(x + th4, y, 1, height, 0xFF_000000);
    }
}
