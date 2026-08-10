package io.github.antthluca.bhvinebound.init;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.items.custom.PoisonousVitalFruit;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BHVinebound.MODID);

    // Items
    public static final DeferredItem<Item> POISONOUS_VITAL_FRUIT = ITEMS.registerItem(
            "poisonous_vital_fruit", PoisonousVitalFruit::new);
}
