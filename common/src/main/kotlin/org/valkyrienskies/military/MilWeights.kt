package org.valkyrienskies.military

import net.minecraft.world.level.block.state.BlockState
import org.valkyrienskies.core.apigame.world.chunks.BlockType
import org.valkyrienskies.mod.common.BlockStateInfoProvider
import org.valkyrienskies.physics_api.Lod1BlockStateId
import org.valkyrienskies.physics_api.Lod1LiquidBlockStateId
import org.valkyrienskies.physics_api.Lod1SolidBlockStateId
import org.valkyrienskies.physics_api.voxel.Lod1LiquidBlockState
import org.valkyrienskies.physics_api.voxel.Lod1SolidBlockState

object MilWeights : BlockStateInfoProvider {
    override val blockStateData: List<Triple<Lod1SolidBlockStateId, Lod1LiquidBlockStateId, Lod1BlockStateId>>
        get() = listOf()
    override val liquidBlockStates: List<Lod1LiquidBlockState>
        get() = listOf()
    override val priority: Int
        get() = 200
    override val solidBlockStates: List<Lod1SolidBlockState>
        get() = listOf()

    override fun getBlockStateMass(blockState: BlockState): Double? =
        null

    override fun getBlockStateType(blockState: BlockState): BlockType? =
        null
}
