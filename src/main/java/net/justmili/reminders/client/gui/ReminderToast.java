package net.justmili.reminders.client.gui;

import net.justmili.reminders.client.RemindersClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ReminderToast implements Toast {
    private static final ResourceLocation SYSTEM = RemindersClient.asMinecraft("toast/system");

    private final Component title;
    private final Component message;
    private final ItemStack icon;

    public ReminderToast(Component title, Component message, ItemStack icon) {
        this.title = title;
        this.message = message;
        this.icon = icon;
    }

    @Override
    public Visibility render(GuiGraphics guiGraphics, ToastComponent component, long visibleTime) {
        guiGraphics.blitSprite(SYSTEM, 0, 0, width(), height());

        guiGraphics.renderItem(icon, 8, 8, 0);

        guiGraphics.drawString(component.getMinecraft().font, title, 30, 7, 0xFFFFFF, false);
        guiGraphics.drawString(component.getMinecraft().font, message, 30, 18, 0xAAAAAA, false);

        return visibleTime >= 5000? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public int width() {
        return 180;
    }

    @Override
    public int height() {
        return 32;
    }
}