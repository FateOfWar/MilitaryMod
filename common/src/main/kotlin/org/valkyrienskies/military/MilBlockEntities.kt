package org.valkyrienskies.military

import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.util.datafix.fixes.References
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.valkyrienskies.military.blockentity.TurretBaseBlockEntity
import org.valkyrienskies.military.registry.DeferredRegister
import org.valkyrienskies.military.registry.RegistrySupplier

@Suppress("unused")
object MilBlockEntities {
    private val BLOCKENTITIES = DeferredRegister.create(MilitaryMod.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY)

    val TURRET_BASE = MilBlocks.TURRET_BASE withBE ::TurretBaseBlockEntity byName "turret_base"

    fun register() {
        BLOCKENTITIES.applyAll()
    }

    private infix fun <T : BlockEntity> Set<RegistrySupplier<out Block>>.withBE(blockEntity: (BlockPos, BlockState) -> T) =
        this to blockEntity

    private infix fun <T : BlockEntity> RegistrySupplier<out Block>.withBE(blockEntity: (BlockPos, BlockState) -> T) =
        setOf(this) to blockEntity

    private infix fun <T : BlockEntity> Block.withBE(blockEntity: (BlockPos, BlockState) -> T) =
        this to blockEntity

    private infix fun <T : BlockEntity> Pair<Set<RegistrySupplier<out Block>>, (BlockPos, BlockState) -> T>.byName(name: String): RegistrySupplier<BlockEntityType<T>> =
        BLOCKENTITIES.register(name) {
            val type = Util.fetchChoiceType(References.BLOCK_ENTITY, name)

            BlockEntityType.Builder.of(
                this.second,
                *this.first.map { it.get() }.toTypedArray()
            ).build(type)
        }
}
