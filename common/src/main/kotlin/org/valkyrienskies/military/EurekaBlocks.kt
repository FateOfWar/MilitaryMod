package org.valkyrienskies.military

import net.minecraft.core.Registry
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FireBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.MaterialColor
import org.valkyrienskies.military.block.*
import org.valkyrienskies.military.registry.DeferredRegister
import org.valkyrienskies.mod.common.hooks.VSGameEvents

@Suppress("unused")
object EurekaBlocks {
    private val BLOCKS = DeferredRegister.create(MilitaryMod.MOD_ID, Registry.BLOCK_REGISTRY)

    val ANCHOR = BLOCKS.register("anchor", ::AnchorBlock)
    val ENGINE = BLOCKS.register("engine", ::EngineBlock)
    val FLOATER = BLOCKS.register("floater", ::FloaterBlock)
    val BALLAST = BLOCKS.register("ballast", ::BallastBlock)

    fun register() {
        BLOCKS.applyAll()

        VSGameEvents.registriesCompleted.on { _, _ ->

        }
    }

    // Blocks should also be registered as items, if you want them to be able to be held
    // aka all blocks
    fun registerItems(items: DeferredRegister<Item>) {
        BLOCKS.forEach {
            items.register(it.name) { BlockItem(it.get(), Item.Properties().tab(EurekaItems.TAB)) }
        }
    }

}
