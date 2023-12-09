package org.valkyrienskies.military.model

import me.alex_s168.math.AABB
import me.alex_s168.math.Vec3
import me.alex_s168.meshlib.ModelLoadContext
import me.alex_s168.meshlib.ModelRaw
import me.alex_s168.meshlib.Triangle
import me.alex_s168.meshlib.texture.TextureCoordinate
import me.alex_s168.meshlib.texture.TextureFace
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.ItemOverrides
import net.minecraft.client.renderer.block.model.ItemTransforms
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.model.BakedModel
import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import kotlin.math.max
import kotlin.math.round

class BakedObjModel(
    val model: ModelLoadContext,
    val textures: (ModelRaw) -> SimpleTextureAtlasSpriteSource,
    val particleTex: TextureAtlasSprite
): BakedModel {

    val quads = mutableListOf<BakedQuad>()

    init {
        model.groups.forEach {
            val texture = textures(it)
            val texSize = Vec3(texture.img.width.toFloat(), texture.img.height.toFloat(), 1f)
            it.mesh.forEach { face ->
                val tex = face.tex?.times(texSize).let { v ->
                    fun TextureCoordinate.round() =
                        TextureCoordinate(
                            round(x),
                            round(y),
                            round(z)
                        )
                    TextureFace(
                        v?.a?.round() ?: TextureCoordinate(0f, 0f, 0f),
                        v?.b?.round() ?: TextureCoordinate(0f, 0f, 0f),
                        v?.c?.round() ?: TextureCoordinate(0f, 0f, 0f)
                    )
                }
                println(tex)
                val texAABB = tex.toTri().getAABB()
                val sprite = SimpleTextureAtlasSprite(
                    texture,
                    texture.info,
                    mipLevel = 0,
                    storageY = 1,
                    storageX = 1,
                    x = 0,
                    y = 0,
                )
                val vert = face.tri.toMinecraftVertices(
                    tex = sprite,
                    texCoords = tex
                )
                quads += BakedQuad(vert, 0, Direction.UP, sprite, true)
            }
        }
    }

    override fun getQuads(state: BlockState?, side: Direction?, rand: Random): MutableList<BakedQuad> =
        quads

    override fun useAmbientOcclusion(): Boolean =
        true

    override fun isGui3d(): Boolean =
        false

    override fun usesBlockLight(): Boolean =
        true

    override fun isCustomRenderer(): Boolean =
        true // TODO: ???

    override fun getParticleIcon(): TextureAtlasSprite =
        particleTex

    override fun getTransforms(): ItemTransforms =
        ItemTransforms.NO_TRANSFORMS

    override fun getOverrides(): ItemOverrides =
        ItemOverrides.EMPTY
}