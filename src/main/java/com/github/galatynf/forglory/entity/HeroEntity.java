package com.github.galatynf.forglory.entity;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.config.ModConfig;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.*;

//Ignore the world constructor error
public class HeroEntity extends ZombieEntity {

    private PlayerEntity owner = null;
    private AttributeContainer attributeContainer;
    private String texture;
    private boolean isFemale;
    private static final String[] NAMES = {"Galatyn", "Pardys", "Zebus", "Chocofurtif", "Extoleon", "Holden", "Hervis", "Astrea", "Apollyon", "Goemon", "Sakura", "Lykeidon", "Iskandar", "Waver", "Dysnomia", "Denheb", "Altair", "Ordan", "Teshin", "Cressa", "Amaryn", "Mercy", "Siv", "Runa", "Seijuro", "Kenshi", "Ermac", "Ultra Galactron the Third, Destroyer of worlds", "NeroBrine", "Faering", "Syntribos", "Asbetos", "Sabaktes", "Rhamnusia", "Dikaiosyne"};

    public HeroEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 0;
        int rand = (int) (Math.random()*3);
        if(rand%2 == 0) {
            this.isFemale = true;
            this.texture = "female_hero"+rand;
        }
        else {
            this.isFemale = false;
            this.texture = "male_hero"+rand;
        }
        String name = NAMES[(int)(Math.random()*(NAMES.length-1))];
        this.setCustomName(Text.of(name));
    }

    @Override
    protected void initEquipment(LocalDifficulty difficulty) {
        int opness = ModConfig.get().featConfig.undeadArmyConfig.heroes_OPness;
        int rand = (int) (Math.random()*7 + opness);
        switch (rand) {
            case(0):
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
                break;
            case(1):
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.GOLDEN_HELMET));
                break;
            case(2):
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
                break;
                //If rand is greater than 2 the hero should not have a helmet
        }
        rand = (int) (Math.random()*7 + opness);
        switch (rand) {
            case(0):
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
                break;
            case(1):
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
                break;
            case(2):
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
                break;
        }
        rand = (int) (Math.random()*5 + opness);
        switch (rand) {
            case(0):
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
                break;
            case(1):
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
                break;
            case(2):
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
                break;
        }
        rand = (int) (Math.random()*4 + opness);
        switch (rand) {
            case(0):
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
                break;
            case(1):
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
                break;
            case(2):
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
                break;
        }
        rand = (int) (Math.random()*2 + opness);
        switch (rand) {
            case(0):
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                break;
            case(1):
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
                break;
        }
        // Purely cosmetic (that's nice if you have Optifine/lambdynamic lights installed :D)
        rand = (int) (Math.random()*5);
        if (rand == 0) {
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        }
        else if (rand == 1) {
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.TORCH));
        }
    }

    public void setOwner(PlayerEntity owner) {
        this.owner = owner;
    }

    public PlayerEntity getOwner() {
        return owner;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(3, new FollowTargetGoal(this, HostileEntity.class, 5, false, true, (livingEntity) -> !(livingEntity instanceof HeroEntity)));
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        Float[] multipliers = { 0.5f, 0.25f, 1f, 1.2f, 1.5f };
        int i = 0;

        List<Float> listMult = Arrays.asList(multipliers);

        Collections.shuffle(listMult);

        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                .setBaseValue(40 * listMult.get(i++));

        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.5D * listMult.get(i++));

        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3D * (listMult.get(i++)));

        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .setBaseValue(5 * listMult.get(i++));

        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.2D * listMult.get(i));

        this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(20.0D);
    }

    @Override
    public void tick() {
        super.tick();
        if(owner != null) {
            if (((IAdrenalinMixin) owner).getAdrenalin() < Feats.UNDEAD_ARMY.tier.threshold) {
                this.kill();
                MyComponents.FEATS.get(owner).resetCooldown(Feats.UNDEAD_ARMY.tier);
            }
        }
    }

    @Override
    public void dealDamage(LivingEntity attacker, Entity target) {
        super.dealDamage(attacker, target);
        if(target instanceof LivingEntity && (int)(Math.random()*5) == 0) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0, false, false));
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
        if(!isFemale) {
            return SoundsInit.male_grunt;
        }
        else {
            return SoundsInit.female_grunt;
        }
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.BLOCK_WOOL_STEP;
    }

    @Override
    protected SoundEvent getDeathSound() {
        SoundEvent returned;
        if(isFemale) {
            returned = SoundsInit.female_death;
        }
        else {
            returned = SoundsInit.male_death;
        }
        return returned;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public boolean isBaby() {
        return false;
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
    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
    }

    @Override
    public AttributeContainer getAttributes()
    {
        if(attributeContainer == null)
            attributeContainer =  new AttributeContainer(
                    HeroEntity.createZombieAttributes().build());
        return attributeContainer;
    }

}
