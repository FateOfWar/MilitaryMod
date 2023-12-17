package org.valkyrienskies.military.forge


import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraftforge.client.ConfigGuiHandler.ConfigGuiFactory
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import org.valkyrienskies.core.impl.config.VSConfigClass.Companion.getRegisteredConfig
import org.valkyrienskies.military.MilBlockEntities
import org.valkyrienskies.military.MilitaryMod.init
import org.valkyrienskies.military.MilitaryMod.initClient
import org.valkyrienskies.military.MilConfig
import org.valkyrienskies.military.MilitaryMod
import org.valkyrienskies.military.blockentity.render.TurretBaseBlockEntityRender
import org.valkyrienskies.mod.compat.clothconfig.VSClothConfig.createConfigScreenFor
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(MilitaryMod.MOD_ID)
class MilitaryModForge {
    private var happendClientSetup = false

    init {
        // Submit our event bus to let architectury register our content on the right time
        MOD_BUS.addListener { event: FMLClientSetupEvent? ->
            clientSetup(
                event
            )
        }
        MOD_BUS.addListener { event: ModelRegistryEvent? ->
            onModelRegistry(
                event
            )
        }
        MOD_BUS.addListener { event: RegisterRenderers ->
            entityRenderers(
                event
            )
        }
        LOADING_CONTEXT.registerExtensionPoint(
            ConfigGuiFactory::class.java
        ) {
            ConfigGuiFactory { _: Minecraft?, parent: Screen? ->
                createConfigScreenFor(
                    parent!!,
                    getRegisteredConfig(MilConfig::class.java)
                )
            }
        }
        init()
    }

    private fun clientSetup(event: FMLClientSetupEvent?) {
        if (happendClientSetup) {
            return
        }
        happendClientSetup = true
        initClient()
    }

    private fun entityRenderers(event: RegisterRenderers) {
        event.registerBlockEntityRenderer(MilBlockEntities.TURRET_BASE.get()) { ctx ->
            TurretBaseBlockEntityRender(
                ctx
            )
        }
    }

    private fun onModelRegistry(event: ModelRegistryEvent?) {

    }

    companion object {
        fun getModBus(): IEventBus =
            MOD_BUS
    }
}