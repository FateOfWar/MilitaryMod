package org.valkyrienskies.military.model

import com.mojang.blaze3d.platform.NativeImage
import me.alex_s168.math.AABB
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.animation.AnimationFrame
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection
import net.minecraft.resources.ResourceLocation
import kotlin.math.max

data class SimpleTextureAtlasSpriteSource(
    val img: NativeImage,
    val loc: ResourceLocation,
    val anim: AnimationMetadataSection = AnimationMetadataSection.EMPTY
) {
    val info: TextureAtlasSprite.Info get() =
        TextureAtlasSprite.Info(
            loc,
            img.width,
            img.height,
            anim
        )

    fun info(aabb: AABB): TextureAtlasSprite.Info =
        TextureAtlasSprite.Info(
            loc,
            max(aabb.width.toInt(), 1),
            max(aabb.height.toInt(), 1),
            anim
        )
}

class SimpleTextureAtlasSprite(
    val source: SimpleTextureAtlasSpriteSource,
    info: Info = source.info,
    mipLevel: Int,
    storageX: Int,
    storageY: Int,
    x: Int,
    y: Int,
): TextureAtlasSprite(
    TextureAtlas(source.loc),
    info,
    mipLevel,
    storageX,
    storageY,
    x,
    y,
    source.img
)