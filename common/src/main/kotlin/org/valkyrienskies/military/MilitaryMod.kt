package org.valkyrienskies.military

import net.minecraft.client.gui.font.FontManager
import org.valkyrienskies.core.impl.config.VSConfigClass


object MilitaryMod {
    const val MOD_ID = "military"
    val LOADING_SCREEN_IMAGES = arrayOf(
        "$MOD_ID:textures/gui/world_screen_1.png",
        "$MOD_ID:textures/gui/world_screen_2.png"
    )

    @JvmStatic
    fun init() {
        EurekaBlocks.register()
        EurekaBlockEntities.register()
        EurekaItems.register()
        EurekaScreens.register()
        EurekaWeights.register()
        VSConfigClass.registerConfig("military", EurekaConfig::class.java)
    }

    @JvmStatic
    fun initClient() {
        EurekaClientScreens.register()
    }
}
