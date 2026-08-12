package io.github.antthluca.bhvinebound.events;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.handlers.AttachmentsHandler;
import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.networking.packets.VineArmorDataSyncPayload;
import io.github.antthluca.bhvinebound.networking.packets.VineArmorToggleSyncPayload;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = BHVinebound.MODID)
public class VBNetworking {
    @SubscribeEvent
    public static void onRegisterNetworking(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(BHVinebound.MODID).versioned("1.0");

        registrar.playToClient(
                VineArmorDataSyncPayload.TYPE,
                VineArmorDataSyncPayload.STREAM_CODEC,
                (pyl, ctx) -> {
                    ctx.enqueueWork(() -> {
                        var player = Minecraft.getInstance().player;
                        if (player != null) {
                            player.setData(
                                    InitAttachmentTypes.PLAYER_VINE_ARMOR,
                                    pyl.data()
                            );
                        }
                    });
                }
        );

        registrar.playToServer(
                VineArmorToggleSyncPayload.TYPE,
                VineArmorToggleSyncPayload.STREAM_CODEC,
                (pyl, ctx) -> {
                    ctx.enqueueWork(() -> {
                        Player player = ctx.player();
                        if (player != null) {
                            VineArmorData data = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);
                            if (data.isUnlocked()) {
                                VineArmorData newData = data.toggleVisibility();

                                player.setData(
                                        InitAttachmentTypes.PLAYER_VINE_ARMOR,
                                        newData
                                );

                                PacketDistributor.sendToPlayersTrackingEntityAndSelf(
                                        player,
                                        new VineArmorDataSyncPayload(newData)
                                );
                            }
                        }
                    });
                }
        );
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                AttachmentsHandler.syncVineArmor(serverPlayer);
            }
        }
    }
}
