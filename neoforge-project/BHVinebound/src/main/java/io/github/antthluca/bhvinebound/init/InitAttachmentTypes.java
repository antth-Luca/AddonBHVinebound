package io.github.antthluca.bhvinebound.init;

import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class InitAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> TYPES = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, BHVinebound.MODID);

    // Attachment Types
    public static final Supplier<AttachmentType<VineArmorData>> PLAYER_VINE_ARMOR = TYPES.register(
            "player_vine_armor", () -> AttachmentType.builder(VineArmorData::getDefault)
                    .serialize(VineArmorData.MAP_CODEC)
                    .copyOnDeath()
                    .build()
    );

}
