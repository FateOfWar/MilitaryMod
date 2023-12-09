package org.valkyrienskies.military.model

import me.alex_s168.math.AABB
import me.alex_s168.math.Vec3
import me.alex_s168.meshlib.ModelLoadContext
import me.alex_s168.meshlib.ModelRaw
import me.alex_s168.meshlib.Triangle
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.ItemOverrides
import net.minecraft.client.renderer.block.model.ItemTransforms
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.model.BakedModel
import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState
import java.util.*
import kotlin.math.max

class BakedObjModel(
    val model: ModelLoadContext,
    val textures: (ModelRaw) -> SimpleTextureAtlasSpriteSource,
    val particleTex: TextureAtlasSprite
): BakedModel {

    val quads = mutableListOf<BakedQuad>()

    init {
        model.groups.forEach {
            val texture = textures(it)
            it.mesh.forEach { face ->
                val tex = face.tex?.times(Vec3(texture.img.width.toFloat(), texture.img.height.toFloat(), 1f))
                println(tex)
                val sprite = SimpleTextureAtlasSprite(
                    texture,
                    texture.info(face.tex?.toTri()?.getAABB() ?: AABB(Vec3(), Vec3())),
                    mipLevel = 0,
                    storageY = 1,
                    storageX = 1,
                    x = 0,
                    y = 0
                )
                val vert = face.tri.toMinecraftVertices(
                    tex = sprite,
                    texCoords = tex?.minus(face.tri.getAABB().min)
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