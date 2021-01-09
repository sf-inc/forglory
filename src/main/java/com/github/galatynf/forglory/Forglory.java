package com.github.galatynf.forglory;

import com.github.galatynf.forglory.blocks.EssenceInfuser;
import com.github.galatynf.forglory.blocks.QuickFireBlock;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.enumFeat.Tier;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.imixin.IFeatsMixin;
import com.github.galatynf.forglory.items.DebugItem;
import com.github.galatynf.forglory.items.damage.*;
import com.github.galatynf.forglory.items.heal.HealGem;
import com.github.galatynf.forglory.items.misc.MiscGem;
import com.github.galatynf.forglory.items.mobility.MobilityGem;
import com.github.galatynf.forglory.items.heal.*;
import com.github.galatynf.forglory.items.misc.*;
import com.github.galatynf.forglory.items.mobility.*;
import com.github.galatynf.forglory.statusEffects.LifeStealStatusEffect;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Forglory implements ModInitializer {
    public static final ItemGroup forGlory = FabricItemGroupBuilder.create(
            new Identifier("forglory", "gems"))
            .icon(() -> new ItemStack(Items.QUARTZ))
            .build();

    public static final Block essenceInfuser = new EssenceInfuser(FabricBlockSettings.of(Material.METAL).hardness(50.0f).lightLevel(15).sounds(BlockSoundGroup.GLASS));
    public static final Item essenceInfuserItem = new BlockItem(essenceInfuser, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final DamageGem damageGem = new DamageGem(new Item.Settings().group(forGlory));
    public static final FireTrailGem fireTrailGem = new FireTrailGem(new Item.Settings().group(forGlory));
    public static final SmiteGem smiteGem = new SmiteGem(new Item.Settings().group(forGlory));
    public static final StrengthGem strengthGem = new StrengthGem(new Item.Settings().group(forGlory));
    public static final DamageSlowedGem damageSlowedGem = new DamageSlowedGem(new Item.Settings().group(forGlory));
    public static final FireZoneGem fireZoneGem = new FireZoneGem(new Item.Settings().group(forGlory));
    public static final FireworkerGem fireworkerGem = new FireworkerGem(new Item.Settings().group(forGlory));
    public static final InstantKillGem instantKillGem = new InstantKillGem(new Item.Settings().group(forGlory));

    public static final HealGem healGem = new HealGem(new Item.Settings().group(forGlory));
    public static final HealingFistGem healingFistGem = new HealingFistGem(new Item.Settings().group(forGlory));
    public static final FireResistanceGem fireResistanceGem = new FireResistanceGem(new Item.Settings().group(forGlory));
    public static final CompanionHealGem companionHealGem = new CompanionHealGem(new Item.Settings().group(forGlory));
    public static final ShieldGem shieldGem = new ShieldGem(new Item.Settings().group(forGlory));
    public static final LastStandGem lastStandGem = new LastStandGem(new Item.Settings().group(forGlory));
    public static final HealTrailGem healTrailGem = new HealTrailGem(new Item.Settings().group(forGlory));

    public static final MiscGem miscGem = new MiscGem(new Item.Settings().group(forGlory));
    public static final DogGem dogGem = new DogGem(new Item.Settings().group(forGlory));
    public static final KnockbackFistGem knockbackFistGem = new KnockbackFistGem(new Item.Settings().group(forGlory));
    public static final MountainGem mountainGem = new MountainGem(new Item.Settings().group(forGlory));
    public static final BeesGem beesGem = new BeesGem(new Item.Settings().group(forGlory));
    public static final InvisibleGem invisibleGem = new InvisibleGem(new Item.Settings().group(forGlory));

    public static final MobilityGem mobilityGem = new MobilityGem(new Item.Settings().group(forGlory));
    public static final NoHungerGem noHungerGem = new NoHungerGem(new Item.Settings().group(forGlory));
    public static final SpeedGem speedGem = new SpeedGem(new Item.Settings().group(forGlory));
    public static final DashGem dashGem = new DashGem(new Item.Settings().group(forGlory));
    public static final JumpBoostGem jumpBoostGem = new JumpBoostGem(new Item.Settings().group(forGlory));

    public static final QuickFireBlock quickFireBlock = new QuickFireBlock(FabricBlockSettings
            .of(Material.FIRE, MaterialColor.PURPLE).breakInstantly().noCollision().lightLevel(15).sounds(BlockSoundGroup.WOOL));

    public static final DebugItem debugItem = new DebugItem(new Item.Settings().group(forGlory));

    public static final LifeStealStatusEffect lifeStealStatusEffect = new LifeStealStatusEffect();

    public static final Identifier TIER_1_EFFECT_ID = new Identifier("forglory:tier_1_whoosh");
    public static SoundEvent tier_1_whoosh_event = new SoundEvent(TIER_1_EFFECT_ID);

    public static final Identifier TIER_2_EFFECT_ID = new Identifier("forglory:tier_2_bass");
    public static SoundEvent tier_2_bass_event = new SoundEvent(TIER_2_EFFECT_ID);

    public static final Identifier TIER_3_EFFECT_ID = new Identifier("forglory:tier_3_strong_bass");
    public static SoundEvent tier_3_strong_bass_event = new SoundEvent(TIER_3_EFFECT_ID);

    public static final Identifier TIER_4_EFFECT_ID = new Identifier("forglory:tier_4_overcharged");
    public static SoundEvent tier_4_overcharged_event = new SoundEvent(TIER_4_EFFECT_ID);

    public static final Identifier ACTIVATE_FEAT_PACKET_ID = new Identifier("forglory", "activate_feat");
    public static final Identifier BERSERK_PACKET_ID = new Identifier("forglory", "is_berserk");

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

        Registry.register(Registry.BLOCK, new Identifier("forglory", "essence_infuser"), essenceInfuser);
        Registry.register(Registry.ITEM, new Identifier("forglory", "essence_infuser"), essenceInfuserItem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_gem"), damageGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fire_trail_gem"), fireTrailGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "smite_gem"), smiteGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "strength_gem"), strengthGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "damage_slowed_gem"), damageSlowedGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fire_zone_gem"), fireZoneGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fireworker_gem"), fireworkerGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "instant_kill_gem"), instantKillGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "heal_gem"), healGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "healing_fist_gem"), healingFistGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "fire_resistance_gem"), fireResistanceGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "companion_heal_gem"), companionHealGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "shield_gem"), shieldGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "last_stand_gem"), lastStandGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "heal_trail_gem"), healTrailGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "misc_gem"), miscGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "dog_gem"), dogGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "knockback_fist_gem"), knockbackFistGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "mountain_gem"), mountainGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "bees_gem"), beesGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "invisible_gem"), invisibleGem);

        Registry.register(Registry.ITEM, new Identifier("forglory", "mobility_gem"), mobilityGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "no_hunger_gem"), noHungerGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "speed_gem"), speedGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "dash_gem"), dashGem);
        Registry.register(Registry.ITEM, new Identifier("forglory", "jump_boost_gem"), jumpBoostGem);

        Registry.register(Registry.BLOCK, new Identifier("forglory", "quick_fire"), quickFireBlock);

        Registry.register(Registry.ITEM, new Identifier("forglory", "debug_item"), debugItem);

        Registry.register(Registry.STATUS_EFFECT, new Identifier("forglory", "life_steal_status_effect"), lifeStealStatusEffect);

        Registry.register(Registry.SOUND_EVENT, Forglory.TIER_1_EFFECT_ID, tier_1_whoosh_event);
        Registry.register(Registry.SOUND_EVENT, Forglory.TIER_2_EFFECT_ID, tier_2_bass_event);
        Registry.register(Registry.SOUND_EVENT, Forglory.TIER_3_EFFECT_ID, tier_3_strong_bass_event);
        Registry.register(Registry.SOUND_EVENT, Forglory.TIER_4_EFFECT_ID, tier_4_overcharged_event);

        ServerSidePacketRegistry.INSTANCE.register(ACTIVATE_FEAT_PACKET_ID, (packetContext, attachedData) -> {
            int verifier = attachedData.readInt();
            if (verifier == 42) {
                packetContext.getTaskQueue().execute(() -> {
                    PlayerEntity playerEntity = packetContext.getPlayer();
                    Feats feat = ((IFeatsMixin) playerEntity).getFeat(Tier.TIER2);
                    if (feat == null) return;
                    if (((IAdrenalinMixin) playerEntity).getAdrenalin() > Tier.TIER2.threshold &&
                            ((IFeatsMixin)playerEntity).getCooldown(Tier.TIER2) == 0) {
                        if (feat.equals(Feats.MOUNTAIN)) {
                            NoMixinFeats.mountainFeat(playerEntity);
                        }
                        ((IFeatsMixin) playerEntity).resetCooldown(Tier.TIER2);
                    }
                });
            }
        });
    }
}
