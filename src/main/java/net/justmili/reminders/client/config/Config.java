package net.justmili.reminders.client.config;

import net.justmili.libs.v1.config.MConfigBuilder;
import net.justmili.libs.v1.config.entry.ConfigEntry;
import net.justmili.reminders.client.RemindersClient;

public class Config {
    public static MConfigBuilder builder = new MConfigBuilder(RemindersClient.MODID, null, false);

    public static ConfigEntry<Boolean>
        isDev,
        enableHydrateReminder,
        enableMealReminder,
        enableBreakReminder,
        enableSleepReminder,
        enableStretchReminder,
        enableWristExcReminder;

    public static ConfigEntry<Integer>
        hydrateReminderInterval,
        mealReminderInterval,
        breakReminderInterval,
        sleepReminderHour,
        sleepReminderMinute,
        stretchReminderInterval,
        wristExcReminderInterval;

    public static void register() {
        isDev = builder.comment("Enable/Disable Developer Logs").define("isDev", false);

        enableHydrateReminder = builder.comment("Should the mod remind you to hydrate?")
            .define("enableHydrateReminder", true);
        hydrateReminderInterval = builder.comment("How often should the reminder pop up? (In minutes)")
            .define("hydrateReminderInterval", 90, 5, 1440);

        enableMealReminder = builder.comment("Should the mod remind you to eat?")
            .define("enableMealReminder", false);
        mealReminderInterval = builder.comment("How often should the reminder pop up? (In minutes)")
            .define("mealReminderInterval", 180, 30, 1440);

        enableBreakReminder = builder.comment("Should the mod remind you to take a break?")
            .define("enableBreakReminder", true);
        breakReminderInterval = builder.comment("How often should the reminder pop up? (In minutes)")
            .define("breakReminderInterval", 60, 5, 1440);

        enableSleepReminder = builder.comment("Should the mod remind you to go to sleep?")
            .define("enableSleepReminder", false);
        sleepReminderHour = builder.comment("At what hour (24-hour format) should the reminder pop up?")
            .define("sleepReminderHour", 22, 0, 23);
        sleepReminderMinute = builder.comment("At what minute of the hour should the reminder pop up?")
            .define("sleepReminderMinute", 0, 0, 59);

        enableStretchReminder = builder.comment("Should the mod remind you to stretch?")
            .define("enableStretchReminder", false);
        stretchReminderInterval = builder.comment("How often should the reminder pop up? (In minutes)")
            .define("stretchReminderInterval", 60, 5, 1440);

        enableWristExcReminder = builder.comment("Should the mod remind you to do wrist exercises?")
            .define("enableWristExcReminder", false);
        wristExcReminderInterval = builder.comment("How often should the reminder pop up? (In minutes)")
            .define("wristExcReminderInterval", 60, 5, 1440);

        builder.build();
    }
}