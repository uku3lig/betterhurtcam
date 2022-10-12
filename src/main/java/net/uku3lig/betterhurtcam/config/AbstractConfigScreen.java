package net.uku3lig.betterhurtcam.config;

import lombok.extern.log4j.Log4j2;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.gui.widgets.OptionButton;
import net.minecraft.client.gui.widgets.Slider;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.client.util.ScreenScaler;
import net.uku3lig.betterhurtcam.BetterHurtCam;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
public abstract class AbstractConfigScreen extends Screen {
    protected final Config config;
    private final Screen parent;
    protected final String title;

    protected AbstractConfigScreen(Screen parent, String title, Config config) {
        super();
        this.parent = parent;
        this.config = config;
        this.title = title;
    }

    protected abstract ConfigOption[] getOptions();

    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        TranslationStorage storage = TranslationStorage.getInstance();
        int counter = 0;

        for (ConfigOption option : getOptions()) {
            if (option.isSlider()) {
                this.buttons.add(new Slider(option.getId(), this.width / 2 - 155 + counter % 2 * 160, this.height / 6 + 24 * (counter >> 1), null, storage.translate(option.getTranslationKey()), option.getGetter().get()));
            } else {
                this.buttons.add(new OptionButton(option.getId(), this.width / 2 - 155 + counter % 2 * 160, this.height / 6 + 24 * (counter >> 1), null, storage.translate(option.getTranslationKey())));
            }

            ++counter;
        }

        this.buttons.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168, storage.translate("gui.done")));
    }

    @Override
    protected void buttonClicked(Button arg) {
        if (arg.active) {
            if (arg.id < 100 && arg instanceof OptionButton) {
                Optional<ConfigOption> option = getOption(arg.id);
                if (option.isPresent()) {
                    option.get().getSetter().accept(1);
                    arg.text = getTranslatedValue(option.get());
                }
            }

            if (arg.id == 200) {
                this.minecraft.options.saveOptions();
                this.minecraft.openScreen(this.parent);
            }

            ScreenScaler var2 = new ScreenScaler(this.minecraft.options, this.minecraft.actualWidth, this.minecraft.actualHeight);
            int var3 = var2.getScaledWidth();
            int var4 = var2.getScaledHeight();
            this.init(this.minecraft, var3, var4);
        }
    }

    private String getTranslatedValue(ConfigOption option) {
        String text = TranslationStorage.getInstance().translate(option.getTranslationKey()) + ": ";
        switch (option.getType()) {
            case BOOLEAN:
                text += option.getGetter().get() == 1 ? "ON" : "OFF";
                break;
            case INTEGER:
                text += option.getGetter().get().intValue();
                break;
            case DOUBLE:
                text += option.getGetter().get();
                break;
        }
        return text;
    }

    private Optional<ConfigOption> getOption(int id) {
        return Arrays.stream(getOptions()).filter(option -> option.getId() == id).findFirst();
    }

    @Override
    public void render(int i, int j, float f) {
        this.renderBackground();
        this.drawTextWithShadowCentred(this.textManager, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(i, j, f);
    }

    @Override
    public void onClose() {
        super.onClose();
        try {
            config.writeConfig(BetterHurtCam.getFile());
        } catch (IOException e) {
            log.warn("Could not write configuration file", e);
        }
    }
}
