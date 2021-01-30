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


    public static final Identifier MALE_GRUNT_ID = new Identifier("forglory:male_grunt");
    public static SoundEvent male_grunt = new SoundEvent((MALE_GRUNT_ID));

    public static final Identifier FEMALE_GRUNT_ID = new Identifier("forglory:female_grunt");
    public static SoundEvent female_grunt = new SoundEvent((FEMALE_GRUNT_ID));

    public static final Identifier FEMALE_DEATH_ID = new Identifier("forglory:female_death");
    public static SoundEvent female_death = new SoundEvent((FEMALE_DEATH_ID));

    public static final Identifier MALE_DEATH_ID = new Identifier("forglory:male_death");
    public static SoundEvent male_death = new SoundEvent((MALE_DEATH_ID));


    public static final Identifier INCRE_ID = new Identifier("forglory:incre");
    public static SoundEvent incre = new SoundEvent((INCRE_ID));

    public static final Identifier DIBILIS_ID = new Identifier("forglory:dibilis");
    public static SoundEvent dibilis = new SoundEvent((DIBILIS_ID));

    public static void init () {
        Registry.register(Registry.SOUND_EVENT, TIER_1_EFFECT_ID, tier_1_whoosh_event);
        Registry.register(Registry.SOUND_EVENT, TIER_2_EFFECT_ID, tier_2_bass_event);
        Registry.register(Registry.SOUND_EVENT, TIER_3_EFFECT_ID, tier_3_strong_bass_event);
        Registry.register(Registry.SOUND_EVENT, TIER_4_EFFECT_ID, tier_4_overcharged_event);

        Registry.register(Registry.SOUND_EVENT, MALE_GRUNT_ID, male_grunt);

        Registry.register(Registry.SOUND_EVENT, FEMALE_GRUNT_ID, female_grunt);

        Registry.register(Registry.SOUND_EVENT, FEMALE_DEATH_ID, female_death);

        Registry.register(Registry.SOUND_EVENT, MALE_DEATH_ID, male_death);

        Registry.register(Registry.SOUND_EVENT, INCRE_ID, incre);
        Registry.register(Registry.SOUND_EVENT, DIBILIS_ID, dibilis);

    }
}
