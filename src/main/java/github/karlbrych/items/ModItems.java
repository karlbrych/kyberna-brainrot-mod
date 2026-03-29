package github.karlbrych.items;

import github.karlbrych.Testmod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;  // ← correct name
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static Item NEGR;
    public static Item KAKAL_POISON;

    private static final ConsumableComponent KAKAL_POISON_CONSUMABLE = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.DARKNESS, 200, 1), 1.0f
            ))
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.POISON, 200, 1), 1.0f
            ))
            .build();

    private static final FoodComponent KAKAL_POISON_FOOD = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .build();

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Testmod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NEGR = registerItem("cernejzmrd", new Item(new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testmod.MOD_ID, "cernejzmrd")))));

        KAKAL_POISON = registerItem("kakal_poison", new Item(new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Testmod.MOD_ID, "kakal_poison")))
                .food(KAKAL_POISON_FOOD, KAKAL_POISON_CONSUMABLE)));

        Testmod.LOGGER.info("Registered mod items for " + Testmod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(NEGR);
            entries.add(KAKAL_POISON);
        });

        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(NEGR, 6767);
        });
    }
}