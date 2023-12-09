package org.valkyrienskies.military

import com.mojang.blaze3d.platform.NativeImage
import me.alex_s168.meshlib.ModelLoadContext
import org.valkyrienskies.military.model.BakedObjModel
import kotlin.properties.Delegates

object MilModels {
    lateinit var TURRET_BASE_RAW: ModelLoadContext
    lateinit var TURRET_BASE: BakedObjModel
    lateinit var TURRET_BASE_TEX: NativeImage
    var TURRET_BASE_TEX_GL by Delegates.notNull<Int>()
}