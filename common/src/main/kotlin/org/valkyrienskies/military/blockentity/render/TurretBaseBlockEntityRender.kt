package org.valkyrienskies.military.blockentity.render

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import org.valkyrienskies.military.MilModels
import org.valkyrienskies.military.blockentity.TurretBaseBlockEntity

class TurretBaseBlockEntityRender(val ctx: BlockEntityRendererProvider.Context):
    BlockEntityRenderer<TurretBaseBlockEntity> {

    override fun render(
        be: TurretBaseBlockEntity,
        partial: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        poseStack.pushPose()
        poseStack.translate(0.5, 0.60, 0.5)

        MilModels.TURRET_BASE(
            poseStack,
            bufferSource,
            { RenderType.solid() },
            OverlayTexture.NO_OVERLAY,
            packedOverlay,
            partial
        )

        poseStack.popPose()
    }

}