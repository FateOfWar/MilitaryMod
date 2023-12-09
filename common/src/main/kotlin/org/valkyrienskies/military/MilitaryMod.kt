package org.valkyrienskies.military

import me.alex_s168.meshlib.format.OBJModelFormat
import net.minecraft.resources.ResourceLocation
import org.valkyrienskies.core.impl.config.VSConfigClass
import org.valkyrienskies.military.model.BakedObjModel
import org.valkyrienskies.military.util.getRes
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
            val model = fmt.loadFrom(a)
            MilModels.TURRET_BASE_RAW = model
            MilModels.TURRET_BASE = BakedObjModel(model, mapOf())
        }
    }
}
