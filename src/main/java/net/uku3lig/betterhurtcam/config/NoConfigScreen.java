package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class NoConfigScreen extends Screen {
    private final Screen parent;

    public NoConfigScreen(Screen parent) {
        super(Text.of("No config? :nobitches"));
        this.parent = parent;
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(parent);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredTextWithShadow(matrices, textRenderer, Text.of("No config screen was found :(").asOrderedText(), width / 2, 100, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
