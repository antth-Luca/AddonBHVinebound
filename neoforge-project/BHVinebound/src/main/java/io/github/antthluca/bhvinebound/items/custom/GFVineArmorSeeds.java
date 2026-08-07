package io.github.antthluca.bhvinebound.items.custom;

import io.github.antthluca.bhvinebound.handlers.AttachmentsHandler;
import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class GFVineArmorSeeds extends Item {
    private static final FoodProperties VINE_ARMOR_SEEDS_PROP = new FoodProperties.Builder()
            .nutrition(0)
            .saturationModifier(0)
            .alwaysEdible()
            .build();

    public GFVineArmorSeeds(Properties props) {
        super(props
                .food(VINE_ARMOR_SEEDS_PROP)
                .stacksTo(1)
                .rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        if (Minecraft.getInstance().hasShiftDown()) {
            builder.accept(
                    Component.translatable("item.bhvinebound.vine_armor_seeds.tooltip")
                            .withStyle(ChatFormatting.GRAY));
            builder.accept(
                    Component.translatable("item.bhvinebound.vine_armor_seeds.effect_tooltip")
                            .withStyle(ChatFormatting.GRAY));
        } else {
            builder.accept(
                    Component.translatable("item.blue_hearts.common_tooltip")
                            .withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        VineArmorData data = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);
        if (data.isLocked()) {
            super.use(level, player, hand);
        }

        return InteractionResult.PASS;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        if (entity instanceof Player player) {
            AttachmentsHandler.setAndSyncVineArmor(
                    player,
                    player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR)
                            .setUnlocked()
            );

            return super.finishUsingItem(itemStack, level, entity);
        }

        return itemStack;
    }
}
