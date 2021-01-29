package com.github.galatynf.forglory.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundsInit {
    private SoundsInit() {}

    public static final Identifier TIER_1_EFFECT_ID = new Identifier("forglory:tier_1_whoosh");
    public static SoundEvent tier_1_whoosh_event = new SoundEvent(TIER_1_EFFECT_ID);

    public static final Identifier TIER_2_EFFECT_ID = new Identifier("forglory:tier_2_bass");
    public static SoundEvent tier_2_bass_event = new SoundEvent(TIER_2_EFFECT_ID);

    public static final Identifier TIER_3_EFFECT_ID = new Identifier("forglory:tier_3_strong_bass");
    public static SoundEvent tier_3_strong_bass_event = new SoundEvent(TIER_3_EFFECT_ID);

    public static final Identifier TIER_4_EFFECT_ID = new Identifier("forglory:tier_4_overcharged");
    public static SoundEvent tier_4_overcharged_event = new SoundEvent(TIER_4_EFFECT_ID);

    public static void init () {
        Registry.register(Registry.SOUND_EVENT, TIER_1_EFFECT_ID, tier_1_whoosh_event);
        Registry.register(Registry.SOUND_EVENT, TIER_2_EFFECT_ID, tier_2_bass_event);
        Registry.register(Registry.SOUND_EVENT, TIER_3_EFFECT_ID, tier_3_strong_bass_event);
        Registry.register(Registry.SOUND_EVENT, TIER_4_EFFECT_ID, tier_4_overcharged_event);
    }
}
