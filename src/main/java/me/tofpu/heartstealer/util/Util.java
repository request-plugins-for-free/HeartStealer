package me.tofpu.heartstealer.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class Util {
    public static String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void message(final Player player, final String message) {
        message(player, message, null);
    }

    public static void message(final Player player, final String message, final Map<String, ?> replaceMap) {
        player.sendMessage(colorize(Util.WordReplacer.replace(message, replaceMap)));
    }

    public static class WordReplacer {
        public static String replace(String message, final Map<String, ?> replaceMap) {
            if (replaceMap == null) return colorize(message);
            for (final Map.Entry<String, ?> replace : replaceMap.entrySet()) {
                message = message.replace(replace.getKey(), replace.getValue() + "");
            }
            return colorize(message);
        }
    }
}
