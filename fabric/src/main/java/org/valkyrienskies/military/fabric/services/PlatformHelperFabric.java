package org.valkyrienskies.military.fabric.services;

import kotlin.jvm.functions.Function0;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.valkyrienskies.military.services.PlatformHelper;

public class PlatformHelperFabric implements PlatformHelper {
    @NotNull
    @Override
    public CreativeModeTab createCreativeTab(@NotNull ResourceLocation id, @NotNull Function0<ItemStack> stack) {
        return FabricItemGroupBuilder.build(id, stack::invoke);
    }
}
