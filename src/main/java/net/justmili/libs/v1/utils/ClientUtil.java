package net.justmili.libs.v1.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

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