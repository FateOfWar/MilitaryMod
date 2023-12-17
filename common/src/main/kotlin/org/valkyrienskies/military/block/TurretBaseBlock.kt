package org.valkyrienskies.military.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material
import org.valkyrienskies.military.blockentity.TurretBaseBlockEntity

class TurretBaseBlock: BaseEntityBlock(Properties.of(Material.METAL)) {

    override fun getRenderShape(blockState: BlockState): RenderShape {
        return RenderShape.INVISIBLE
    }

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity =
        TurretBaseBlockEntity(pos, state)

}