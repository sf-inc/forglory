package com.github.galatynf.forglory.enumFeat;

import com.github.galatynf.forglory.config.AdrenalinConfig;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.sound.SoundEvent;

public enum Tier {
    TIER1(SoundRegistry.TIER_1_WHOOSH_EVENT),
    TIER2(SoundRegistry.TIER_2_BASS_EVENT),
    TIER3(SoundRegistry.TIER_3_STRONG_BASS_EVENT),
    TIER4(SoundRegistry.TIER_4_OVERCHARGED_EVENT);

    private float threshold = 0.f;
    private final SoundEvent soundEvent;

    Tier(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public float getThreshold() {
        return this.threshold;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public static void init(final AdrenalinConfig adrenalinConfig) {
        TIER1.threshold = adrenalinConfig.threshold.tier1;
        TIER2.threshold = adrenalinConfig.threshold.tier2;
        TIER3.threshold = adrenalinConfig.threshold.tier3;
        TIER4.threshold = adrenalinConfig.threshold.tier4;
    }

    public static float getValueBetween(final Tier tier1, final Tier tier2) {
        return (tier1.threshold + tier2.threshold) / 2.f;
    }
}
