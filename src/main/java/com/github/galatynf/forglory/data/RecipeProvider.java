package com.github.galatynf.forglory.data;

import com.github.galatynf.forglory.init.ItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    private static final Item TIER_I_INGREDIENT = Items.LAPIS_LAZULI;
    private static final Item TIER_II_INGREDIENT = Items.EMERALD;
    private static final Item TIER_III_INGREDIENT = Items.DIAMOND;
    private static final Item TIER_IV_INGREDIENT = Items.NETHERITE_SCRAP;

    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        generateGemRecipe(ItemRegistry.ANTI_GEM_I, TIER_I_INGREDIENT).offerTo(exporter);
        generateGemRecipe(ItemRegistry.ANTI_GEM_II, TIER_II_INGREDIENT).offerTo(exporter);
        generateGemRecipe(ItemRegistry.ANTI_GEM_III, TIER_III_INGREDIENT).offerTo(exporter);
        generateGemRecipe(ItemRegistry.ANTI_GEM_IV, TIER_IV_INGREDIENT).offerTo(exporter);

        generateGemRecipe(ItemRegistry.DAMAGE_GEM, Items.BLAZE_POWDER).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.SMITE_GEM, ItemRegistry.DAMAGE_GEM, Items.TORCH).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.STRENGTH_GEM, ItemRegistry.DAMAGE_GEM, Items.STICK).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.FIRE_TRAIL_GEM, ItemRegistry.STRENGTH_GEM, Items.FLINT_AND_STEEL).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.MACHINE_GUN_GEM, ItemRegistry.SMITE_GEM, Items.BOW).offerTo(exporter);
        generatePoweredGemRecipeIII(ItemRegistry.DAMAGE_SLOWED_GEM, ItemRegistry.MACHINE_GUN_GEM, Items.CLOCK).offerTo(exporter);
        generatePoweredGemRecipeIII(ItemRegistry.SUPER_SHIELD_GEM, ItemRegistry.FIRE_TRAIL_GEM, Items.SHIELD).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.FIREWORKER_GEM, ItemRegistry.SUPER_SHIELD_GEM, Items.FIREWORK_ROCKET).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.FIRE_ZONE_GEM, ItemRegistry.SUPER_SHIELD_GEM, Items.LAVA_BUCKET).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.INSTANT_KILL_GEM, ItemRegistry.DAMAGE_SLOWED_GEM, Items.IRON_SWORD).offerTo(exporter);

        generateGemRecipe(ItemRegistry.HEAL_GEM, Items.GLISTERING_MELON_SLICE).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.RESISTANCE_GEM, ItemRegistry.HEAL_GEM, Items.IRON_INGOT).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.HEALING_FIST_GEM, ItemRegistry.RESISTANCE_GEM, Items.GLISTERING_MELON_SLICE).offerTo(exporter);
        generatePoweredGemRecipeIII(ItemRegistry.SHIELD_RESISTANCE_GEM, ItemRegistry.HEALING_FIST_GEM, Items.DIAMOND).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.HEAL_TRAIL_GEM, ItemRegistry.SHIELD_RESISTANCE_GEM, Items.GHAST_TEAR).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.LAST_STAND_GEM, ItemRegistry.SHIELD_RESISTANCE_GEM, Items.GOLDEN_APPLE).offerTo(exporter);

        generateGemRecipe(ItemRegistry.MISC_GEM, Items.NETHER_WART).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.ARROW_COMBO_GEM, ItemRegistry.MISC_GEM, Items.ARROW).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.BLOODLUST_GEM, ItemRegistry.MISC_GEM, Items.ROTTEN_FLESH).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.DOG_GEM, ItemRegistry.ARROW_COMBO_GEM, Items.BONE).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.KNOCKBACK_FIST_GEM, ItemRegistry.BLOODLUST_GEM, Items.STRING).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.MOUNTAIN_GEM, ItemRegistry.ARROW_COMBO_GEM, Items.DIRT).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.BEES_GEM, ItemRegistry.DOG_GEM, Items.HONEYCOMB).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.INVISIBLE_GEM, ItemRegistry.MOUNTAIN_GEM, Items.FERMENTED_SPIDER_EYE).offerTo(exporter);
        generatePoweredGemRecipeIV(ItemRegistry.UNDEAD_ARMY_GEM, ItemRegistry.KNOCKBACK_FIST_GEM, Items.BELL).offerTo(exporter);

        generateGemRecipe(ItemRegistry.MOBILITY_GEM, Items.SUGAR).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.NO_HUNGER_GEM, ItemRegistry.MOBILITY_GEM, Items.APPLE).offerTo(exporter);
        generatePoweredGemRecipeI(ItemRegistry.SPEED_GEM, ItemRegistry.MOBILITY_GEM, Items.SUGAR).offerTo(exporter);
        generatePoweredGemRecipeII(ItemRegistry.DASH_GEM, ItemRegistry.SPEED_GEM, Items.SLIME_BALL).offerTo(exporter);
        generatePoweredGemRecipeIII(ItemRegistry.STRIDERS_GRACE_GEM, ItemRegistry.DASH_GEM, Items.WARPED_FUNGUS).offerTo(exporter);
    }

    private static ShapedRecipeJsonBuilder generateGemRecipe(Item gem, Item tierIngredient) {
        return ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, gem)
                .pattern(" q ")
                .pattern("qtq")
                .pattern(" q ")
                .input('q', Items.QUARTZ)
                .input('t', tierIngredient)
                .criterion(FabricRecipeProvider.hasItem(tierIngredient),
                        FabricRecipeProvider.conditionsFromItem(tierIngredient));
    }

    private static ShapelessRecipeJsonBuilder generatePoweredGemRecipe(Item poweredGem, Item inputGem,
                                                                       Item tierIngredient, Item miscIngredient) {
        return ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, poweredGem)
                .input(inputGem)
                .input(tierIngredient)
                .input(miscIngredient)
                .criterion(FabricRecipeProvider.hasItem(miscIngredient),
                        FabricRecipeProvider.conditionsFromItem(miscIngredient));
    }

    private static ShapelessRecipeJsonBuilder generatePoweredGemRecipeI(Item poweredGem, Item inputGem,
                                                                        Item miscIngredient) {
        return generatePoweredGemRecipe(poweredGem, inputGem, TIER_I_INGREDIENT, miscIngredient);
    }

    private static ShapelessRecipeJsonBuilder generatePoweredGemRecipeII(Item poweredGem, Item inputGem,
                                                                        Item miscIngredient) {
        return generatePoweredGemRecipe(poweredGem, inputGem, TIER_II_INGREDIENT, miscIngredient);
    }

    private static ShapelessRecipeJsonBuilder generatePoweredGemRecipeIII(Item poweredGem, Item inputGem,
                                                                        Item miscIngredient) {
        return generatePoweredGemRecipe(poweredGem, inputGem, TIER_III_INGREDIENT, miscIngredient);
    }

    private static ShapelessRecipeJsonBuilder generatePoweredGemRecipeIV(Item poweredGem, Item inputGem,
                                                                        Item miscIngredient) {
        return generatePoweredGemRecipe(poweredGem, inputGem, TIER_IV_INGREDIENT, miscIngredient);
    }
}
