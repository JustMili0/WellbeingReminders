package net.justmili.reminders.client.gui;

import net.justmili.libs.v1.utils.ResourceUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public class ReminderToast implements Toast {
    private static final Identifier TOAST = ResourceUtil.asMinecraft("toast/advancement");

    private final Component title;
    private final Component message;
    private final ItemStack icon;
    private Visibility wantedVisibility = Visibility.SHOW;

    public ReminderToast(Component title, Component message, ItemStack icon) {
        this.title = title;
        this.message = message;
        this.icon = icon;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, Font font, long fullyVisibleForMs) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, TOAST, 0, 0, width(), height());

        graphics.item(icon, 8, 8, 0);

        graphics.text(font, title, 30, 7, 0xFFFFFF00, false);
        graphics.text(font, message, 30, 18, 0xFFFFFFFF, false);
    }

    @Override
    public void update(ToastManager toastManager, long visibleTime) {
        wantedVisibility = visibleTime >= 5000 * toastManager.getNotificationDisplayTimeMultiplier()? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public Visibility getWantedVisibility() {
        return wantedVisibility;
    }

    @Override
    public int width() {
        return 170;
    }

    @Override
    public int height() {
        return 32;
    }
}