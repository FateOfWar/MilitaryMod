package org.valkyrienskies.military

import net.minecraft.core.Registry
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import org.valkyrienskies.military.gui.engine.EngineScreenMenu
import org.valkyrienskies.military.registry.DeferredRegister

private typealias HFactory<T> = (syncId: Int, playerInv: Inventory) -> T

@Suppress("unused")
object EurekaScreens {
    private val SCREENS = DeferredRegister.create(MilitaryMod.MOD_ID, Registry.MENU_REGISTRY)

    val ENGINE = EngineScreenMenu.factory withName "engine"

    fun register() {
        SCREENS.applyAll()
    }

    private infix fun <T : AbstractContainerMenu> HFactory<T>.withName(name: String) =
        SCREENS.register(name) { MenuType(this) }
}
