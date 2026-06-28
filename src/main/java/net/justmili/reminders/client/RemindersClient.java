package net.justmili.reminders.client;

import net.justmili.libs.v1.utils.ResourceUtil;
import net.justmili.reminders.client.config.Config;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = RemindersClient.MODID, dist = Dist.CLIENT)
public class RemindersClient {
    public static final String MODID = "wbreminders";
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("Wellbeing Reminders");

    public RemindersClient(IEventBus eventBus, ModContainer container) {
        Config.register();
        LOGGER.info("Loaded Wellbeing Reminders Config");
        //TickReminders.register();
        LOGGER.info("Initialized Wellbeing Reminders");
    }

    public static ResourceLocation asResource(String path) {
        return ResourceUtil.parse(MODID, path);
    }
}
