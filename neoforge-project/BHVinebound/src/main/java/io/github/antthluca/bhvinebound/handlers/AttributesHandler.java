package io.github.antthluca.bhvinebound.handlers;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class AttributesHandler {
    public static int getIntValue(Player player, Holder<Attribute> attribute, double base) {
        if (attribute == null) {
            return 0;
        }

        AttributeMap playerAttributes = player.getAttributes();
        if (playerAttributes == null
            || !playerAttributes.hasAttribute(attribute)) {
                return (int) base;
        }

        double value = getAttributeValue(player, attribute, base);
        int clippedValue = (int) value;

        if (player.getRandom().nextFloat() < value - clippedValue) {
            value++;
        }

        return (int) value;
    }

    private static double getAttributeValue(Player player, Holder<Attribute> attribute, double base) {
        AttributeInstance instance = player.getAttribute(attribute);

        if (instance.getBaseValue() != base) {
            instance.setBaseValue(base);
        }

        return instance.getValue();
    }
}
