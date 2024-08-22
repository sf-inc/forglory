package com.github.galatynf.forglory.gui;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.init.BlockRegistry;
import io.github.cottonmc.cotton.gui.widget.WDynamicLabel;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class FeatLabel extends WDynamicLabel {
    private final Tier tier;
    public FeatLabel(final Tier tier) {
        super(() -> I18n.translate("text.forglory.no_feat"));
        this.alignment = HorizontalAlignment.CENTER;
        this.tier = tier;
        this.color = 15000000;
    }

    @Override
    public void paint(DrawContext context, int x, int y, int mouseX, int mouseY) {
        PlayerEntity playerEntity = MinecraftClient.getInstance().player;
        if (playerEntity == null) return;

        BlockPos pos = playerEntity.getBlockPos().down();
        BlockState blockState = playerEntity.getWorld().getBlockState(pos);
        if (!blockState.isOf(BlockRegistry.ESSENCE_INFUSER)) return;

        x = (MinecraftClient.getInstance().getWindow().getScaledWidth() - this.width) / 2;
        this.text = () -> I18n.translate(MyComponents.FEATS.get(playerEntity).getFeat(this.tier).toTranslatableText());
        super.paint(context, x, y, mouseX, mouseY);
    }
}
