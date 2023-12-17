package org.valkyrienskies.military.util

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation

typealias RenderFunction = (
    poseStack: PoseStack,
    bufferSource: MultiBufferSource,
    renderTypeFunction: (ResourceLocation) -> RenderType,
    lightmapCoord: Int,
    overlayCoord: Int,
    partialTicks: Float,
) -> Unit