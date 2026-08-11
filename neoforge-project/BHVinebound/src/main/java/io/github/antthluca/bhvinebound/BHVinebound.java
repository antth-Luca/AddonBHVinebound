package io.github.antthluca.bhvinebound;

import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.init.InitAttributes;
import io.github.antthluca.bhvinebound.init.InitItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(BHVinebound.MODID)
public class BHVinebound {
    public static final String MODID = "bhvinebound";

    public BHVinebound(IEventBus bus, ModContainer container) {
        // Init
        InitAttachmentTypes.TYPES.register(bus);
        InitAttributes.ATTRIBUTES.register(bus);
        InitItems.ITEMS.register(bus);
    }
}