package org.valkyrienskies.military

import net.minecraft.core.Registry
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import org.valkyrienskies.military.registry.DeferredRegister
import org.valkyrienskies.mod.common.hooks.VSGameEvents

@Suppress("unused")
object MilBlocks {
    private val BLOCKS = DeferredRegister.create(MilitaryMod.MOD_ID, Registry.BLOCK_REGISTRY)

    // val ANCHOR = BLOCKS.register("anchor", ::AnchorBlock)

    fun register() {
        BLOCKS.applyAll()

        VSGameEvents.registriesCompleted.on { _, _ ->

        }
    }

    // Blocks should also be registered as items, if you want them to be able to be held
    // aka all blocks
    fun registerItems(items: DeferredRegister<Item>) {
        BLOCKS.forEach {
            items.register(it.name) { BlockItem(it.get(), Item.Properties().tab(MilItems.TAB)) }
        }
    }

}
