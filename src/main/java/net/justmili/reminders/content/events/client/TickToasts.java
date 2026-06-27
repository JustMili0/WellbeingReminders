package net.justmili.reminders.content.events.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.justmili.reminders.client.gui.ReminderToast;
import net.justmili.reminders.client.lang.TransKeys;
import net.justmili.reminders.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TickToasts {
    private static int sessionTicks = -1;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (minecraft.player == null) {
                sessionTicks = -1;
                return;
            }

            if (sessionTicks == -1) {
                sessionTicks = 0;
            } else {
                sessionTicks++;
            }
            double playtime = sessionTicks;

            // Trigger reminders
            hydrateReminder(playtime);
            mealReminder(playtime);

            breakReminder(playtime);
            sleepReminder(playtime);

            stretchReminder(playtime);
            wristExcReminder(playtime);

            // Dev env stuff
//            if (sessionTicks % 20 == 0) {
//                RemindersClient.LOGGER.info("SESSION PLAYTIME");
//                RemindersClient.LOGGER.info(playtime + " Ticks | " + playtimeInMinutes(playtime) + " Minutes | " + playtimeInHours(playtime) + " Hours");
//            }
        });
    }

    // Toast
    private static void newToast(Component titleKey, Component messageKey, ItemStack icon) {
        Minecraft.getInstance().getToasts().addToast(
            new ReminderToast(titleKey, messageKey, icon)
        );
    }
    private static void timedToast(double playtime, int intervalMinutes, Component titleKey, Component messageKey, ItemStack icon) {
        int ticks = (int) playtime,
            intervalTicks = intervalMinutes * 1200;

        if (ticks > 0 && ticks % intervalTicks == 0) {
            newToast(titleKey, messageKey, icon);
        }
    }

    // Reminders
    private static void hydrateReminder(double playtime) {
        timedToast(
            playtime, Config.hydrateReminderInterval.get(),
            TransKeys.hydrateTitle,
            TransKeys.hydrateMessage,
            new ItemStack(Items.POTION)
        );
    }
    private static void mealReminder(double playtime) {
        timedToast(
            playtime, Config.mealReminderInterval.get(),
            TransKeys.mealTitle,
            TransKeys.mealMessage,
            new ItemStack(Items.RABBIT_STEW)
        );
    }

    private static void breakReminder(double playtime) {
        timedToast(
            playtime, Config.breakReminderInterval.get(),
            TransKeys.breakTitle,
            TransKeys.breakMessage,
            new ItemStack(Items.RABBIT_STEW)
        );
    }
    private static void sleepReminder(double playtime) {
        // Custom: Play toast at a specific hour

        newToast(
            TransKeys.sleepTitle,
            TransKeys.sleepMessage,
            new ItemStack(Items.RED_BED)
        );
    }

    private static void stretchReminder(double playtime) {
        timedToast(
            playtime, Config.stretchReminderInterval.get(),
            TransKeys.stretchTitle,
            TransKeys.stretchMessage,
            new ItemStack(Items.RABBIT_STEW)
        );
    }
    private static void wristExcReminder(double playtime) {
        timedToast(
            playtime, Config.wristExcReminderInterval.get(),
            TransKeys.wristExcTitle,
            TransKeys.wristExcMessage,
            new ItemStack(Items.RABBIT_STEW)
        );
    }
}