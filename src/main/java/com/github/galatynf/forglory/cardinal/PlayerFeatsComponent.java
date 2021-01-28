package com.github.galatynf.forglory.cardinal;

import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.List;

import static com.github.galatynf.forglory.enumFeat.Tier.TIER1;

public class PlayerFeatsComponent implements FeatsComponent {
    public PlayerFeatsComponent() {
        for(int i  = 0 ; i < 4 ; i++) {
            forglory_feats.add(i, Feats.NO_FEAT);
            forglory_cooldowns.add(i, 0);
        }
    }

    private final List<Feats> forglory_feats = new ArrayList<>();

    private final List<Integer> forglory_cooldowns = new ArrayList<>();

    @Override public void readFromNbt(CompoundTag tag) {
        for (int i = 0 ; i < 4 ; i++) {
            this.forglory_feats.set(i, Feats.valueOf(tag.getString("feat" + (i+1))));
            this.forglory_cooldowns.set(i, tag.getInt("cooldown" + (i+1)));
        }
    }
    @Override public void writeToNbt(CompoundTag tag) {
        tag.putString ("feat1", forglory_feats.get(0).toString());
        tag.putString ("feat2", forglory_feats.get(1).toString());
        tag.putString ("feat3", forglory_feats.get(2).toString());
        tag.putString ("feat4", forglory_feats.get(3).toString());
        tag.putInt ("cooldown1", forglory_cooldowns.get(0));
        tag.putInt ("cooldown2", forglory_cooldowns.get(1));
        tag.putInt ("cooldown3", forglory_cooldowns.get(2));
        tag.putInt ("cooldown4", forglory_cooldowns.get(3));
    }

    @Override
    public Feats getFeat(final Tier tier) {
        switch (tier) {
            case TIER1:
                return forglory_feats.get(0);
            case TIER2:
                return forglory_feats.get(1);
            case TIER3:
                return forglory_feats.get(2);
        }
        return forglory_feats.get(3);
    }

    @Override
    public Integer getCooldown(final Tier tier) {
        switch (tier) {
            case TIER1:
                return forglory_cooldowns.get(0);
            case TIER2:
                return forglory_cooldowns.get(1);
            case TIER3:
                return forglory_cooldowns.get(2);
        }
        return forglory_cooldowns.get(3);
    }

    @Override
    public void addOrUpdateFeat(final Feats feat) {
        switch (feat.tier) {
            case TIER1:
                forglory_feats.set(0, feat);
                forglory_cooldowns.set(0, 0);
                break;
            case TIER2:
                forglory_feats.set(1, feat);
                forglory_cooldowns.set(1, 0);
                break;
            case TIER3:
                forglory_feats.set(2, feat);
                forglory_cooldowns.set(2, 0);
                break;
            case TIER4:
                forglory_feats.set(3, feat);
                forglory_cooldowns.set(3, 0);
                break;
        }
    }

    @Override
    public void resetCooldown(final Tier tier) {
        Feats feat;
        switch (tier) {
            case TIER1:
                feat = forglory_feats.get(0);
                forglory_cooldowns.set(0, feat.cooldown);
                break;
            case TIER2:
                feat = forglory_feats.get(1);
                forglory_cooldowns.set(1, feat.cooldown);
                break;
            case TIER3:
                feat = forglory_feats.get(2);
                forglory_cooldowns.set(2, feat.cooldown);
                break;
            case TIER4:
                feat = forglory_feats.get(3);
                forglory_cooldowns.set(3, feat.cooldown);
                break;
        }
    }

    @Override
    public void setUniqueCooldown(final Tier tier) {
        switch (tier) {
            case TIER1:
                forglory_cooldowns.set(0, 0);
                break;
            case TIER2:
                forglory_cooldowns.set(1, 0);
                break;
            case TIER3:
                forglory_cooldowns.set(2, 0);
                break;
            case TIER4:
                forglory_cooldowns.set(3, 0);
                break;
        }
    }

    @Override
    public void decrementCooldowns() {
        int tierNum = 0;
        Integer cooldown;
        for (Tier tier : Tier.values()) {
            cooldown = getCooldown(tier);
            if (cooldown != null && cooldown > 0) {
                forglory_cooldowns.set(tierNum, getCooldown(TIER1) - 1);
                tierNum++;
            }
        }
    }
}
