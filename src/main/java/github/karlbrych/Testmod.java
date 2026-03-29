package github.karlbrych;

import github.karlbrych.items.ModItemGroups;
import github.karlbrych.items.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static github.karlbrych.items.ModItems.*;
import static github.karlbrych.blocks.ModBlocks.*;
public class Testmod implements ModInitializer {
	public static final String MOD_ID = "test-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		registerModItems();
		registerModBlocks();
		ModItemGroups.registerItemGroups();
        LOGGER.info("Hello Fabric world!");
	}
}