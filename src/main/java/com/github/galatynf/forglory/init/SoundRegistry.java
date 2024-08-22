package com.github.galatynf.forglory.init;

import com.github.galatynf.forglory.Forglory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
    private SoundRegistry() {
    }

    public static final Identifier TIER_1_EFFECT_ID = Forglory.id("tier_1_whoosh");
    public static SoundEvent tier_1_whoosh_event = SoundEvent.of(TIER_1_EFFECT_ID);

    public static final Identifier TIER_2_EFFECT_ID = Forglory.id("tier_2_bass");
    public static SoundEvent tier_2_bass_event = SoundEvent.of(TIER_2_EFFECT_ID);

    public static final Identifier TIER_3_EFFECT_ID = Forglory.id("tier_3_strong_bass");
    public static SoundEvent tier_3_strong_bass_event = SoundEvent.of(TIER_3_EFFECT_ID);

    public static final Identifier TIER_4_EFFECT_ID = Forglory.id("tier_4_overcharged");
    public static SoundEvent tier_4_overcharged_event = SoundEvent.of(TIER_4_EFFECT_ID);


    public static final Identifier MALE_GRUNT_ID = Forglory.id("male_grunt");
    public static SoundEvent male_grunt = SoundEvent.of(MALE_GRUNT_ID);

    public static final Identifier FEMALE_GRUNT_ID = Forglory.id("female_grunt");
    public static SoundEvent female_grunt = SoundEvent.of(FEMALE_GRUNT_ID);

    public static final Identifier FEMALE_DEATH_ID = Forglory.id("female_death");
    public static SoundEvent female_death = SoundEvent.of(FEMALE_DEATH_ID);

    public static final Identifier MALE_DEATH_ID = Forglory.id("male_death");
    public static SoundEvent male_death = SoundEvent.of(MALE_DEATH_ID);


    public static final Identifier INCRE_ID = Forglory.id("incre");
    public static SoundEvent incre = SoundEvent.of(INCRE_ID);

    public static final Identifier DIBILIS_ID = Forglory.id("dibilis");
    public static SoundEvent dibilis = SoundEvent.of(DIBILIS_ID);

    public static final Identifier DASH_ID = Forglory.id("dash");
    public static SoundEvent dash = SoundEvent.of(DASH_ID);

    public static final Identifier MOUNTAIN_ID = Forglory.id("mountain");
    public static SoundEvent mountain = SoundEvent.of(MOUNTAIN_ID);

    public static final Identifier FIRE_TRAIL_ACT_ID = Forglory.id("fire_trail_act");
    public static SoundEvent fire_trail_act = SoundEvent.of(FIRE_TRAIL_ACT_ID);

    public static final Identifier FIRE_TRAIL_ACT_VOICE_ID = Forglory.id("fire_trail_act_voice");
    public static SoundEvent fire_trail_act_voice = SoundEvent.of(FIRE_TRAIL_ACT_VOICE_ID);

    public static final Identifier VAMPIRISM_ID = Forglory.id("vampirism");
    public static SoundEvent vampirism = SoundEvent.of(VAMPIRISM_ID);

    public static final Identifier MACHINE_GUN_ID = Forglory.id("machine_gun");
    public static SoundEvent machine_gun = SoundEvent.of(MACHINE_GUN_ID);

    public static final Identifier MACHINE_GUN_VOICE_ID = Forglory.id("machine_gun_voice");
    public static SoundEvent machine_gun_voice = SoundEvent.of(MACHINE_GUN_VOICE_ID);

    public static final Identifier KNOCKBACK_FIST_ACT_ID = Forglory.id("knockback_fist_act");
    public static SoundEvent knockback_fist_act = SoundEvent.of(KNOCKBACK_FIST_ACT_ID);

    public static final Identifier KNOCKBACK_FISTED_ID = Forglory.id("knockback_fisted");
    public static SoundEvent knockback_fisted = SoundEvent.of(KNOCKBACK_FISTED_ID);

    public static final Identifier SHIELD_RES_HITS_ID = Forglory.id("shield_res_hits");
    public static SoundEvent shield_res_hits = SoundEvent.of(SHIELD_RES_HITS_ID);

    public static final Identifier SHIELD_FLIP_ID = Forglory.id("shield_flip");
    public static SoundEvent shield_flip = SoundEvent.of(SHIELD_FLIP_ID);

    public static final Identifier UNDEAD_ARMY_SPAWN_ID = Forglory.id("undead_army_spawn");
    public static SoundEvent undead_army_spawn = SoundEvent.of(UNDEAD_ARMY_SPAWN_ID);

    public static final Identifier UNDEAD_ARMY_VOICE_ID = Forglory.id("undead_army_voice");
    public static SoundEvent undead_army_voice = SoundEvent.of(UNDEAD_ARMY_VOICE_ID);

    public static final Identifier INVISIBLE_ID = Forglory.id("invisible");
    public static SoundEvent invisible = SoundEvent.of(INVISIBLE_ID);

    public static final Identifier HEAL_TRAIL_ID = Forglory.id("heal_trail");
    public static SoundEvent heal_trail = SoundEvent.of(HEAL_TRAIL_ID);

    public static final Identifier HEAL_TRAIL_VOICE_ID = Forglory.id("heal_trail_voice");
    public static SoundEvent heal_trail_voice = SoundEvent.of(HEAL_TRAIL_VOICE_ID);

    public static final Identifier FIRE_ZONE_PULSE_ID = Forglory.id("fire_zone_pulse");
    public static SoundEvent fire_zone_pulse = SoundEvent.of(FIRE_ZONE_PULSE_ID);

    public static final Identifier FIRE_ZONE_VOICE_ID = Forglory.id("fire_zone_voice");
    public static SoundEvent fire_zone_voice = SoundEvent.of(FIRE_ZONE_VOICE_ID);

    public static final Identifier INSTANT_KILLED_ID = Forglory.id("instant_killed");
    public static SoundEvent instant_killed = SoundEvent.of(INSTANT_KILLED_ID);

    public static final Identifier LAST_STANDING_ID = Forglory.id("last_standing");
    public static SoundEvent last_standing = SoundEvent.of(LAST_STANDING_ID);

    public static final Identifier LAST_STANDING_VOICE_ID = Forglory.id("last_standing_voice");
    public static SoundEvent last_standing_voice = SoundEvent.of(LAST_STANDING_VOICE_ID);

    public static final Identifier FIREWORKER_VOICE_ID = Forglory.id("fireworker_voice");
    public static SoundEvent fireworker_voice = SoundEvent.of(FIREWORKER_VOICE_ID);

    public static void init() {
        Registry.register(Registries.SOUND_EVENT, TIER_1_EFFECT_ID, tier_1_whoosh_event);
        Registry.register(Registries.SOUND_EVENT, TIER_2_EFFECT_ID, tier_2_bass_event);
        Registry.register(Registries.SOUND_EVENT, TIER_3_EFFECT_ID, tier_3_strong_bass_event);
        Registry.register(Registries.SOUND_EVENT, TIER_4_EFFECT_ID, tier_4_overcharged_event);

        Registry.register(Registries.SOUND_EVENT, MALE_GRUNT_ID, male_grunt);
        Registry.register(Registries.SOUND_EVENT, FEMALE_GRUNT_ID, female_grunt);
        Registry.register(Registries.SOUND_EVENT, FEMALE_DEATH_ID, female_death);
        Registry.register(Registries.SOUND_EVENT, MALE_DEATH_ID, male_death);

        Registry.register(Registries.SOUND_EVENT, INCRE_ID, incre);
        Registry.register(Registries.SOUND_EVENT, DIBILIS_ID, dibilis);
        Registry.register(Registries.SOUND_EVENT, DASH_ID, dash);
        Registry.register(Registries.SOUND_EVENT, MOUNTAIN_ID, mountain);
        Registry.register(Registries.SOUND_EVENT, FIRE_TRAIL_ACT_ID, fire_trail_act);
        Registry.register(Registries.SOUND_EVENT, FIRE_TRAIL_ACT_VOICE_ID, fire_trail_act_voice);
        Registry.register(Registries.SOUND_EVENT, VAMPIRISM_ID, vampirism);
        Registry.register(Registries.SOUND_EVENT, MACHINE_GUN_ID, machine_gun);
        Registry.register(Registries.SOUND_EVENT, MACHINE_GUN_VOICE_ID, machine_gun_voice);
        Registry.register(Registries.SOUND_EVENT, KNOCKBACK_FIST_ACT_ID, knockback_fist_act);
        Registry.register(Registries.SOUND_EVENT, KNOCKBACK_FISTED_ID, knockback_fisted);
        Registry.register(Registries.SOUND_EVENT, SHIELD_RES_HITS_ID, shield_res_hits);
        Registry.register(Registries.SOUND_EVENT, SHIELD_FLIP_ID, shield_flip);
        Registry.register(Registries.SOUND_EVENT, UNDEAD_ARMY_SPAWN_ID, undead_army_spawn);
        Registry.register(Registries.SOUND_EVENT, UNDEAD_ARMY_VOICE_ID, undead_army_voice);
        Registry.register(Registries.SOUND_EVENT, INVISIBLE_ID, invisible);
        Registry.register(Registries.SOUND_EVENT, HEAL_TRAIL_ID, heal_trail);
        Registry.register(Registries.SOUND_EVENT, HEAL_TRAIL_VOICE_ID, heal_trail_voice);
        Registry.register(Registries.SOUND_EVENT, FIRE_ZONE_PULSE_ID, fire_zone_pulse);
        Registry.register(Registries.SOUND_EVENT, FIRE_ZONE_VOICE_ID, fire_zone_voice);
        Registry.register(Registries.SOUND_EVENT, INSTANT_KILLED_ID, instant_killed);
        Registry.register(Registries.SOUND_EVENT, LAST_STANDING_ID, last_standing);
        Registry.register(Registries.SOUND_EVENT, LAST_STANDING_VOICE_ID, last_standing_voice);
    }
}
