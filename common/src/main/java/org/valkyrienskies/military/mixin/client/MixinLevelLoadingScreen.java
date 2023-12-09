package org.valkyrienskies.military.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.valkyrienskies.military.MilitaryMod;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.leftPad;

@Mixin(LevelLoadingScreen.class)
public class MixinLevelLoadingScreen extends Screen {

    // will be ignored
    protected MixinLevelLoadingScreen(Component component) {
        super(component);
    }

    @Shadow
    private String getFormattedProgress() {return null;}

    boolean init = false;
    ResourceLocation tex;

    /**
     * @author a
     * @reason a
     */
    @Overwrite
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (!init) {
            init = true;
            tex = new ResourceLocation(MilitaryMod.INSTANCE.getLOADING_SCREEN_IMAGES()[
                    new Random().nextInt(MilitaryMod.INSTANCE.getLOADING_SCREEN_IMAGES().length)
                ]);
        }
        assert minecraft != null;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, tex);
        this.blit(poseStack, 0, 0, 0, 0, width, height);

        String progress = leftPad(this.getFormattedProgress(), 4);
        assert progress != null;
        drawString(poseStack, this.font, progress, 5, height - 10, 16777215);
    }
}
