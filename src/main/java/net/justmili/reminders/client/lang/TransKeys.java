package net.justmili.reminders.client.lang;

import net.minecraft.network.chat.Component;

public class TransKeys {
    public static Component
        configTitleKey = newKey("config.wbreminders.title"),
        configUnitMinutesKey = newKey("config.wbreminders.unit.minutes"),
        configUnitHourAmKey = newKey("config.wbreminders.unit.hour.am"),
        configUnitHourPmKey = newKey("config.wbreminders.unit.hour.pm"),
        hydrateTitle = newKey("toast.wbreminders.hydrate.title"),
        hydrateMessage = newKey("toast.wbreminders.hydrate.message"),
        configHydrateTitle = newKey("config.wbreminders.hydrate.title"),
        configHydrateIntTitle = newKey("config.wbreminders.hydrate.int.title"),
        configHydrateDesc = newKey("config.wbreminders.hydrate.desc"),
        mealTitle = newKey("toast.wbreminders.meal.title"),
        mealMessage = newKey("toast.wbreminders.meal.message"),
        configMealTitle = newKey("config.wbreminders.meal.title"),
        configMealIntTitle = newKey("config.wbreminders.meal.int.title"),
        configMealDesc = newKey("config.wbreminders.meal.desc"),
        breakTitle = newKey("toast.wbreminders.break.title"),
        breakMessage = newKey("toast.wbreminders.break.message"),
        configBreakTitle = newKey("config.wbreminders.break.title"),
        configBreakIntTitle = newKey("config.wbreminders.break.int.title"),
        configBreakDesc = newKey("config.wbreminders.break.desc"),
        sleepTitle = newKey("toast.wbreminders.sleep.title"),
        sleepMessage = newKey("toast.wbreminders.sleep.message"),
        configSleepTitle = newKey("config.wbreminders.sleep.title"),
        configSleepDesc = newKey("config.wbreminders.sleep.desc"),
        stretchTitle = newKey("toast.wbreminders.stretch.title"),
        stretchMessage = newKey("toast.wbreminders.stretch.message"),
        configStretchTitle = newKey("config.wbreminders.stretch.title"),
        configStretchIntTitle = newKey("config.wbreminders.stretch.int.title"),
        configStretchDesc = newKey("config.wbreminders.stretch.desc"),
        wristExcTitle = newKey("toast.wbreminders.wristexc.title"),
        wristExcMessage = newKey("toast.wbreminders.wristexc.message"),
        configWristExcTitle = newKey("config.wbreminders.wristexc.title"),
        configWristExcIntTitle = newKey("config.wbreminders.wristexc.int.title"),
        configWristExcDesc = newKey("config.wbreminders.wristexc.desc");

    private static Component newKey(String key) {
        return Component.translatable(key);
    }
}