package net.justmili.reminders.client.lang;

import net.minecraft.network.chat.Component;

public class TransKeys {
    public static Component
        configTitleKey = newKey("config.wbreminders.title"),
        configUnitMinutesKey = newKey("config.wbreminders.unit.minutes"),
        configUnitHourAmKey = newKey("config.wbreminders.unit.hour.am"),
        configUnitHourPmKey = newKey("config.wbreminders.unit.hour.pm"),
        reminderTitleKey = newKey("toast.wbreminders.reminders.title"),

        hydrateTitle = newKey("toast.wbreminders.hydrate.title"),
        mealTitle = newKey("toast.wbreminders.meal.title"),
        breakTitle = newKey("toast.wbreminders.break.title"),
        sleepTitle = newKey("toast.wbreminders.sleep.title"),
        stretchTitle = newKey("toast.wbreminders.stretch.title"),
        wristExcTitle = newKey("toast.wbreminders.wristexc.title"),

        configHydrateTitle = newKey("config.wbreminders.hydrate.title"),
        configHydrateIntTitle = newKey("config.wbreminders.hydrate.int.title"),
        configHydrateDesc = newKey("config.wbreminders.hydrate.desc"),

        configMealTitle = newKey("config.wbreminders.meal.title"),
        configMealIntTitle = newKey("config.wbreminders.meal.int.title"),
        configMealDesc = newKey("config.wbreminders.meal.desc"),

        configBreakTitle = newKey("config.wbreminders.break.title"),
        configBreakIntTitle = newKey("config.wbreminders.break.int.title"),
        configBreakDesc = newKey("config.wbreminders.break.desc"),

        configSleepTitle = newKey("config.wbreminders.sleep.title"),
        configSleepHourTitle = newKey("config.wbreminders.sleep.hour.title"),
        configSleepMinTitle = newKey("config.wbreminders.sleep.minute.title"),
        configSleepDesc = newKey("config.wbreminders.sleep.desc"),

        configStretchTitle = newKey("config.wbreminders.stretch.title"),
        configStretchIntTitle = newKey("config.wbreminders.stretch.int.title"),
        configStretchDesc = newKey("config.wbreminders.stretch.desc"),

        configWristExcTitle = newKey("config.wbreminders.wristexc.title"),
        configWristExcIntTitle = newKey("config.wbreminders.wristexc.int.title"),
        configWristExcDesc = newKey("config.wbreminders.wristexc.desc");

    private static Component newKey(String key) {
        return Component.translatable(key);
    }
}