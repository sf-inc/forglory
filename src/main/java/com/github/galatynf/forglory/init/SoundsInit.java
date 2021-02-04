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
    public static SoundEvent male_grunt = new SoundEvent(MALE_GRUNT_ID);

    public static final Identifier FEMALE_GRUNT_ID = new Identifier("forglory:female_grunt");
    public static SoundEvent female_grunt = new SoundEvent(FEMALE_GRUNT_ID);

    public static final Identifier FEMALE_DEATH_ID = new Identifier("forglory:female_death");
    public static SoundEvent female_death = new SoundEvent(FEMALE_DEATH_ID);

    public static final Identifier MALE_DEATH_ID = new Identifier("forglory:male_death");
    public static SoundEvent male_death = new SoundEvent(MALE_DEATH_ID);


    public static final Identifier INCRE_ID = new Identifier("forglory:incre");
    public static SoundEvent incre = new SoundEvent(INCRE_ID);

    public static final Identifier DIBILIS_ID = new Identifier("forglory:dibilis");
    public static SoundEvent dibilis = new SoundEvent(DIBILIS_ID);

    public static final Identifier DASH_ID = new Identifier("forglory:dash");
    public static SoundEvent dash = new SoundEvent(DASH_ID);

    public static final Identifier MOUNTAIN_ID = new Identifier("forglory:mountain");
    public static SoundEvent mountain = new SoundEvent(MOUNTAIN_ID);

    public static final Identifier  FIRE_TRAIL_ACT_ID = new Identifier("forglory:fire_trail_act");
    public static SoundEvent fire_trail_act = new SoundEvent(FIRE_TRAIL_ACT_ID);

    public static final Identifier VAMPIRISM_ID = new Identifier("forglory:vampirism");
    public static SoundEvent vampirism = new SoundEvent(VAMPIRISM_ID);

    public static final Identifier MACHINE_GUN_ID = new Identifier("forglory:machine_gun");
    public static SoundEvent machine_gun = new SoundEvent(MACHINE_GUN_ID);

    public static final Identifier KNOCKBACK_FIST_ACT_ID = new Identifier("forglory:knockback_fist_act");
    public static SoundEvent knockback_fist_act = new SoundEvent(KNOCKBACK_FIST_ACT_ID);

    public static final Identifier KNOCKBACK_FISTED_ID = new Identifier("forglory:knockback_fisted");
    public static SoundEvent knockback_fisted = new SoundEvent(KNOCKBACK_FISTED_ID);

    public static final Identifier SHIELD_RES_HITS_ID = new Identifier("forglory:shield_res_hits");
    public static SoundEvent shield_res_hits = new SoundEvent(SHIELD_RES_HITS_ID);

    public static final Identifier SHIELD_FLIP_ID = new Identifier("forglory:shield_flip");
    public static SoundEvent shield_flip = new SoundEvent(SHIELD_FLIP_ID);

    public static final Identifier UNDEAD_ARMY_SPAWN_ID = new Identifier("forglory:undead_army_spawn");
    public static SoundEvent undead_army_spawn = new SoundEvent(UNDEAD_ARMY_SPAWN_ID);

    public static final Identifier UNDEAD_ARMY_VOICE_ID = new Identifier("forglory:undead_army_voice");
    public static SoundEvent undead_army_voice = new SoundEvent(UNDEAD_ARMY_VOICE_ID);

    public static final Identifier INVISIBLE_ID = new Identifier("forglory:invisible");
    public static SoundEvent invisible = new SoundEvent(INVISIBLE_ID);

    public static final Identifier HEAL_TRAIL_ID = new Identifier("forglory:heal_trail");
    public static SoundEvent heal_trail = new SoundEvent(HEAL_TRAIL_ID);

    public static final Identifier FIRE_ZONE_PULSE_ID = new Identifier("forglory:fire_zone_pulse");
    public static SoundEvent fire_zone_pulse = new SoundEvent(FIRE_ZONE_PULSE_ID);

    public static final Identifier INSTANT_KILLED_ID = new Identifier("forglory:instant_killed");
    public static SoundEvent instant_killed = new SoundEvent(INSTANT_KILLED_ID);

    public static final Identifier LAST_STANDING_ID = new Identifier("forglory:last_standing");
    public static SoundEvent last_standing = new SoundEvent(LAST_STANDING_ID);

    public static final Identifier LAST_STANDING_VOICE_ID = new Identifier("forglory:last_standing_voice");
    public static SoundEvent last_standing_voice = new SoundEvent(LAST_STANDING_VOICE_ID);

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

        Registry.register(Registry.SOUND_EVENT, DASH_ID, dash);

        Registry.register(Registry.SOUND_EVENT, MOUNTAIN_ID, mountain);

        Registry.register(Registry.SOUND_EVENT, FIRE_TRAIL_ACT_ID, fire_trail_act);

        Registry.register(Registry.SOUND_EVENT, VAMPIRISM_ID, vampirism);

        Registry.register(Registry.SOUND_EVENT, MACHINE_GUN_ID, machine_gun);

        Registry.register(Registry.SOUND_EVENT, KNOCKBACK_FIST_ACT_ID, knockback_fist_act);

        Registry.register(Registry.SOUND_EVENT, KNOCKBACK_FISTED_ID, knockback_fisted);

        Registry.register(Registry.SOUND_EVENT, SHIELD_RES_HITS_ID, shield_res_hits);

        Registry.register(Registry.SOUND_EVENT, SHIELD_FLIP_ID, shield_flip);

        Registry.register(Registry.SOUND_EVENT, UNDEAD_ARMY_SPAWN_ID, undead_army_spawn);

        Registry.register(Registry.SOUND_EVENT, UNDEAD_ARMY_VOICE_ID, undead_army_voice);

        Registry.register(Registry.SOUND_EVENT, INVISIBLE_ID, invisible);

        Registry.register(Registry.SOUND_EVENT, HEAL_TRAIL_ID, heal_trail);

        Registry.register(Registry.SOUND_EVENT, FIRE_ZONE_PULSE_ID, fire_zone_pulse);

        Registry.register(Registry.SOUND_EVENT, INSTANT_KILLED_ID, instant_killed);

        Registry.register(Registry.SOUND_EVENT, LAST_STANDING_ID, last_standing);

        Registry.register(Registry.SOUND_EVENT, LAST_STANDING_VOICE_ID, last_standing_voice);

    }
}
