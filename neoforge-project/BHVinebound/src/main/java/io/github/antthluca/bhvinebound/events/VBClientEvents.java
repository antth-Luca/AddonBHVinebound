package io.github.antthluca.bhvinebound.events;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.client.render.PlayerVineArmorRenderLayer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.entity.player.PlayerModelType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = BHVinebound.MODID, value = Dist.CLIENT)
public class VBClientEvents {
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerModelType playerModelType : event.getSkins()) {
            AvatarRenderer<?> renderer = event.getPlayerRenderer(playerModelType);
            if (renderer != null) {
                renderer.addLayer(new PlayerVineArmorRenderLayer(renderer));
            }
        }
    }
}
