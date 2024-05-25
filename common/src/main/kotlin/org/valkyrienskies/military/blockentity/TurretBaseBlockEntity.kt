package org.valkyrienskies.military.blockentity

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import org.valkyrienskies.military.MilBlockEntities

class TurretBaseBlockEntity(pos: BlockPos, state: BlockState):
    BlockEntity(MilBlockEntities.TURRET_BASE.get(), pos, state)