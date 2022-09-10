package net.uku3lig.betterhurtcam.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.function.DoubleConsumer;

public class TextInputScreen extends Screen {
    private final Screen parent;
    private final DoubleConsumer callback;
    private final double last;

    private TextFieldWidget textField;

    public TextInputScreen(Screen parent, DoubleConsumer callback, double last) {
        super(Text.literal("Text input screen"));
        this.parent = parent;
        this.callback = callback;
        this.last = last;
    }

    @Override
    protected void init() {
        final ButtonWidget doneButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 96 + 12, 200, 20, ScreenTexts.DONE, button -> this.close()));
        textField = this.addDrawableChild(new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 116, 200, 20, Text.literal("Hurtcam Multiplier")));
        textField.setText(String.valueOf(last));
        textField.setChangedListener(s -> doneButton.active = isValid(s));
    }

    private boolean isValid(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void removed() {
        if (isValid(textField.getText())) {
            callback.accept(Double.parseDouble(textField.getText()));
        }
    }

    @Override
    public void close() {
        MinecraftClient.getInstance().setScreen(parent);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        drawTextWithShadow(matrices, this.textRenderer, Text.literal("Hurtcam Multiplier :3"), this.width / 2 - 100, 100, 0xA0A0A0);
        this.textField.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
