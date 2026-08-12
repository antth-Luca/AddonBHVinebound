package io.github.antthluca.bhvinebound.serializers.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.antthluca.bhvinebound.BHVinebound;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.puffish.skillsmod.api.SkillsAPI;

public record VineArmorData(boolean unlocked, boolean visible) {
    public static final Identifier VINE_ARMOR_CATEGORY = Identifier.fromNamespaceAndPath(BHVinebound.MODID, "vine_armor");

    public static final MapCodec<VineArmorData> MAP_CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("unlocked").forGetter(VineArmorData::unlocked),
                    Codec.BOOL.fieldOf("visible").forGetter(VineArmorData::visible)
            ).apply(instance, VineArmorData::new)
    );

    public static final StreamCodec<ByteBuf, VineArmorData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, VineArmorData::unlocked,
            ByteBufCodecs.BOOL, VineArmorData::visible,
            VineArmorData::new
    );

    public static VineArmorData getDefault() {
        return new VineArmorData(false, true);
    }

    // Getters and Setters
    public boolean isUnlocked() { return unlocked; }

    public boolean isLocked() { return !unlocked; }

    public VineArmorData setUnlocked(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            SkillsAPI.getCategory(VINE_ARMOR_CATEGORY)
                    .ifPresent(cat -> cat.unlock(serverPlayer));
        }

        return new VineArmorData(true, visible);
    }

    public boolean isVisible() { return visible; }

    public boolean isInvisible() { return !visible; }

    public VineArmorData setVisible() {
        return new VineArmorData(unlocked, true);
    }

    public VineArmorData setInvisible() {
        return new VineArmorData(unlocked, false);
    }

    public VineArmorData toggleVisibility() {
        return new VineArmorData(unlocked, !visible);
    }
}
