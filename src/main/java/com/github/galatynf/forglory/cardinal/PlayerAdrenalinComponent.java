package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PlayerAdrenalinComponent implements AdrenalinComponent, AutoSyncedComponent {

    private final PlayerEntity provider;
    private float forglory_adrenalin;

    public PlayerAdrenalinComponent(final PlayerEntity playerEntity) {
        this.provider = playerEntity;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.forglory_adrenalin = tag.getFloat("adrenalin");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putFloat("adrenalin", this.forglory_adrenalin);
    }

    @Override
    public float getAdrenalin() {
        return this.forglory_adrenalin;
    }

    @Override
    public void setAdrenalin(final float newAdrenalin) {
        this.forglory_adrenalin = newAdrenalin;
        MyComponents.ADRENALIN.sync(this.provider);
    }

    @Override
    public void addAdrenalin(final float amount) {
        if(!MyComponents.FEATS.get(provider).hasAFeat()) {
            return;
        }
        float threshold = Tier.TIER4.getThreshold();
        if(this.forglory_adrenalin >= threshold && amount > 0) {
            return;
        }
        float prev_adrenalin = this.forglory_adrenalin;
        this.forglory_adrenalin += amount;
        if(prev_adrenalin >= threshold && this.forglory_adrenalin < threshold) {
            this.forglory_adrenalin = (threshold + Tier.TIER3.getThreshold()) / 2.f;
            return;
        }
        if(prev_adrenalin < threshold && this.forglory_adrenalin >= threshold) {
            this.forglory_adrenalin = threshold+600;
            if(MyComponents.FEATS.get(provider).getForgloryClass() != FeatsClass.NONE) {
                this.forglory_adrenalin += 200;
            }
            return;
        }

        if (this.forglory_adrenalin > ModConfig.get().adrenalinConfig.maxAmount) {
            this.forglory_adrenalin = ModConfig.get().adrenalinConfig.maxAmount;
        }
        if (this.forglory_adrenalin < ConstantsConfig.MIN_AMOUNT) {
            this.forglory_adrenalin = ConstantsConfig.MIN_AMOUNT;
        }
        MyComponents.ADRENALIN.sync(provider);
    }
}
