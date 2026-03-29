package github.karlbrych.items;

import github.karlbrych.Testmod;
import github.karlbrych.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static github.karlbrych.blocks.ModBlocks.DAN_BLOCK;

public class ModItemGroups {
    public static final ItemGroup KYBERNA_BRAINROT= Registry.register(Registries.ITEM_GROUP, Identifier.of(Testmod.MOD_ID,"kyberna_brainrot"),
            FabricItemGroup.builder()
                    .icon(()-> new ItemStack(ModItems.KAKAL_POISON))
                    .displayName(Text.translatable("itemgroup.test-mod.kyberna_brainrot"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.KAKAL_POISON);
                        entries.add(ModBlocks.NOR_GATE_BLOCK);
                        entries.add(ModBlocks.NOT_GATE_BLOCK);
                        entries.add(ModBlocks.NAND_GATE_BLOCK);
                        entries.add(ModBlocks.AND_GATE_BLOCK);
                        entries.add(ModBlocks.OR_GATE_BLOCK);
                        entries.add(ModBlocks.VOLODA_BLOCK);
                        entries.add(ModItems.NEGR);
                        entries.add(DAN_BLOCK);
                    })
                    .build()
    );
    public static void registerItemGroups(){
        Testmod.LOGGER.info("Registering item groups for "+ Testmod.MOD_ID);
    }
}
