package org.valkyrienskies.military.forge.services;

import kotlin.jvm.functions.Function0;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.valkyrienskies.military.services.PlatformHelper;

public class PlatformHelperForge implements PlatformHelper {
    @NotNull
    @Override
    public CreativeModeTab createCreativeTab(
            @NotNull final ResourceLocation id,
            @NotNull final Function0<ItemStack> stack
    ) {
        return new CreativeModeTab(id.toString()) {
            @Override
            public ItemStack makeIcon() {
                return stack.invoke();
            }

            @Override
            public Component getDisplayName() {
                return new TranslatableComponent(
                    "itemGroup." + String.format("%s.%s", id.getNamespace(), id.getPath()));
            }
        };
    }
}
