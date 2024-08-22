package com.github.galatynf.forglory.entity;

import com.github.galatynf.forglory.Forglory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HeroEntityRenderer extends ZombieBaseEntityRenderer<HeroEntity, ZombieEntityModel<HeroEntity>> {
    public HeroEntityRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.ZOMBIE, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public HeroEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer layer,
                              EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(
                context,
                new ZombieEntityModel<>(context.getPart(layer)),
                new ZombieEntityModel<>(context.getPart(legsArmorLayer)),
                new ZombieEntityModel<>(context.getPart(bodyArmorLayer))
        );
    }

    @Override
    public Identifier getTexture(HeroEntity hero) {
        return Forglory.id("textures/entity/" + hero.getTexture() + ".png");
    }
}
