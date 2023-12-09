package org.valkyrienskies.military

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.platform.NativeImage
import com.mojang.blaze3d.systems.RenderSystem
import me.alex_s168.meshlib.format.OBJModelFormat
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection
import net.minecraft.resources.ResourceLocation
import org.lwjgl.opengl.GL11
import org.valkyrienskies.core.impl.config.VSConfigClass
import org.valkyrienskies.military.model.BakedObjModel
import org.valkyrienskies.military.model.SimpleTextureAtlasSprite
import org.valkyrienskies.military.model.SimpleTextureAtlasSpriteSource
import org.valkyrienskies.military.util.getRes
import org.valkyrienskies.military.util.getResStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader


object MilitaryMod {
    const val MOD_ID = "military"
    val LOADING_SCREEN_IMAGES = arrayOf(
        "$MOD_ID:textures/gui/world_screen_1.png",
        "$MOD_ID:textures/gui/world_screen_2.png"
    )

    @JvmStatic
    fun init() {
        MilBlocks.register()
        MilBlockEntities.register()
        MilItems.register()
        VSConfigClass.registerConfig("military", MilConfig::class.java)
    }

    @JvmStatic
    fun initClient() {
        val fmt = OBJModelFormat()
        MilEvents.postLoad.on { _ ->
            val a = getRes(javaClass, "assets/$MOD_ID/models/special/turret_base.obj")!!.readText()
            val tex = getResStream(javaClass, "assets/$MOD_ID/textures/special/turret_base.png")!!
            val texLoc = ResourceLocation(MOD_ID, "textures/special/turret_base")
            val texImg = NativeImage.read(tex)
            val img = SimpleTextureAtlasSpriteSource(
                texImg,
                texLoc
            )
            val model = fmt.loadFrom(a)
            MilModels.TURRET_BASE_RAW = model
            MilModels.TURRET_BASE_TEX = texImg
            MilModels.TURRET_BASE = BakedObjModel(
                model,
                { img },
                SimpleTextureAtlasSprite(img, img.info, 0, 0, 0, 0, 0)
            )
        }
    }
}
