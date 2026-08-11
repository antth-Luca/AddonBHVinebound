package io.github.antthluca.bhvinebound.events;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.init.InitAttributes;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber(modid = BHVinebound.MODID)
public class VBAttributes {
    @SubscribeEvent
    public static void onSetAttributes(EntityAttributeModificationEvent event) {
        InitAttributes.ATTRIBUTES.getEntries().forEach(attribute ->
                event.add(EntityType.PLAYER, attribute));
    }
}
