package org.valkyrienskies.military.forge.services;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;
import org.valkyrienskies.military.forge.DeferredRegisterImpl;
import org.valkyrienskies.military.registry.DeferredRegister;
import org.valkyrienskies.military.services.DeferredRegisterBackend;

public class DeferredRegisterBackendForge implements DeferredRegisterBackend {

    @NotNull
    @Override
    public <T> DeferredRegister<T> makeDeferredRegister(
            @NotNull final String id,
            @NotNull final ResourceKey<Registry<T>> registry
    ) {
        return new DeferredRegisterImpl(id, registry);
    }
}
