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
import net.puffish.skillsmod.api.Category;
import net.puffish.skillsmod.api.Skill;
import net.puffish.skillsmod.api.SkillsAPI;

import java.util.List;
import java.util.Optional;

public record VineArmorData(boolean unlocked) {
    public static final Identifier VINE_ARMOR_CATEGORY = Identifier.fromNamespaceAndPath(BHVinebound.MODID, "vine_armor");

    private static final List<String> ROOT_SKILLS = List.of(
            "cpsmmy0ax7t721pa",
            "ferds6gxxu7ejimp",
            "bjqzpvkg7b0lpa43",
            "f7auo06yitl8iuly",
            "mh61pmualgb0hg37"
    );

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
    public boolean isUnlocked() { return unlocked; }

    public boolean isLocked() { return !unlocked; }

    public VineArmorData setUnlocked(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            Optional<Category> opCategory = SkillsAPI.getCategory(VINE_ARMOR_CATEGORY);
            SkillsAPI.getCategory(VINE_ARMOR_CATEGORY)
                    .ifPresent(cat -> {
                        cat.unlock(serverPlayer);

                        for (String skill : ROOT_SKILLS) {
                            cat.getSkill(skill)
                                    .ifPresent(skl -> skl.unlock(serverPlayer));
                        }

                        cat.openScreen(serverPlayer);
                    });
        }

        return new VineArmorData(true);
    }
}
