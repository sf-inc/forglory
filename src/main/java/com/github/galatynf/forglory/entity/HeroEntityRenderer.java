package com.github.galatynf.forglory.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HeroEntityRenderer extends BipedEntityRenderer<HeroEntity, ZombieEntityModel<HeroEntity>> {

    public HeroEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new ZombieEntityModel<>(0.0F, false), 0.5F);
        this.addFeature(new ArmorFeatureRenderer<>(this, new ZombieEntityModel<>(0.5F, true),
                new ZombieEntityModel<>(1.0F, true)));
    }

    @Override
    public Identifier getTexture(HeroEntity hero) {
        return new Identifier("forglory", "textures/entity/"+hero.getTexture()+".png");
    }
}