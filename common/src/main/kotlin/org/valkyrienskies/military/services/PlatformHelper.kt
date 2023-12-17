package org.valkyrienskies.military.services

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import org.valkyrienskies.military.util.RenderFunction
import java.util.*

interface PlatformHelper {
    fun createCreativeTab(id: ResourceLocation, stack: () -> ItemStack): CreativeModeTab

    fun loadOBJModel(
        loc: ResourceLocation,
        detectCullableFaces: Boolean,
        diffuseLighting: Boolean,
        flipV: Boolean,
        ambientToFullbright: Boolean,
        materialLibraryOverrideLocation: String?,
        textures: Map<String, ResourceLocation>
    ): () -> RenderFunction?

    companion object {
        fun get(): PlatformHelper {
            return ServiceLoader.load(PlatformHelper::class.java)
                .findFirst()
                .get()
        }
    }
}