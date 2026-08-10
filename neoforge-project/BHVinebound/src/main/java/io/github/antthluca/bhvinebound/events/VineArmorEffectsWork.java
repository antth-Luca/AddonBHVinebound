package io.github.antthluca.bhvinebound.events;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = BHVinebound.MODID)
public class VineArmorEffectsWork {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        VineArmorData vineArmor = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);
        if (vineArmor.isLocked()) return;

        // Burn eternally
        if (player.getRemainingFireTicks() == 1) {
            player.setRemainingFireTicks(20);
        }

        // Water removes negative effects
        if (player.isInWater()) {
            for (MobEffectInstance instance : player.getActiveEffects()) {
                Holder<MobEffect> effect = instance.getEffect();
                if (effect.value().getCategory() == MobEffectCategory.HARMFUL) {
                    player.removeEffect(effect);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        Entity attacker = event.getSource().getEntity();
        if (attacker == null || attacker.level().isClientSide()) return;

        if (attacker instanceof Player player) {
            VineArmorData vineArmor = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);
            if (vineArmor.isLocked()) return;

            // Damage reduced 25%
            event.setNewDamage(event.getNewDamage() * 0.75F);
        }
    }
}
