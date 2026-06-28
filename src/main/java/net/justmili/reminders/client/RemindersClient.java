package net.justmili.reminders.client;

import net.fabricmc.api.ClientModInitializer;
import net.justmili.libs.v1.utils.ResourceUtil;
import net.justmili.reminders.client.config.Config;
import net.justmili.reminders.content.events.client.TickReminders;
import net.minecraft.resources.ResourceLocation;

public class RemindersClient implements ClientModInitializer {
    public static final String MODID = "wbreminders";
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("Wellbeing Reminders");

    @Override
    public void onInitializeClient() {
        Config.register();
        LOGGER.info("Loaded Wellbeing Reminders Config");
        TickReminders.register();
        LOGGER.info("Initialized Wellbeing Reminders");
    }

    public static ResourceLocation asResource(String path) {
        return ResourceUtil.parse(MODID, path);
    }
}
