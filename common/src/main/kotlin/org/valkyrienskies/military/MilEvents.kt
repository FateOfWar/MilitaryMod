package org.valkyrienskies.military

import net.minecraft.client.Minecraft
import org.valkyrienskies.core.impl.util.events.EventEmitterImpl

object MilEvents {

    val postLoad = EventEmitterImpl<Minecraft>()

}