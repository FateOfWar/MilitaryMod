package org.valkyrienskies.military.model

import me.alex_s168.meshlib.ModelLoadContext
import me.alex_s168.meshlib.ModelRaw
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.ItemOverrides
import net.minecraft.client.renderer.block.model.ItemTransforms
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.model.BakedModel
import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState
import java.util.*

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
                val sprite = SimpleTextureAtlasSprite(texture, 0, (face.tex?.a?.u ?: 0).toInt() + 1, (face.tex?.a?.v ?: 0).toInt() + 1, 1, 1) // TODO
                val vert = face.tri.toMinecraftVertices(tex = sprite)
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