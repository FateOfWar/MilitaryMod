package org.valkyrienskies.military.model

import com.mojang.blaze3d.platform.NativeImage
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.resources.ResourceLocation

data class SimpleTextureAtlasSpriteSource(
    val img: NativeImage,
    val loc: ResourceLocation,
    val info: TextureAtlasSprite.Info,
)

class SimpleTextureAtlasSprite(
    source: SimpleTextureAtlasSpriteSource,
    mipLevel: Int,
    storageX: Int,
    storageY: Int,
    x: Int,
    y: Int,
): TextureAtlasSprite(
    TextureAtlas(source.loc),
    source.info,
    mipLevel,
    storageX,
    storageY,
    x,
    y,
    source.img
)