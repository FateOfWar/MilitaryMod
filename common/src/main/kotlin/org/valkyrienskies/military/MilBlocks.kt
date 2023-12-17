package org.valkyrienskies.military

import net.minecraft.core.Registry
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import org.valkyrienskies.military.block.TurretBaseBlock
import org.valkyrienskies.military.registry.DeferredRegister

@Suppress("unused")
object MilBlocks {
    private val BLOCKS = DeferredRegister.create(MilitaryMod.MOD_ID, Registry.BLOCK_REGISTRY)

    val TURRET_BASE = BLOCKS.register("turret_base", ::TurretBaseBlock)

    fun register() {
        BLOCKS.applyAll()
    }

    // Blocks should also be registered as items, if you want them to be able to be held
    // aka all blocks
    fun registerItems(items: DeferredRegister<Item>) {
        BLOCKS.forEach {
            items.register(it.name) { BlockItem(it.get(), Item.Properties().tab(MilItems.TAB)) }
        }
    }

}
