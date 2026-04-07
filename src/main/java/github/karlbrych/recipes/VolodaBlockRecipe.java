package github.karlbrych.recipes;
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
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

import java.util.concurrent.CompletableFuture;

import github.karlbrych.Testmod;
import github.karlbrych.blocks.ModBlocks;
import github.karlbrych.items.ModItems;
import net.minecraft.util.Identifier;
public class VolodaBlockRecipe extends FabricRecipeProvider {

	public VolodaBlockRecipe(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public String getName() {
		
		return "VolodaBlockRecipe";
	}
	@Override
	protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                var itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                //recipe 1 
                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModBlocks.VOLODA_BLOCK)
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .input('A', ModItems.NEGR)
                        .input('B', Items.OBSIDIAN)
                        .criterion(hasItem(ModItems.NEGR), conditionsFromItem(ModItems.NEGR))
                        .offerTo(exporter, String.valueOf(Identifier.of(Testmod.MOD_ID, "voloda_block_from_negr")));
                //recipe 2
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModItems.NEGR, 8)
                        .input(ModBlocks.VOLODA_BLOCK)
                        .criterion(hasItem(ModBlocks.VOLODA_BLOCK), conditionsFromItem(ModBlocks.VOLODA_BLOCK))
                        .offerTo(exporter, String.valueOf(Identifier.of(Testmod.MOD_ID, "cernejzmrd_from_bones")));
            }
        };
    }
	
}
