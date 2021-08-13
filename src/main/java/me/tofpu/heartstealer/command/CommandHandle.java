package me.tofpu.heartstealer.command;

import me.tofpu.heartstealer.HeartStealer;
import me.tofpu.heartstealer.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandle implements CommandExecutor {
    private final HeartStealer heartStealer;

    public CommandHandle(final HeartStealer heartStealer) {
        this.heartStealer = heartStealer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        heartStealer.getCachedConfig().reload(heartStealer.getConfig());
        sender.sendMessage(Util.colorize("&7You have successfully applied the new changes!"));
        return false;
    }
}
