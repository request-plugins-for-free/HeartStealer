package me.tofpu.heartstealer.config;

import org.bukkit.configuration.file.FileConfiguration;

public class CachedConfig {
    private final String path = "message.";

    private int banLength;

    private String banMessage;
    private String killerMessage;
    private String victimMessage;

    public CachedConfig(final FileConfiguration configuration){
        reload(configuration);
    }

    public void reload(final FileConfiguration configuration){
        this.banLength = configuration.getInt("settings.ban-length");

        this.banMessage = configuration.getString(path + "ban-message");
        this.killerMessage = configuration.getString(path + "killer-message");
        this.victimMessage = configuration.getString(path + "victim-message");
    }

    public int getBanLength() {
        return banLength;
    }

    public String getBanMessage() {
        return banMessage;
    }

    public String getKillerMessage() {
        return killerMessage;
    }

    public String getVictimMessage() {
        return victimMessage;
    }
}
