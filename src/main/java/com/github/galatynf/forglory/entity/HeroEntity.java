package com.github.galatynf.forglory.entity;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.init.SoundRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HeroEntity extends ZombieEntity {
    private final String texture;
    private final boolean isFemale;
    private static final String[] NAMES = {"Galatyn", "Pardys", "Zebus", "Chocofurtif", "Extoleon", "Holden", "Hervis", "Astrea", "Apollyon", "Goemon", "Sakura", "Lykeidon", "Iskandar", "Waver", "Dysnomia", "Denheb", "Altair", "Ordan", "Teshin", "Cressa", "Amaryn", "Mercy", "Siv", "Runa", "Seijuro", "Kenshi", "Ermac", "Ultra Galactron the Third, Destroyer of worlds", "NeroBrine", "Faering", "Syntribos", "Asbetos", "Sabaktes", "Rhamnusia", "Dikaiosyne"};

    public HeroEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 0;

        if (world.random.nextBoolean()) {
            this.isFemale = true;
            this.texture = "female_hero" + world.random.nextInt(4);
        } else {
            this.isFemale = false;
            this.texture = "male_hero" + world.random.nextInt(6);
        }
        String name = NAMES[world.random.nextInt(NAMES.length)];
        this.setCustomName(Text.literal(name));
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        int opness = ModConfig.get().feats.undeadArmy.heroesOPness;
        int rand = (int) (Math.random() * (7 + opness));
        switch (rand) {
            case 0:
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
                break;
            case 1:
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
                break;
            case 2:
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                break;
            //If rand is greater than 2 the hero should not have a helmet
        }
        rand = (int) (Math.random() * (7 + opness));
        switch (rand) {
            case 0:
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                break;
            case 1:
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
                break;
            case 2:
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
                break;
        }
        rand = (int) (Math.random() * (5 + opness));
        switch (rand) {
            case 0:
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
                break;
            case 1:
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
                break;
            case 2:
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
                break;
        }
        rand = (int) (Math.random() * (4 + opness));
        switch (rand) {
            case 0:
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
                break;
            case 1:
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
                break;
            case 2:
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
                break;
        }
        rand = (int) (Math.random() * (2 + opness));
        switch (rand) {
            case 0:
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                break;
            case 1:
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
                break;
        }
        // Purely cosmetic (that's nice if you have Optifine/lambdynamic lights installed :D)
        rand = (int) (Math.random() * 5);
        if (rand == 0) {
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        } else if (rand == 1) {
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.TORCH));
        }
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
        this.setBaby(false);
        return data;
    }

    @Override
    protected void initGoals() {
        // TODO: Add a new follow goal for the summoner (needs a new type of goal)
        // TODO: Rework target goals
        this.goalSelector.add(1, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, HostileEntity.class, 5, false, true, (livingEntity) -> !(livingEntity instanceof HeroEntity)));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, LivingEntity.class, 5, false, true, (livingEntity) -> {
            PlayerEntity summoner = this.getWorld().getPlayerByUuid(MyComponents.SUMMONED.get(this).getPlayer());
            if (summoner == null) return false;
            return (!(livingEntity instanceof HeroEntity) && (Objects.equals(summoner.getAttacker(), livingEntity) || Objects.equals(summoner.getAttacking(), livingEntity)));
        }));
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    protected void initAttributes() {
        List<Float> multipliers = new ArrayList<>(List.of(0.8f, 0.9f, 1f, 1.1f, 1.2f));
        Collections.shuffle(multipliers);

        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                .setBaseValue(25 * multipliers.getFirst());

        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(1D * multipliers.get(1));

        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3D * (multipliers.get(2)));

        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .setBaseValue(1.5 * multipliers.get(3));

        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.1D * multipliers.get(4));

        this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(2.0D);
    }

    @Override
    public void tick() {
        super.tick();
        UUID uuid = MyComponents.SUMMONED.get(this).getPlayer();
        if (uuid != null) {
            PlayerEntity playerEntity = this.getWorld().getPlayerByUuid(uuid);
            if (playerEntity != null) {
                if (MyComponents.ADRENALIN.get(playerEntity).getAdrenalin() < Feats.UNDEAD_ARMY.tier.getThreshold()) {
                    this.kill();
                    MyComponents.FEATS.get(playerEntity).resetCooldown(Feats.UNDEAD_ARMY.tier);
                }
            }
        }
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (!super.tryAttack(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity livingEntity) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100), this);
            }

            return true;
        }
    }

    @Override
    public boolean isConvertingInWater() {
        return false;
    }

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }

    @Override
    public boolean canSpawn(WorldView view) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_TURTLE_SHAMBLE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        if (!this.isFemale) {
            return SoundRegistry.MALE_GRUNT;
        } else {
            return SoundRegistry.FEMALE_GRUNT;
        }
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.BLOCK_WOOL_STEP;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return this.isFemale ? SoundRegistry.FEMALE_DEATH : SoundRegistry.MALE_DEATH;
    }

    public String getTexture() {
        return this.texture;
    }

    @Override
    protected boolean canConvertInWater() {
        return false;
    }

    @Override
    protected boolean shouldBreakDoors() {
        return false;
    }

    @Override
    protected void dropEquipment(ServerWorld world, DamageSource source, boolean causedByPlayer) {
    }
}
