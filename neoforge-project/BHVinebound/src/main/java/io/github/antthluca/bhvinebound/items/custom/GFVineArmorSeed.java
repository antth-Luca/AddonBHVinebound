package io.github.antthluca.bhvinebound.items.custom;

import io.github.antthluca.bhvinebound.handlers.AttachmentsHandler;
import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Consumer;

public class GFVineArmorSeed extends Item {
    public GFVineArmorSeed(Properties props) {
        super(props
                .stacksTo(1)
                .rarity(Rarity.RARE)
                .component(
                        DataComponents.CONSUMABLE,
                        Consumable.builder()
                                .animation(ItemUseAnimation.EAT)
                                .build()
                )
        );
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        if (Minecraft.getInstance().hasShiftDown()) {
            builder.accept(
                    Component.translatable("item.bhvinebound.vine_armor_seed.tooltip")
                            .withStyle(ChatFormatting.GRAY));
            builder.accept(
                    Component.translatable("item.bhvinebound.vine_armor_seed.effect_tooltip")
                            .withStyle(ChatFormatting.GRAY));
        } else {
            builder.accept(
                    Component.translatable("item.blue_hearts.common_tooltip")
                            .withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            this.onConsumed(player);
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            }

            if (!player.getAbilities().instabuild) {
                return ItemStack.EMPTY;
            }
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 32;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (this.canEat(player)) {
            player.startUsingItem(hand);
            return super.use(level, player, hand);
        }

        if (!level.isClientSide()) {
            Vec3 playerPos = player.position();
            level.playSound(
                    null,
                    playerPos.x, playerPos.y, playerPos.z,
                    SoundEvents.PIGLIN_CONVERTED_TO_ZOMBIFIED,
                    SoundSource.PLAYERS,
                    0.4F, 2.0F
            );
            level.playSound(
                    null,
                    playerPos.x, playerPos.y, playerPos.z,
                    SoundEvents.AZALEA_LEAVES_BREAK,
                    SoundSource.PLAYERS,
                    1.0F, 1.0F
            );
        }

        player.getCooldowns().addCooldown(player.getItemInHand(hand), 20);
        return InteractionResult.FAIL;
    }

    public boolean canEat(Player player) {
        VineArmorData data = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);
        return data.isLocked();
    }

    public void onConsumed(Player player) {
        AttachmentsHandler.setAndSyncVineArmor(
                player,
                player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR)
                        .setUnlocked(player)
        );
    }
}
