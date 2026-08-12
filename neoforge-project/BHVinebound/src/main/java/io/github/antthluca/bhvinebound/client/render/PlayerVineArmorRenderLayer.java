package io.github.antthluca.bhvinebound.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class PlayerVineArmorRenderLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    public static final Identifier VINE_ARMOR_TEXTURE = Identifier.fromNamespaceAndPath(
            BHVinebound.MODID, "textures/entity/player_vines.png");

    public PlayerVineArmorRenderLayer(RenderLayerParent<AvatarRenderState, PlayerModel> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int light, AvatarRenderState state, float v, float v1) {
        Minecraft mc = Minecraft.getInstance();
        if (mc == null) return;

        Player player = mc.player;
        if (player == null) return;

        VineArmorData data = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);
        if (data.isLocked()) return;

        renderColoredCutoutModel(
                getParentModel(),
                VINE_ARMOR_TEXTURE,
                poseStack,
                collector,
                light,
                state,
                0xFFFFFF,
                0
        );
    }
}
