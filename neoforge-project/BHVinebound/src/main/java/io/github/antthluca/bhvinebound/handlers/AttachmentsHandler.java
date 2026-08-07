package io.github.antthluca.bhvinebound.handlers;

import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.networking.packets.VineArmorDataSyncPayload;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class AttachmentsHandler {
    public static void setAndSyncVineArmor(Player player, VineArmorData data) {
        player.setData(
                InitAttachmentTypes.PLAYER_VINE_ARMOR,
                data
        );

        if (player instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new VineArmorDataSyncPayload(data));
        }
    }

    public static void syncVineArmor(ServerPlayer serverPlayer) {
        PacketDistributor.sendToPlayer(serverPlayer, new VineArmorDataSyncPayload(
                serverPlayer.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR)
        ));
    }
}
