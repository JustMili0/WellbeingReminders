package net.justmili.reminders.client.config;

import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.option.OptionBinding;
import net.caffeinemc.mods.sodium.api.config.structure.BooleanOptionBuilder;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.caffeinemc.mods.sodium.api.config.structure.IntegerOptionBuilder;
import net.justmili.libs.v1.config.entry.ConfigEntry;
import net.justmili.reminders.client.RemindersClient;
import net.justmili.reminders.client.lang.TransKeys;
import net.minecraft.network.chat.Component;

public class SodiumApiOptions implements ConfigEntryPoint {

    @Override
    public void registerConfigLate(ConfigBuilder builder) {
        builder.registerOwnModOptions()
            .setNonTintedIcon(RemindersClient.asResource("../../icon.png")) // I'm kinda surprised this worked lmao
            .addPage(builder.createOptionPage()
                .setName(TransKeys.configTitleKey)

                // Hydrate
                .addOption(newBooleanOption(builder, Config.enableHydrateReminder,
                    TransKeys.configHydrateTitle, TransKeys.configHydrateDesc))
                .addOption(newIntegerOption(builder, Config.hydrateReminderInterval, 5,
                    TransKeys.configHydrateIntTitle, TransKeys.configHydrateDesc))

                // Meal
                .addOption(newBooleanOption(builder, Config.enableMealReminder,
                    TransKeys.configMealTitle, TransKeys.configMealDesc))
                .addOption(newIntegerOption(builder, Config.mealReminderInterval, 5,
                    TransKeys.configMealIntTitle, TransKeys.configMealDesc))

                // Break
                .addOption(newBooleanOption(builder, Config.enableBreakReminder,
                    TransKeys.configBreakTitle, TransKeys.configBreakDesc))
                .addOption(newIntegerOption(builder, Config.breakReminderInterval, 5,
                    TransKeys.configBreakIntTitle, TransKeys.configBreakDesc))

                // Sleep
                .addOption(newBooleanOption(builder, Config.enableSleepReminder,
                    TransKeys.configSleepTitle, TransKeys.configSleepDesc))
                .addOption(builder.createIntegerOption(RemindersClient.asResource(Config.sleepReminderHour.key().toLowerCase()))
                    .setName(TransKeys.configSleepHourTitle)
                    .setTooltip(TransKeys.configSleepDesc)
                    .setDefaultValue(Config.sleepReminderHour.defaultValue())
                    .setRange(Config.sleepReminderHour.min(), Config.sleepReminderHour.max(), 1)
                    .setValueFormatter(hour -> {
                        int displayHour = hour % 12 == 0 ? 12 : hour % 12;
                        int minute = Config.sleepReminderMinute.get();
                        Component suffix = hour < 12 ? TransKeys.configUnitHourAmKey : TransKeys.configUnitHourPmKey;
                        return Component.literal(String.format("%d:%02d ", displayHour, minute)).append(suffix);
                    })
                    .setBinding(new OptionBindingImpl<>(Config.sleepReminderHour))
                    .setStorageHandler(Config.builder.getConfig()::save))
                .addOption(builder.createIntegerOption(RemindersClient.asResource(Config.sleepReminderMinute.key().toLowerCase()))
                    .setName(TransKeys.configSleepMinTitle)
                    .setTooltip(TransKeys.configSleepDesc)
                    .setDefaultValue(Config.sleepReminderMinute.defaultValue())
                    .setRange(Config.sleepReminderMinute.min(), Config.sleepReminderMinute.max(), 1)
                    .setValueFormatter(minute -> {
                        int hour = Config.sleepReminderHour.get();
                        int displayHour = hour % 12 == 0 ? 12 : hour % 12;
                        Component suffix = hour < 12 ? TransKeys.configUnitHourAmKey : TransKeys.configUnitHourPmKey;
                        return Component.literal(String.format("%d:%02d ", displayHour, minute)).append(suffix);
                    })
                    .setBinding(new OptionBindingImpl<>(Config.sleepReminderMinute))
                    .setStorageHandler(Config.builder.getConfig()::save))

                // Stretching out
                .addOption(newBooleanOption(builder, Config.enableStretchReminder,
                    TransKeys.configStretchTitle, TransKeys.configStretchDesc))
                .addOption(newIntegerOption(builder, Config.stretchReminderInterval, 5,
                    TransKeys.configStretchIntTitle, TransKeys.configStretchDesc))

                // Wrist exercises
                .addOption(newBooleanOption(builder, Config.enableWristExcReminder,
                    TransKeys.configWristExcTitle, TransKeys.configWristExcDesc))
                .addOption(newIntegerOption(builder, Config.wristExcReminderInterval, 5,
                    TransKeys.configWristExcIntTitle, TransKeys.configWristExcDesc))
            );
    }

    private static BooleanOptionBuilder newBooleanOption(ConfigBuilder builder, ConfigEntry<Boolean> entry,
                                                         Component name, Component tooltip) {
        return builder.createBooleanOption(RemindersClient.asResource(entry.key().toLowerCase()))
            .setName(name)
            .setTooltip(tooltip)
            .setDefaultValue(entry.defaultValue())
            .setBinding(new OptionBindingImpl<>(entry))
            .setStorageHandler(Config.builder.getConfig()::save);
    }

    private static IntegerOptionBuilder newIntegerOption(ConfigBuilder builder, ConfigEntry<Integer> entry, int step,
                                                         Component name, Component tooltip) {
        return builder.createIntegerOption(RemindersClient.asResource(entry.key().toLowerCase()))
            .setName(name)
            .setTooltip(tooltip)
            .setDefaultValue(entry.defaultValue())
            .setRange(entry.min(), entry.max(), step)
            .setValueFormatter(value -> Component.translatable(TransKeys.configUnitMinutesKey.getString(), value))
            .setBinding(new OptionBindingImpl<>(entry))
            .setStorageHandler(Config.builder.getConfig()::save);
    }

    private record OptionBindingImpl<T>(ConfigEntry<T> entry) implements OptionBinding<T> {

        @Override
        public void save(T value) {
            this.entry.set(value);
        }

        @Override
        public T load() {
            return this.entry.get();
        }
    }
}