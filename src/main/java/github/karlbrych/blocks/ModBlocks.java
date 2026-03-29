package github.karlbrych.blocks;
import github.karlbrych.Testmod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static Block VOLODA_BLOCK;
    public static Block AND_GATE_BLOCK;
    public static Block OR_GATE_BLOCK;
    public static Block NOT_GATE_BLOCK;
    public static Block NAND_GATE_BLOCK;
    public static Block NOR_GATE_BLOCK;
    public static Block DAN_BLOCK;
    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Testmod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Testmod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testmod.MOD_ID, name)))));
                         
    }

    public static void registerModBlocks() {
        //VotrouBlock
        VOLODA_BLOCK = registerBlock("voloda_block", new Block(
                AbstractBlock.Settings.create()
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Testmod.MOD_ID, "voloda_block")))
                        .strength(6.7f, 67.0f)       // hardness, blast resistance
                        .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                        .requiresTool()
        ));

        //AND Gate
        AND_GATE_BLOCK= registerBlock("and_gate",new NorGateBlock(
           AbstractBlock.Settings.create()
                   .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Testmod.MOD_ID,"and_gate")))
                   .strength(0.5f)
                   .sounds(BlockSoundGroup.COPPER)
        ));
        OR_GATE_BLOCK=registerBlock("or_gate",new OrGateBlock(
           AbstractBlock.Settings.create()
                   .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Testmod.MOD_ID,"or_gate")))
                   .strength(0.5f)
                   .sounds(BlockSoundGroup.COPPER)
        ));
        NOT_GATE_BLOCK=registerBlock("not_gate",new NotGateBlock(
                AbstractBlock.Settings.create()
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Testmod.MOD_ID,"not_gate")))
                        .strength(0.5f)
                        .sounds(BlockSoundGroup.COPPER)
        ));
        NAND_GATE_BLOCK=registerBlock("nand_gate",new NandGateBlock(
                AbstractBlock.Settings.create()
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Testmod.MOD_ID,"nand_gate")))
                        .strength(0.5f)
                        .sounds(BlockSoundGroup.COPPER)
        ));
        NOR_GATE_BLOCK=registerBlock("nor_gate",new NorGateBlock(
                AbstractBlock.Settings.create()
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Testmod.MOD_ID,"nor_gate")))
                        .strength(0.5f)
                        .sounds(BlockSoundGroup.COPPER)
        ));
        DAN_BLOCK=registerBlock("dan_block",new DanBlock(
                AbstractBlock.Settings.create()
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Testmod.MOD_ID,"dan_block")))
                        .strength(0.5f)
                        .sounds(BlockSoundGroup.COPPER)
        ));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(VOLODA_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(AND_GATE_BLOCK);
            entries.add(OR_GATE_BLOCK);
            entries.add(NOT_GATE_BLOCK);
            entries.add(NAND_GATE_BLOCK);
            entries.add(NOR_GATE_BLOCK);
            entries.add(DAN_BLOCK);
        });
        Testmod.LOGGER.info("Registered mod blocks for " + Testmod.MOD_ID);
    }
}