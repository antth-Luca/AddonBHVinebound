package io.github.antthluca.bhvinebound.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.antthluca.bhvinebound.handlers.AttributesHandler;
import io.github.antthluca.bhvinebound.init.InitAttributes;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public abstract class VBEnchantmentHelperMixin {
    @ModifyReturnValue(method = "getEnchantmentLevel", at = @At("RETURN"))
    private static int bhvinebound$modifyLootingLevel(int original,
                                                    @Local(argsOnly = true) Holder<Enchantment> enchantment,
                                                    @Local(argsOnly = true) LivingEntity entity
    ) {
        if (entity instanceof Player player
            && enchantment.is(Enchantments.LOOTING)) {
            return AttributesHandler.getIntValue(player, InitAttributes.LOOTING, original);
        }

        return original;
    }
}
