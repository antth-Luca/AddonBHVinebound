package io.github.antthluca.bhvinebound.events;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.client.renderer.layers.VineArmorRenderLayer;
import net.minecraft.world.entity.player.PlayerSkin;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = BHVinebound.MODID)
public class VBClientEvents {
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent event) {
        for (PlayerSkin.ModelType modelType : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(modelType);
            if (renderer != null) {
                renderer.addLayer(new VineArmorRenderLayer(renderer));
            }
        }
    }
}
