package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
    private SoundRegistry() {
    }

    // TODO: Rework sound playing, as we can't hear feat sounds while tier ones are playing
    public static final Identifier TIER_1_EFFECT_ID = Forglory.id("tier_1_whoosh");
    public static final SoundEvent TIER_1_WHOOSH_EVENT = SoundEvent.of(TIER_1_EFFECT_ID);

    public static final Identifier TIER_2_EFFECT_ID = Forglory.id("tier_2_bass");
    public static final SoundEvent TIER_2_BASS_EVENT = SoundEvent.of(TIER_2_EFFECT_ID);

    public static final Identifier TIER_3_EFFECT_ID = Forglory.id("tier_3_strong_bass");
    public static final SoundEvent TIER_3_STRONG_BASS_EVENT = SoundEvent.of(TIER_3_EFFECT_ID);

    public static final Identifier TIER_4_EFFECT_ID = Forglory.id("tier_4_overcharged");
    public static final SoundEvent TIER_4_OVERCHARGED_EVENT = SoundEvent.of(TIER_4_EFFECT_ID);

    public static final Identifier MALE_GRUNT_ID = Forglory.id("male_grunt");
    public static final SoundEvent MALE_GRUNT = SoundEvent.of(MALE_GRUNT_ID);
    public static final Identifier FEMALE_GRUNT_ID = Forglory.id("female_grunt");
    public static final SoundEvent FEMALE_GRUNT = SoundEvent.of(FEMALE_GRUNT_ID);

    public static final Identifier MALE_DEATH_ID = Forglory.id("male_death");
    public static final SoundEvent MALE_DEATH = SoundEvent.of(MALE_DEATH_ID);
    public static final Identifier FEMALE_DEATH_ID = Forglory.id("female_death");
    public static final SoundEvent FEMALE_DEATH = SoundEvent.of(FEMALE_DEATH_ID);

    public static final Identifier INCRE_ID = Forglory.id("incre");
    public static final SoundEvent INCRE = SoundEvent.of(INCRE_ID);
    public static final Identifier DIBILIS_ID = Forglory.id("dibilis");
    public static final SoundEvent DIBILIS = SoundEvent.of(DIBILIS_ID);

    public static final Identifier DASH_ID = Forglory.id("dash");
    public static final SoundEvent DASH = SoundEvent.of(DASH_ID);

    public static final Identifier MOUNTAIN_ID = Forglory.id("mountain");
    public static final SoundEvent MOUNTAIN = SoundEvent.of(MOUNTAIN_ID);

    public static final Identifier FIRE_TRAIL_ACT_ID = Forglory.id("fire_trail_act");
    public static final SoundEvent FIRE_TRAIL_ACT = SoundEvent.of(FIRE_TRAIL_ACT_ID);
    public static final Identifier FIRE_TRAIL_ACT_VOICE_ID = Forglory.id("fire_trail_act_voice");
    public static final SoundEvent FIRE_TRAIL_ACT_VOICE = SoundEvent.of(FIRE_TRAIL_ACT_VOICE_ID);

    public static final Identifier VAMPIRISM_ID = Forglory.id("vampirism");
    public static final SoundEvent VAMPIRISM = SoundEvent.of(VAMPIRISM_ID);

    public static final Identifier MACHINE_GUN_ID = Forglory.id("machine_gun");
    public static final SoundEvent MACHINE_GUN = SoundEvent.of(MACHINE_GUN_ID);
    public static final Identifier MACHINE_GUN_VOICE_ID = Forglory.id("machine_gun_voice");
    public static final SoundEvent MACHINE_GUN_VOICE = SoundEvent.of(MACHINE_GUN_VOICE_ID);

    public static final Identifier KNOCKBACK_FIST_ACT_ID = Forglory.id("knockback_fist_act");
    public static final SoundEvent KNOCKBACK_FIST_ACT = SoundEvent.of(KNOCKBACK_FIST_ACT_ID);
    public static final Identifier KNOCKBACK_FISTED_ID = Forglory.id("knockback_fisted");
    public static final SoundEvent KNOCKBACK_FISTED = SoundEvent.of(KNOCKBACK_FISTED_ID);

    public static final Identifier SHIELD_RES_HITS_ID = Forglory.id("shield_res_hits");
    public static final SoundEvent SHIELD_RES_HITS = SoundEvent.of(SHIELD_RES_HITS_ID);
    public static final Identifier SHIELD_FLIP_ID = Forglory.id("shield_flip");
    public static final SoundEvent SHIELD_FLIP = SoundEvent.of(SHIELD_FLIP_ID);

    public static final Identifier UNDEAD_ARMY_SPAWN_ID = Forglory.id("undead_army_spawn");
    public static final SoundEvent UNDEAD_ARMY_SPAWN = SoundEvent.of(UNDEAD_ARMY_SPAWN_ID);
    public static final Identifier UNDEAD_ARMY_VOICE_ID = Forglory.id("undead_army_voice");
    public static final SoundEvent UNDEAD_ARMY_VOICE = SoundEvent.of(UNDEAD_ARMY_VOICE_ID);

    public static final Identifier INVISIBLE_ID = Forglory.id("invisible");
    public static final SoundEvent INVISIBLE = SoundEvent.of(INVISIBLE_ID);

    public static final Identifier HEAL_TRAIL_ID = Forglory.id("heal_trail");
    public static final SoundEvent HEAL_TRAIL = SoundEvent.of(HEAL_TRAIL_ID);
    public static final Identifier HEAL_TRAIL_VOICE_ID = Forglory.id("heal_trail_voice");
    public static final SoundEvent HEAL_TRAIL_VOICE = SoundEvent.of(HEAL_TRAIL_VOICE_ID);

    public static final Identifier FIRE_ZONE_PULSE_ID = Forglory.id("fire_zone_pulse");
    public static final SoundEvent FIRE_ZONE_PULSE = SoundEvent.of(FIRE_ZONE_PULSE_ID);
    public static final Identifier FIRE_ZONE_VOICE_ID = Forglory.id("fire_zone_voice");
    public static final SoundEvent FIRE_ZONE_VOICE = SoundEvent.of(FIRE_ZONE_VOICE_ID);

    public static final Identifier INSTANT_KILLED_ID = Forglory.id("instant_killed");
    public static final SoundEvent INSTANT_KILLED = SoundEvent.of(INSTANT_KILLED_ID);

    public static final Identifier LAST_STANDING_ID = Forglory.id("last_standing");
    public static final SoundEvent LAST_STANDING = SoundEvent.of(LAST_STANDING_ID);
    public static final Identifier LAST_STANDING_VOICE_ID = Forglory.id("last_standing_voice");
    public static final SoundEvent LAST_STANDING_VOICE = SoundEvent.of(LAST_STANDING_VOICE_ID);

    public static final Identifier FIREWORKER_VOICE_ID = Forglory.id("fireworker_voice");
    public static final SoundEvent FIREWORKER_VOICE = SoundEvent.of(FIREWORKER_VOICE_ID);

    public static void init() {
        Registry.register(Registries.SOUND_EVENT, TIER_1_EFFECT_ID, TIER_1_WHOOSH_EVENT);
        Registry.register(Registries.SOUND_EVENT, TIER_2_EFFECT_ID, TIER_2_BASS_EVENT);
        Registry.register(Registries.SOUND_EVENT, TIER_3_EFFECT_ID, TIER_3_STRONG_BASS_EVENT);
        Registry.register(Registries.SOUND_EVENT, TIER_4_EFFECT_ID, TIER_4_OVERCHARGED_EVENT);

        Registry.register(Registries.SOUND_EVENT, MALE_GRUNT_ID, MALE_GRUNT);
        Registry.register(Registries.SOUND_EVENT, FEMALE_GRUNT_ID, FEMALE_GRUNT);
        Registry.register(Registries.SOUND_EVENT, FEMALE_DEATH_ID, FEMALE_DEATH);
        Registry.register(Registries.SOUND_EVENT, MALE_DEATH_ID, MALE_DEATH);

        Registry.register(Registries.SOUND_EVENT, INCRE_ID, INCRE);
        Registry.register(Registries.SOUND_EVENT, DIBILIS_ID, DIBILIS);
        Registry.register(Registries.SOUND_EVENT, DASH_ID, DASH);
        Registry.register(Registries.SOUND_EVENT, MOUNTAIN_ID, MOUNTAIN);
        Registry.register(Registries.SOUND_EVENT, FIRE_TRAIL_ACT_ID, FIRE_TRAIL_ACT);
        Registry.register(Registries.SOUND_EVENT, FIRE_TRAIL_ACT_VOICE_ID, FIRE_TRAIL_ACT_VOICE);
        Registry.register(Registries.SOUND_EVENT, VAMPIRISM_ID, VAMPIRISM);
        Registry.register(Registries.SOUND_EVENT, MACHINE_GUN_ID, MACHINE_GUN);
        Registry.register(Registries.SOUND_EVENT, MACHINE_GUN_VOICE_ID, MACHINE_GUN_VOICE);
        Registry.register(Registries.SOUND_EVENT, KNOCKBACK_FIST_ACT_ID, KNOCKBACK_FIST_ACT);
        Registry.register(Registries.SOUND_EVENT, KNOCKBACK_FISTED_ID, KNOCKBACK_FISTED);
        Registry.register(Registries.SOUND_EVENT, SHIELD_RES_HITS_ID, SHIELD_RES_HITS);
        Registry.register(Registries.SOUND_EVENT, SHIELD_FLIP_ID, SHIELD_FLIP);
        Registry.register(Registries.SOUND_EVENT, UNDEAD_ARMY_SPAWN_ID, UNDEAD_ARMY_SPAWN);
        Registry.register(Registries.SOUND_EVENT, UNDEAD_ARMY_VOICE_ID, UNDEAD_ARMY_VOICE);
        Registry.register(Registries.SOUND_EVENT, INVISIBLE_ID, INVISIBLE);
        Registry.register(Registries.SOUND_EVENT, HEAL_TRAIL_ID, HEAL_TRAIL);
        Registry.register(Registries.SOUND_EVENT, HEAL_TRAIL_VOICE_ID, HEAL_TRAIL_VOICE);
        Registry.register(Registries.SOUND_EVENT, FIRE_ZONE_PULSE_ID, FIRE_ZONE_PULSE);
        Registry.register(Registries.SOUND_EVENT, FIRE_ZONE_VOICE_ID, FIRE_ZONE_VOICE);
        Registry.register(Registries.SOUND_EVENT, INSTANT_KILLED_ID, INSTANT_KILLED);
        Registry.register(Registries.SOUND_EVENT, LAST_STANDING_ID, LAST_STANDING);
        Registry.register(Registries.SOUND_EVENT, LAST_STANDING_VOICE_ID, LAST_STANDING_VOICE);
    }
}
