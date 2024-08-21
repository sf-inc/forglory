package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import java.util.HashMap;

public class PlayerFeatsComponent implements FeatsComponent, AutoSyncedComponent {

    private final PlayerEntity provider;
    private final HashMap<Tier, Feats> forglory_feats = new HashMap<>();
    private final HashMap<Tier, Integer> forglory_cooldowns = new HashMap<>();
    private FeatsClass forglory_class = FeatsClass.NONE;
    private boolean forglory_has_a_feat = false;

    public PlayerFeatsComponent(final PlayerEntity playerEntity) {
        this.provider = playerEntity;
        for (Tier tier : Tier.values()) {
            this.forglory_feats.put(tier, Feats.NO_FEAT);
            this.forglory_cooldowns.put(tier, Feats.NO_FEAT.cooldown);
        }
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        for (Tier tier : Tier.values()) {
            this.forglory_feats.put(tier, Feats.valueOf(tag.getString("feat" + tier)));
            this.forglory_cooldowns.put(tier, tag.getInt("cooldown" + tier));
        }
        this.forglory_class = FeatsClass.valueOf(tag.getString("class"));
        this.forglory_has_a_feat = tag.getBoolean("hasFeat");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        for (Tier tier : Tier.values()) {
            tag.putString("feat" + tier, this.forglory_feats.get(tier).toString());
            tag.putInt("cooldown" + tier, this.forglory_cooldowns.get(tier));
        }
        tag.putString("class", this.forglory_class.toString());
        tag.putBoolean("hasFeat", this.forglory_has_a_feat);
    }

    @Override
    public Feats getFeat(final Tier tier) {
        return this.forglory_feats.get(tier);
    }

    @Override
    public Integer getCooldown(final Tier tier) {
        return this.forglory_cooldowns.get(tier);
    }

    public FeatsClass getForgloryClass() {
        return this.forglory_class;
    }

    @Override
    public void addOrUpdateFeat(final Feats feat) {
        this.forglory_feats.put(feat.tier, feat);
        this.forglory_cooldowns.put(feat.tier, ConstantsConfig.NO_COOLDOWN);
        this.forglory_class = FeatsClass.hasClass(this.forglory_feats);
        this.forglory_has_a_feat = true;
        MyComponents.FEATS.sync(this.provider);
    }

    @Override
    public void removeFeat(Tier tier) {
        this.forglory_feats.put(tier, Feats.NO_FEAT);
        this.forglory_cooldowns.put(tier, ConstantsConfig.NO_COOLDOWN);
        this.forglory_class = FeatsClass.hasClass(this.forglory_feats);
        this.forglory_has_a_feat = false;
        for(Feats aFeat : this.forglory_feats.values()) {
            if(aFeat != Feats.NO_FEAT) {
                this.forglory_has_a_feat = true;
                break;
            }
        }
        MyComponents.FEATS.sync(this.provider);
    }

    @Override
    public void resetCooldown(final Tier tier) {
        Feats feat = this.forglory_feats.get(tier);
        this.forglory_cooldowns.put(tier, feat.cooldown);
    }

    @Override
    public void setUniqueCooldown(final Tier tier) {
        this.forglory_cooldowns.put(tier, ConstantsConfig.UNIQUE_COOLDOWN);
    }

    @Override
    public void decrementCooldowns() {
        for (Tier tier : Tier.values()) {
            Integer cooldown = this.forglory_cooldowns.get(tier);
            if (cooldown != null && cooldown > 0) {
                this.forglory_cooldowns.put(tier, cooldown - 1);
            }
        }
    }

    @Override
    public boolean hasAFeat() {
        return this.forglory_has_a_feat;
    }
}
