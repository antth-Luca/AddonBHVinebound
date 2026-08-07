package io.github.antthluca.bhvinebound.init;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.items.custom.GFVineArmorSeeds;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BHVinebound.MODID);

    // Items
    public static final DeferredItem<Item> VINE_ARMOR_SEEDS = ITEMS.registerItem(
            "vine_armor_seeds", GFVineArmorSeeds::new);
}
