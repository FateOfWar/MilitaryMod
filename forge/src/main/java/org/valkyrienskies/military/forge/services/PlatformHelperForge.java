package org.valkyrienskies.military.forge.services;

import com.mojang.blaze3d.vertex.PoseStack;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.StandaloneModelConfiguration;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.renderable.MultipartTransforms;
import net.minecraftforge.client.model.renderable.SimpleRenderable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.valkyrienskies.military.MilLogger;
import org.valkyrienskies.military.services.PlatformHelper;

import java.util.Map;

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

    @Override
    @NotNull
    public Function0<@Nullable Function6<PoseStack, MultiBufferSource, Function1<? super ResourceLocation, ? extends RenderType>, Integer, Integer, Float, Unit>>
    loadOBJModel(
            @NotNull ResourceLocation loc,
            boolean detectCullableFaces,
            boolean diffuseLighting,
            boolean flipV,
            boolean ambientToFullbright,
            String materialLibraryOverrideLocation,
            @NotNull Map<String, ? extends ResourceLocation> textures
    ) {
        return () -> {
            OBJModel.ModelSettings settings = new OBJModel.ModelSettings(
                    loc,
                    detectCullableFaces,
                    diffuseLighting,
                    flipV,
                    ambientToFullbright,
                    materialLibraryOverrideLocation
            );
            try {
                OBJModel model = OBJLoader.INSTANCE.loadModel(settings);
                if (model == null) {
                    return null;
                }
                IModelConfiguration config = StandaloneModelConfiguration.create(loc, (Map<String, ResourceLocation>) textures);
                SimpleRenderable renderable = model.bakeRenderable(config);
                return (poseStack, multiBufferSource, renderTypeGetter, light, overlay, partial) -> {
                    renderable.render(poseStack, multiBufferSource, (res) -> RenderType.solid(), light, overlay, partial, MultipartTransforms.EMPTY);
                    return Unit.INSTANCE;
                };
            } catch (Exception e) {
                MilLogger.INSTANCE.exception(e);
                return null;
            }
        };
    }
}
