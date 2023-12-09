package org.valkyrienskies.military

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import org.valkyrienskies.military.registry.CreativeTabs
import org.valkyrienskies.military.registry.DeferredRegister

@Suppress("unused")
object MilItems {
    private val ITEMS = DeferredRegister.create(MilitaryMod.MOD_ID, Registry.ITEM_REGISTRY)
    val TAB: CreativeModeTab = CreativeTabs.create(
        ResourceLocation(
            MilitaryMod.MOD_ID,
            "mil_tab"
        )
    ) { ItemStack(Blocks.TNT) }

    fun register() {
        MilBlocks.registerItems(ITEMS)
        ITEMS.applyAll()
    }

    private infix fun Item.byName(name: String) =
        ITEMS.register(name) { this }
}
