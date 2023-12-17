package org.valkyrienskies.military.fabric.services;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.fabricators_of_create.porting_lib.model.IModelConfiguration;
import io.github.fabricators_of_create.porting_lib.model.MultipartTransforms;
import io.github.fabricators_of_create.porting_lib.model.SimpleRenderable;
import io.github.fabricators_of_create.porting_lib.model.obj.OBJLoader;
import io.github.fabricators_of_create.porting_lib.model.obj.OBJModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.valkyrienskies.military.MilLogger;
import org.valkyrienskies.military.fabric.StandaloneModelConfiguration;
import org.valkyrienskies.military.services.PlatformHelper;

import java.util.Map;

public class PlatformHelperFabric implements PlatformHelper {
    @NotNull
    @Override
    public CreativeModeTab createCreativeTab(@NotNull ResourceLocation id, @NotNull Function0<ItemStack> stack) {
        return FabricItemGroupBuilder.build(id, stack::invoke);
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
