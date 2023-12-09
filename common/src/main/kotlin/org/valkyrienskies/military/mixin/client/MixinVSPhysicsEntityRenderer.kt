package org.valkyrienskies.military.mixin.client

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Vector4f
import me.alex_s168.math.Vec3
import me.alex_s168.meshlib.texture.TextureCoordinate
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.RenderShape
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Overwrite
import org.valkyrienskies.core.impl.game.ships.ShipObjectClientWorld
import org.valkyrienskies.military.MilModels
import org.valkyrienskies.mod.client.VSPhysicsEntityRenderer
import org.valkyrienskies.mod.common.IShipObjectWorldClientProvider
import org.valkyrienskies.mod.common.ValkyrienSkiesMod
import org.valkyrienskies.mod.common.entity.VSPhysicsEntity
import org.valkyrienskies.mod.common.util.toMinecraft


@Mixin(VSPhysicsEntityRenderer::class)
class MixinVSPhysicsEntityRenderer {

    /**
     * @author alex_s168
     * @reason VS does not support custom physics entity renderers yet
     */
    @Overwrite
    fun render(
        fallingBlockEntity: VSPhysicsEntity, f: Float, partialTick: Float, poseStack: PoseStack,
        multiBufferSource: MultiBufferSource, i: Int
    ) {
        val blockState = ValkyrienSkiesMod.TEST_SPHERE.defaultBlockState()
        if (blockState.renderShape != RenderShape.MODEL) {
            return
        }
        val level = fallingBlockEntity.getLevel()
        if (blockState === level.getBlockState(
                fallingBlockEntity.blockPosition()
            ) || blockState.renderShape == RenderShape.INVISIBLE
        ) {
            return
        }

        val renderTransform = fallingBlockEntity.getRenderTransform(
            ((Minecraft.getInstance() as IShipObjectWorldClientProvider).shipObjectWorld as ShipObjectClientWorld)
        ) ?: return

        val expectedX = fallingBlockEntity.xo + (fallingBlockEntity.x - fallingBlockEntity.xo) * partialTick
        val expectedY = fallingBlockEntity.yo + (fallingBlockEntity.y - fallingBlockEntity.yo) * partialTick
        val expectedZ = fallingBlockEntity.zo + (fallingBlockEntity.z - fallingBlockEntity.zo) * partialTick

        // Replace the default transform applied by mc with these offsets
        val offsetX = renderTransform.positionInWorld.x() - expectedX
        val offsetY = renderTransform.positionInWorld.y() - expectedY
        val offsetZ = renderTransform.positionInWorld.z() - expectedZ

        poseStack.pushPose()

        poseStack.translate(offsetX, offsetY, offsetZ)
        poseStack.mulPose(renderTransform.shipToWorldRotation.toMinecraft())
        poseStack.translate(-0.5, -0.5, -0.5)

        val blockRenderDispatcher = Minecraft.getInstance().blockRenderer
        blockRenderDispatcher.modelRenderer.renderModel(
            poseStack.last(),
            multiBufferSource.getBuffer(
                ItemBlockRenderTypes.getMovingBlockRenderType(blockState)
            ),
            blockState,
            MilModels.TURRET_BASE,
            1f, 1f, 1f,
            0,
            OverlayTexture.NO_OVERLAY
        )

        poseStack.popPose()
    }
}