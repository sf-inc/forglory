package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.config.ConstantsConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.FeatsClass;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;

public class PlayerFeatsComponent implements FeatsComponent {
    public PlayerFeatsComponent() {
        for (Tier tier: Tier.values()) {
            forglory_feats.put(tier, Feats.NO_FEAT);
            forglory_cooldowns.put(tier, Feats.NO_FEAT.cooldown);
        }
    }

    private final HashMap<Tier, Feats> forglory_feats = new HashMap<>();
    private final HashMap<Tier, Integer> forglory_cooldowns = new HashMap<>();
    private FeatsClass forglory_class = FeatsClass.NONE;

    @Override public void readFromNbt(CompoundTag tag) {
        for (Tier tier: Tier.values()) {
            this.forglory_feats.put(tier, Feats.valueOf(tag.getString("feat" + tier.toString())));
            this.forglory_cooldowns.put(tier, tag.getInt("cooldown" + tier.toString()));
            System.out.println(tag.getString("class"));
            if(!tag.getString("class").equals("")) {
                this.forglory_class = FeatsClass.valueOf(tag.getString("class"));
            }
        }
    }

    @Override public void writeToNbt(CompoundTag tag) {
        for (Tier tier: Tier.values()) {
            tag.putString("feat" + tier.toString(), forglory_feats.get(tier).toString());
            tag.putInt("cooldown" + tier.toString(), forglory_cooldowns.get(tier));
            tag.putString("class", forglory_class.toString());
        }
    }

    @Override
    public Feats getFeat(final Tier tier) {
        return forglory_feats.get(tier);
    }

    @Override
    public Integer getCooldown(final Tier tier) {
        return forglory_cooldowns.get(tier);
    }

    public FeatsClass getForgloryClass() {
        return forglory_class;
    }

    @Override
    public void addOrUpdateFeat(final Feats feat) {
        forglory_feats.put(feat.tier, feat);
        forglory_cooldowns.put(feat.tier, ConstantsConfig.NO_COOLDOWN);
        forglory_class = FeatsClass.hasClass(forglory_feats);
    }

    @Override
    public void resetCooldown(final Tier tier) {
        Feats feat = forglory_feats.get(tier);
        forglory_cooldowns.put(tier, feat.cooldown);
    }

    @Override
    public void setUniqueCooldown(final Tier tier) {
        forglory_cooldowns.put(tier, ConstantsConfig.UNIQUE_COOLDOWN);
    }

    @Override
    public void decrementCooldowns() {
        for (Tier tier: Tier.values()) {
            Integer cooldown = forglory_cooldowns.get(tier);
            if (cooldown != null && cooldown > 0) {
                forglory_cooldowns.put(tier, cooldown - 1);
            }
        }
    }
}
