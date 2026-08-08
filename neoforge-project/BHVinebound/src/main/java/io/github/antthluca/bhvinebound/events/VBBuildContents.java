package io.github.antthluca.bhvinebound.events;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.init.InitItems;
import io.github.antthluca.blue_hearts.init.InitCreativeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = BHVinebound.MODID)
public class VBBuildContents {
    @SubscribeEvent
    public static void onCreativeTabBuild(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == InitCreativeTabs.MAIN.getKey()) {
              event.accept(InitItems.VINE_ARMOR_SEED.get());
        }
    }
}
