package io.github.antthluca.bhvinebound.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.antthluca.bhvinebound.BHVinebound;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.resources.Identifier;

public class PlayerVineArmorRenderLayer extends RenderLayer<AvatarRenderState, PlayerModel> {
    public static final Identifier VINE_ARMOR_TEXTURE = Identifier.fromNamespaceAndPath(
            BHVinebound.MODID, "textures/entity/player_vines.png");

    public PlayerVineArmorRenderLayer(RenderLayerParent<AvatarRenderState, PlayerModel> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int light, AvatarRenderState state, float v, float v1) {
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
