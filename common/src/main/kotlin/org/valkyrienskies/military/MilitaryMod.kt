package org.valkyrienskies.military

import org.valkyrienskies.core.impl.config.VSConfigClass

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
        MilWeights.register()
        VSConfigClass.registerConfig("military", MilConfig::class.java)
    }

    @JvmStatic
    fun initClient() {

    }
}
