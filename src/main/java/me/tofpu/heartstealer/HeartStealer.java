package me.tofpu.heartstealer;

import me.tofpu.heartstealer.command.CommandHandle;
import me.tofpu.heartstealer.config.CachedConfig;
import me.tofpu.heartstealer.listener.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HeartStealer extends JavaPlugin {
    private CachedConfig cachedConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        this.cachedConfig = new CachedConfig(getConfig());

        getCommand("heartstealer").setExecutor(new CommandHandle(this));

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathListener(cachedConfig), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public CachedConfig getCachedConfig() {
        return cachedConfig;
    }
}
