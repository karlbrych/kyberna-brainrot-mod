package github.karlbrych;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import github.karlbrych.blocks.ModBlocks;
import github.karlbrych.items.ModItems;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TestmodRecipeProvider extends FabricRecipeProvider {

    public TestmodRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                var itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModBlocks.VOLODA_BLOCK)
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .input('A', ModItems.NEGR)
                        .input('B', Items.OBSIDIAN)
                        .criterion(hasItem(ModItems.NEGR), conditionsFromItem(ModItems.NEGR))
                        .offerTo(exporter, String.valueOf(Identifier.of(Testmod.MOD_ID, "voloda_block_from_negr")));
                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.KAKAL_POISON)
                        .pattern("ABA")
                        .input('A', Items.SPIDER_EYE)
                        .input('B', ModItems.NEGR)
                        .criterion(hasItem(ModItems.NEGR), conditionsFromItem(ModItems.NEGR))
                        .offerTo(exporter);
                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.NEGR, 8)
                        .pattern("AAA")
                        .pattern("AAA")
                        .input('A', Items.BONE)
                        .criterion(hasItem(Items.BONE), conditionsFromItem(Items.BONE))
                        .offerTo(exporter);
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.NEGR, 8)
                        .input(ModBlocks.VOLODA_BLOCK)
                        .criterion(hasItem(ModBlocks.VOLODA_BLOCK), conditionsFromItem(ModBlocks.VOLODA_BLOCK))
                        .offerTo(exporter, String.valueOf(Identifier.of(Testmod.MOD_ID, "cernejzmrd_from_bones")));

            }
        };
    }

    @Override
    public String getName() {
        return "TestmodRecipeProvider";
    }
}