package net.justmili.reminders.client;

import net.fabricmc.api.ClientModInitializer;
import net.justmili.reminders.client.config.Config;
import net.justmili.reminders.content.events.client.TickToasts;
import net.minecraft.resources.ResourceLocation;

public class RemindersClient implements ClientModInitializer {
    public static final String MODID = "wbreminders";
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("Wellbeing Reminders");

    @Override
    public void onInitializeClient() {
        Config.register();
        LOGGER.info("Loaded Wellbeing Reminders Config");
        TickToasts.register();
        LOGGER.info("Initialized Wellbeing Reminders");
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
    public static ResourceLocation asPath(String path) {
        return ResourceLocation.parse(path);
    }
    public static ResourceLocation asMinecraft(String path) {
        return ResourceLocation.withDefaultNamespace(path);
    }
}
