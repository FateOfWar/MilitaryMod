package org.valkyrienskies.military.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import org.valkyrienskies.core.impl.config.VSConfigClass;
import org.valkyrienskies.military.MilBlockEntities;
import org.valkyrienskies.military.MilConfig;
import org.valkyrienskies.military.MilitaryMod;
import org.valkyrienskies.military.blockentity.render.TurretBaseBlockEntityRender;
import org.valkyrienskies.mod.compat.clothconfig.VSClothConfig;
import org.valkyrienskies.mod.fabric.common.ValkyrienSkiesModFabric;

public class MilitaryModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // force VS2 to load before eureka
        new ValkyrienSkiesModFabric().onInitialize();

        MilitaryMod.init();
    }

    @Environment(EnvType.CLIENT)
    public static class Client implements ClientModInitializer {

        @Override
        public void onInitializeClient() {
            MilitaryMod.initClient();

            BlockEntityRendererRegistry.register(
                    MilBlockEntities.INSTANCE.getTURRET_BASE().get(),
                    TurretBaseBlockEntityRender::new
            );
        }
    }

    public static class ModMenu implements ModMenuApi {
        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return (parent) -> VSClothConfig.createConfigScreenFor(
                    parent,
                    VSConfigClass.Companion.getRegisteredConfig(MilConfig.class)
            );
        }
    }
}
