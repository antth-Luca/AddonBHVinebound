package io.github.antthluca.bhvinebound.networking.packets;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record VineArmorToggleSyncPayload() implements CustomPacketPayload {
    public static final Type<VineArmorToggleSyncPayload> TYPE = new Type<>(Identifier.fromNamespaceAndPath(
            BHVinebound.MODID, "vine_armor_toggle_sync"));

    public static final StreamCodec<ByteBuf, VineArmorToggleSyncPayload> STREAM_CODEC =
            StreamCodec.unit(new VineArmorToggleSyncPayload());

    @Override
    public Type<VineArmorToggleSyncPayload> type() { return TYPE; }
}
