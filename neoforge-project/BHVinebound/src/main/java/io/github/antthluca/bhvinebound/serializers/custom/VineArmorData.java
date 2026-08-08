package io.github.antthluca.bhvinebound.serializers.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

public record VineArmorData(boolean unlocked) {
    public static final MapCodec<VineArmorData> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("unlocked").forGetter(VineArmorData::unlocked)
            ).apply(instance, VineArmorData::new)
    );

    public static final StreamCodec<ByteBuf, VineArmorData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, VineArmorData::unlocked,
            VineArmorData::new
    );

    public static VineArmorData getDefault() {
        return new VineArmorData(false);
    }

    // Getters and Setters
    public boolean isUnlocked() {
        return unlocked;
    }

    public boolean isLocked() {
        return !unlocked;
    }

    public VineArmorData setUnlocked(Player player) {
        System.out.println("Skill Tree unlocked!");  // TODO: Trocar para o desbloqueio real;

        return new VineArmorData(true);
    }
}
