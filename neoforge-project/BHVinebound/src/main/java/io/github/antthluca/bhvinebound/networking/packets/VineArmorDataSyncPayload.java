package io.github.antthluca.bhvinebound.networking.packets;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record VineArmorDataSyncPayload(VineArmorData data) implements CustomPacketPayload {
    public static final Type<VineArmorDataSyncPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(
            BHVinebound.MODID, "vine_armor_data_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, VineArmorDataSyncPayload> STREAM_CODEC =
            StreamCodec.composite(
                    VineArmorData.STREAM_CODEC,
                    VineArmorDataSyncPayload::data,
                    VineArmorDataSyncPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
