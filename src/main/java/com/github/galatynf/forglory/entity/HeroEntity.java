package com.github.galatynf.forglory.entity;

import com.github.galatynf.forglory.cardinal.MyComponents;
import com.github.galatynf.forglory.enumFeat.Feats;
import com.github.galatynf.forglory.imixin.IAdrenalinMixin;
import com.github.galatynf.forglory.init.SoundsInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.UUID;

//Ignore the world constructor error
public class HeroEntity extends ZombieEntity {

    private PlayerEntity owner = null;
    private AttributeContainer attributeContainer;
    private String texture;

    public HeroEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 0;
        int rand = (int) (Math.random()*6);
        texture = "hero"+rand;
    }

    @Override
    protected void initEquipment(LocalDifficulty difficulty) {
        int rand = (int) (Math.random()*4);
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
                //If rand equals 4 the hero should not have a helmet
        }
        rand = (int) (Math.random()*3);
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
        rand = (int) (Math.random()*3);
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
        rand = (int) (Math.random()*3);
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
        rand = (int) (Math.random()*2);
        switch (rand) {
            case(0):
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
                break;
            case(1):
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
                break;
        }
        rand = (int) (Math.random()*2);
        if (rand == 0) {
            this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
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
        this.goalSelector.add(4, new FollowTargetGoal(this, PlayerEntity.class, 5, false, true, (livingEntity) -> {
            UUID summonerID = MyComponents.SUMMONED.get(this).getSummoner();
            if(summonerID != null) {
                PlayerEntity summoner = world.getPlayerByUuid(summonerID);
                return (livingEntity.equals(summoner) && distanceTo(summoner) > 15F);
            }
            return false;
        }));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(3, new FollowTargetGoal(this, HostileEntity.class, 5, false, true, (livingEntity) -> !(livingEntity instanceof HeroEntity)));
    }

    @Override
    public boolean canPickUpLoot() {
        return false;
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();

        float multiplier = (float) (Math.random() + 0.5);
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                .setBaseValue(40 * multiplier);

        multiplier = (float) (Math.random() + 0.5);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.5D * multiplier);

        multiplier = (float) (Math.random() + 0.5);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3D * (multiplier/2));

        multiplier = (float) (Math.random() + 0.5);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .setBaseValue(5 * multiplier);

        multiplier = (float) (Math.random() + 0.5);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).setBaseValue(1.2D * multiplier);

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
        return SoundsInit.tier_1_whoosh_event;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource_1) {
        return SoundsInit.tier_1_whoosh_event;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsInit.tier_1_whoosh_event;
    }

    protected SoundEvent getStepSound() {
        return SoundsInit.tier_1_whoosh_event;
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
