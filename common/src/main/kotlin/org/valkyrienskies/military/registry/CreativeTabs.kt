package org.valkyrienskies.military.registry

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import org.valkyrienskies.military.services.PlatformHelper

class CreativeTabs {
    companion object {
        fun create(id: ResourceLocation, stack: () -> ItemStack): CreativeModeTab =
            PlatformHelper.get().createCreativeTab(id, stack)
    }
}