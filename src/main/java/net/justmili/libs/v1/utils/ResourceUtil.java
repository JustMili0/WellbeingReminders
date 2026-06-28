package net.justmili.libs.v1.utils;

import net.minecraft.resources.ResourceLocation;

public class ResourceUtil {
    public static ResourceLocation parse(String modId, String path) {
        return ResourceLocation.fromNamespaceAndPath(modId, path);
    }
    public static ResourceLocation asMinecraft(String path) {
        return parse("minecraft", path);
    }
}
