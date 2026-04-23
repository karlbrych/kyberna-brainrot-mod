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
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import github.karlbrych.Testmod;
import github.karlbrych.blocks.ModBlocks;
import github.karlbrych.items.ModItems;

public class DanBlockRecipe extends FabricRecipeProvider {

	public DanBlockRecipe(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		
		return "DanBlockRecipe";
	}

	@Override
	protected RecipeGenerator getRecipeGenerator(WrapperLookup registryLookup, RecipeExporter exporter) {
		return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                var itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                //recipe 1 
                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, ModBlocks.DAN_BLOCK)
                        .pattern("AAA")
                        .pattern("BBB")
                        .pattern("AAA")
                        .input('A', Items.BONE_BLOCK)
                        .input('B', ModItems.KAKAL_POISON)
                        .criterion(hasItem(ModItems.KAKAL_POISON), conditionsFromItem(ModItems.KAKAL_POISON))
                        .offerTo(exporter, String.valueOf(Identifier.of(Testmod.MOD_ID, "dan_block_from_kakal")));
                //recipe 2
                ShapelessRecipeJsonBuilder.create(itemLookup, RecipeCategory.MISC, Items.SKELETON_SKULL, 1)
                        .input(ModBlocks.DAN_BLOCK)
                        .criterion(hasItem(ModBlocks.DAN_BLOCK), conditionsFromItem(ModBlocks.DAN_BLOCK))
                        .offerTo(exporter, String.valueOf(Identifier.of(Testmod.MOD_ID, "skull_from_dan")));
            }
        };
    }
	}


