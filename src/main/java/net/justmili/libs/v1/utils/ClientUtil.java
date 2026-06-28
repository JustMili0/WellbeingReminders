package net.justmili.libs.v1.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class ClientUtil {
    public static Minecraft minecraft = Minecraft.getInstance();

    public static Player getPlayer() {
        return minecraft.player;
    }

    public static void playSound(SoundEvent sound, float volume, float pitch) {
        if (getPlayer() == null) return;
        getPlayer().playSound(sound, volume, pitch);
    }
}
