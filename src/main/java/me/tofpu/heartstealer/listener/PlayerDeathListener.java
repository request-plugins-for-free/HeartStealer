package me.tofpu.heartstealer.listener;

import com.google.common.collect.Maps;
import me.tofpu.heartstealer.config.CachedConfig;
import me.tofpu.heartstealer.util.Util;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PlayerDeathListener implements Listener {
    private final CachedConfig cachedConfig;

    public PlayerDeathListener(final CachedConfig cachedConfig) {
        this.cachedConfig = cachedConfig;
    }

    @EventHandler
    private void onPlayerDeath(final PlayerDeathEvent event){
        final Player victim = event.getEntity();
        final Player killer = victim.getKiller();
        if (killer == null) return;

        final AttributeInstance victimAttribute = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        final AttributeInstance killerAttribute = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        final Map<String, String> replaceMap = Maps.newHashMap();
        replaceMap.put("%player%", killer.getName());
        if (victimAttribute.getValue() <= 2) {
            final Date date = Date.from(Instant.now().plus(Duration.ofSeconds(cachedConfig.getBanLength())));
            final String message = Util.WordReplacer.replace(cachedConfig.getBanMessage(), replaceMap);
            Bukkit
                    .getBanList(BanList.Type.NAME)
                    .addBan(
                            victim.getName(),
                            message,
                            date,
                            "Console");
            victimAttribute.setBaseValue(victimAttribute.getDefaultValue());
            victim.kickPlayer(message);
        } else {
            victimAttribute.setBaseValue(victimAttribute.getValue() - 2);
            Util.message(victim, cachedConfig.getVictimMessage(), replaceMap);
        }
        killerAttribute.setBaseValue(killerAttribute.getValue() + 2);

        replaceMap.put("%player%", victim.getName());
        Util.message(killer, cachedConfig.getKillerMessage(), replaceMap);
    }
}
