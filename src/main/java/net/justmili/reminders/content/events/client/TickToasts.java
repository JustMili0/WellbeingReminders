package net.justmili.reminders.content.events.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.justmili.reminders.client.RemindersClient;
import net.justmili.reminders.client.config.Config;
import net.justmili.reminders.client.gui.ReminderToast;
import net.justmili.reminders.client.lang.TransKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class TickToasts {
    private static long sessionTicks = -1;
    private static LocalDate sleepReminderLastFired = null;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                sessionTicks = -1;
                return;
            }

            if (sessionTicks == -1) {
                sessionTicks = 0;
            } else {
                sessionTicks++;
            }

            // Trigger reminders
            hydrateReminder(sessionTicks);
            mealReminder(sessionTicks);

            breakReminder(sessionTicks);
            sleepReminder(); // Based on real time, not session play time

            stretchReminder(sessionTicks);
            wristExcReminder(sessionTicks);

            // Dev env stuff
            if (sessionTicks % 100 == 0 && Config.isDev.get()) { // Log every 5s
                RemindersClient.LOGGER.info("SESSION TIME: " + ticksToHours(sessionTicks) + "h, " + ticksToMinutes(sessionTicks) + "min, " + sessionTicks + "t");
            }
        });
    }

    // Calculate into minutes and hours as doubles for extra time info
    // Or smth like that idfk, I just like seeing numbers go up
    private static double ticksToMinutes(double ticks) {
        double minutes = ticks / 20.0 / 60.0;
        return BigDecimal.valueOf(minutes).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private static double ticksToHours(double ticks) {
        double hours = ticks / 20.0 / 60.0 / 60.0;
        return BigDecimal.valueOf(hours).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    // Toast
    private static void newToast(Component reminderKey, ItemStack icon) {
        Minecraft client = Minecraft.getInstance();

        client.gui.toastManager().addToast(
            new ReminderToast(TransKeys.reminderTitleKey, reminderKey, icon)
        );
        if (client.player != null) {
            client.player.playSound(SoundEvents.NOTE_BLOCK_CHIME.value(), 2f, 2f);
        }
    }
    private static void timedToast(long playtime, int intervalMinutes, Component reminderKey, ItemStack icon) {
        int intervalTicks = intervalMinutes * 1200;

        if (playtime > 0 && playtime % intervalTicks == 0) {
            newToast(reminderKey, icon);
        }
    }

    // Reminders
    private static void hydrateReminder(long playtime) {
        if (!Config.enableHydrateReminder.get()) return;

        timedToast(
            playtime, Config.hydrateReminderInterval.get(),
            TransKeys.hydrateTitle,
            new ItemStack(Items.POTION)
        );
    }
    private static void mealReminder(long playtime) {
        if (!Config.enableMealReminder.get()) return;

        timedToast(
            playtime, Config.mealReminderInterval.get(),
            TransKeys.mealTitle,
            new ItemStack(Items.RABBIT_STEW)
        );
    }

    private static void breakReminder(long playtime) {
        if (!Config.enableBreakReminder.get()) return;

        timedToast(
            playtime, Config.breakReminderInterval.get(),
            TransKeys.breakTitle,
            new ItemStack(Items.CLOCK)
        );
    }
    private static void sleepReminder() {
        if (!Config.enableSleepReminder.get()) return;

        LocalTime now = LocalTime.now();
        LocalTime target = LocalTime.of(Config.sleepReminderHour.get(), Config.sleepReminderMinute.get());
        LocalDate today = LocalDate.now();

        boolean alreadyFiredToday = today.equals(sleepReminderLastFired),
            pastTargetTime = !now.isBefore(target);

        if (pastTargetTime && !alreadyFiredToday) {
            sleepReminderLastFired = today;
            newToast(
                TransKeys.sleepTitle,
                new ItemStack(Items.BED.red())
            );
        }
    }

    private static void stretchReminder(long playtime) {
        if (!Config.enableStretchReminder.get()) return;

        timedToast(
            playtime, Config.stretchReminderInterval.get(),
            TransKeys.stretchTitle,
            new ItemStack(Items.FEATHER)
        );
    }
    private static void wristExcReminder(long playtime) {
        if (!Config.enableWristExcReminder.get()) return;

        timedToast(
            playtime, Config.wristExcReminderInterval.get(),
            TransKeys.wristExcTitle,
            new ItemStack(Items.WOODEN_SWORD)
        );
    }
}