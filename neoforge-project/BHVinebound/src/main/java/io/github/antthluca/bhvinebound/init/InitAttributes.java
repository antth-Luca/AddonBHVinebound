package io.github.antthluca.bhvinebound.init;

import io.github.antthluca.bhvinebound.BHVinebound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(
            BuiltInRegistries.ATTRIBUTE, BHVinebound.MODID);

    // Attributes
    public static final DeferredHolder<Attribute, Attribute> LOOTING = ATTRIBUTES.register(
            "looting", () -> new RangedAttribute(
                    "attribute." + BHVinebound.MODID + ".looting",
                    0, 0, 1024).setSyncable(true));
}
