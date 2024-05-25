package org.valkyrienskies.military

import net.minecraft.resources.ResourceLocation
import org.valkyrienskies.military.services.PlatformHelper
import org.valkyrienskies.military.util.RenderFunction

object MilModels {
    val TURRET_BASE: RenderFunction by lazy {
        val m = PlatformHelper.get().loadOBJModel(
            loc = ResourceLocation(MilitaryMod.MOD_ID, "models/special/turret_base.obj"),
            detectCullableFaces = true,
            diffuseLighting = true,
            flipV = false,
            ambientToFullbright = false,
            materialLibraryOverrideLocation = null,
            textures = mapOf(
                "turret_base.png" to ResourceLocation(MilitaryMod.MOD_ID, "textures/special/turret_base.png")
            )
        )()
        if (m == null) {
            MilLogger.error("Failed to load turret base model")
            return@lazy { _, _, _, _, _, _ -> }
        } else {
            m
        }
    }
}