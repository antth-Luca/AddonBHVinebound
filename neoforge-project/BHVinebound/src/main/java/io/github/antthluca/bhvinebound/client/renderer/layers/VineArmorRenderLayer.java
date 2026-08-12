package io.github.antthluca.bhvinebound.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.antthluca.bhvinebound.BHVinebound;
import io.github.antthluca.bhvinebound.init.InitAttachmentTypes;
import io.github.antthluca.bhvinebound.serializers.custom.VineArmorData;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class VineArmorRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private static final Identifier PLAYER_VINES_TEXTURE = Identifier.fromNamespaceAndPath(BHVinebound.MODID,
            "textures/entity/player_vines.png");

    public VineArmorRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1) {

    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                       AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        VineArmorData data = player.getData(InitAttachmentTypes.PLAYER_VINE_ARMOR);

        // Se estiver bloqueado, invisível pelo botão ou o player estiver invisível no jogo -> não renderiza
        if (data.isLocked() || data.isInvisible() || player.isInvisible()) {
            return;
        }

        PlayerModel<AbstractClientPlayer> model = this.getParentModel();

        // RenderType.entityCutoutNoCull permite transparência no PNG e desenha os dois lados das faces
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(PLAYER_VINES_TEXTURE));

        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }
}
