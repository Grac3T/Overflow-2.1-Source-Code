// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.commands;

import us.overflow.events.OverflowListener;
import java.util.Iterator;
import us.overflow.base.user.global.GlobalObject;
import us.overflow.base.user.User;
import org.bukkit.OfflinePlayer;
import us.overflow.utils.file.ConfigFile;
import us.overflow.utils.http.HTTPUtil;
import java.util.function.BiConsumer;
import java.util.HashMap;
import java.io.IOException;
import org.eclipse.egit.github.core.service.GistService;
import java.util.Collections;
import us.overflow.utils.other.TimeUtils;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;
import java.sql.SQLException;
import us.overflow.utils.config.ConfigManager;
import us.overflow.theme.ThemeGUI;
import us.overflow.base.Check;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import us.overflow.Overflow;
import org.bukkit.command.CommandSender;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.command.defaults.BukkitCommand;

public class OverflowCommand extends BukkitCommand
{
    public OverflowCommand(final String name) {
        super(name);
        this.description = "Anticheat command.";
        this.usageMessage = "/" + name;
        this.setAliases((List)new ArrayList());
    }
    
    public boolean execute(final CommandSender commandSender, final String commandLabel, final String[] args) {
        Label_0301: {
            if (commandLabel.equalsIgnoreCase("client")) {
                if (!commandSender.isOp()) {
                    if (!commandSender.hasPermission(Overflow.getInstance().getPermissionUtil().getAlertsPermission())) {
                        if (Overflow.getInstance().getConfigValues().isHider()) {
                            commandSender.sendMessage(Overflow.getInstance().getConfigValues().getNoPermissionMessage());
                            break Label_0301;
                        }
                        commandSender.sendMessage(ChatColor.RED + "No Permission.");
                        break Label_0301;
                    }
                }
                try {
                    final OfflinePlayer target = (OfflinePlayer)Bukkit.getPlayer(args[0]);
                    if (target != null && target.isOnline()) {
                        final User targetUser = Overflow.getInstance().getUserManager().getUser(target.getUniqueId());
                        if (targetUser != null) {
                            commandSender.sendMessage(ChatColor.GREEN + targetUser.getPlayer().getName() + "'s " + ChatColor.GRAY + "client brand is: " + ChatColor.GREEN + ((targetUser.getClientBrand() != null) ? targetUser.getClientBrand() : (ChatColor.RED + "(Not Detected, User needs to relog!)")));
                        }
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + "Player is not online.");
                    }
                }
                catch (Exception ignored) {
                    commandSender.sendMessage(ChatColor.RED + "Please supply a player.");
                }
            }
        }
        if (commandLabel.equalsIgnoreCase("alerts")) {
            if (commandSender.isOp() || commandSender.hasPermission(Overflow.getInstance().getPermissionUtil().getAlertsPermission())) {
                final Player player = (Player)commandSender;
                final GlobalObject globalObject = Overflow.getInstance().getGlobalUserManager().getUser(player.getUniqueId().toString());
                if (globalObject != null) {
                    if (globalObject.isHasAlerts()) {
                        globalObject.setHasAlerts(false);
                        commandSender.sendMessage(ChatColor.RED + "Alerts have been toggled off.");
                    }
                    else {
                        globalObject.setHasAlerts(true);
                        commandSender.sendMessage(ChatColor.GREEN + "Alerts have been toggled on.");
                    }
                }
            }
            else if (Overflow.getInstance().getConfigValues().isHider()) {
                commandSender.sendMessage(Overflow.getInstance().getConfigValues().getNoPermissionMessage());
            }
            else {
                commandSender.sendMessage(ChatColor.RED + "No Permission.");
            }
        }
        Label_0805: {
            if (commandLabel.equalsIgnoreCase("forceban") || commandLabel.equalsIgnoreCase("fb")) {
                if (!commandSender.isOp()) {
                    if (!commandSender.hasPermission(Overflow.getInstance().getPermissionUtil().getAlertsPermission())) {
                        if (Overflow.getInstance().getConfigValues().isHider()) {
                            commandSender.sendMessage(Overflow.getInstance().getConfigValues().getNoPermissionMessage());
                            break Label_0805;
                        }
                        commandSender.sendMessage(ChatColor.RED + "No Permission.");
                        break Label_0805;
                    }
                }
                try {
                    if (args[0] != null && args[0].length() > 0) {
                        final OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        if (target != null) {
                            if (target.hasPlayedBefore()) {
                                commandSender.sendMessage(ChatColor.GREEN + "Force ban executing for " + ChatColor.RED + target.getName() + ChatColor.GREEN + "...");
                                Check.ban(target.getName());
                            }
                            else {
                                commandSender.sendMessage(ChatColor.RED + "This player has never played before!");
                            }
                        }
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                    }
                }
                catch (Exception ignored) {
                    commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                }
            }
        }
        if (commandLabel.equalsIgnoreCase(Overflow.getInstance().getConfigValues().isHider() ? Overflow.getInstance().getConfigValues().getCustomAnticheatCommand() : "overflow")) {
            if (commandSender instanceof Player) {
                if (commandSender.isOp() || commandSender.hasPermission(Overflow.getInstance().getPermissionUtil().getCommandPermission())) {
                    final User user = Overflow.getInstance().getUserManager().getUser(((Player)commandSender).getUniqueId());
                    if (user != null) {
                        if (args.length > 0) {
                            final String lowerCase = args[0].toLowerCase();
                            switch (lowerCase) {
                                case "theme": {
                                    commandSender.sendMessage(ChatColor.GREEN + "Opening GUI...");
                                    new ThemeGUI().openGUI((Player)commandSender);
                                    break;
                                }
                                case "client": {
                                    try {
                                        final OfflinePlayer target2 = (OfflinePlayer)Bukkit.getPlayer(args[1]);
                                        if (target2 != null && target2.isOnline()) {
                                            final User targetUser2 = Overflow.getInstance().getUserManager().getUser(target2.getUniqueId());
                                            if (targetUser2 != null) {
                                                commandSender.sendMessage(ChatColor.GREEN + targetUser2.getPlayer().getName() + "'s " + ChatColor.GRAY + "client brand is: " + ChatColor.GREEN + ((targetUser2.getClientBrand() != null) ? targetUser2.getClientBrand() : (ChatColor.RED + "(Not Detected, User needs to relog!)")));
                                            }
                                        }
                                        else {
                                            commandSender.sendMessage(ChatColor.RED + "Player is not online.");
                                        }
                                    }
                                    catch (Exception ignored2) {
                                        commandSender.sendMessage(ChatColor.RED + "Please supply a player.");
                                    }
                                    break;
                                }
                                case "info": {
                                    commandSender.sendMessage(ChatColor.GRAY + "Overall information:");
                                    commandSender.sendMessage(ChatColor.GRAY + "Players online: " + ChatColor.RED + Bukkit.getServer().getOnlinePlayers().size());
                                    commandSender.sendMessage(ChatColor.GRAY + "Registered users: " + ChatColor.RED + Overflow.getInstance().getUserManager().getUsers().size());
                                    commandSender.sendMessage(ChatColor.GRAY + "Total flags (for this session): " + ChatColor.RED + Overflow.getInstance().getTotalFlags());
                                    commandSender.sendMessage(ChatColor.GRAY + "Total bans (for this session): " + ChatColor.RED + Overflow.getInstance().getTotalBans());
                                    commandSender.sendMessage(ChatColor.GRAY + "Checks Enabled: " + ChatColor.RED + Overflow.getInstance().getCheckManager().getCheckList().stream().filter(Check::isEnabled).count());
                                    commandSender.sendMessage(ChatColor.GRAY + "Checks Disabled: " + ChatColor.RED + Overflow.getInstance().getCheckManager().getCheckList().stream().filter(check -> !check.isEnabled()).count());
                                    commandSender.sendMessage(ChatColor.GRAY + "Checks Banning: " + ChatColor.RED + Overflow.getInstance().getCheckManager().getCheckList().stream().filter(Check::isAutoBans).count());
                                    commandSender.sendMessage(ChatColor.GRAY + "Server Version: " + ChatColor.RED + Bukkit.getVersion());
                                    commandSender.sendMessage(ChatColor.GRAY + "Worlds: " + ChatColor.RED + Bukkit.getServer().getWorlds().size());
                                    break;
                                }
                                case "reload": {
                                    commandSender.sendMessage(ChatColor.GREEN + "Reloading config...");
                                    new ConfigManager().doAction(ConfigManager.Action.SAVE);
                                    new ConfigManager().doAction(ConfigManager.Action.LOAD);
                                    Overflow.getInstance().getCheckManager().getCheckList().forEach(check -> Overflow.getInstance().getEventManager().unregisterListener(check));
                                    Overflow.getInstance().getCheckManager().getCheckList().clear();
                                    Overflow.getInstance().getCheckManager().unRegisterAll();
                                    Overflow.getInstance().getCheckManager().loadChecks();
                                    Overflow.getInstance().getUserManager().getUsers().forEach(users -> {
                                        users.setViolation(0);
                                        users.setVioSplit(0);
                                        users.setViolationSplit(0);
                                        return;
                                    });
                                    break;
                                }
                                case "ping": {
                                    try {
                                        final OfflinePlayer target2 = (OfflinePlayer)Bukkit.getPlayer(args[1]);
                                        if (target2 != null && target2.isOnline()) {
                                            final User targetUser2 = Overflow.getInstance().getUserManager().getUser(target2.getUniqueId());
                                            if (targetUser2 != null) {
                                                commandSender.sendMessage(ChatColor.GREEN + targetUser2.getPlayer().getName() + "'s " + ChatColor.GRAY + "ping is: " + targetUser2.getPing() + ChatColor.GREEN + "ms");
                                            }
                                        }
                                        else {
                                            commandSender.sendMessage(ChatColor.RED + "Player is not online.");
                                        }
                                    }
                                    catch (Exception ignored2) {
                                        commandSender.sendMessage(ChatColor.RED + "Please supply a player.");
                                    }
                                    break;
                                }
                                case "alerts": {
                                    final Player player2 = (Player)commandSender;
                                    final GlobalObject globalObject2 = Overflow.getInstance().getGlobalUserManager().getUser(player2.getUniqueId().toString());
                                    if (globalObject2 == null) {
                                        break;
                                    }
                                    if (globalObject2.isHasAlerts()) {
                                        globalObject2.setHasAlerts(false);
                                        commandSender.sendMessage(ChatColor.RED + "Alerts have been toggled off.");
                                        break;
                                    }
                                    globalObject2.setHasAlerts(true);
                                    commandSender.sendMessage(ChatColor.GREEN + "Alerts have been toggled on.");
                                    break;
                                }
                                case "fb":
                                case "forceban": {
                                    try {
                                        if (args[1] != null && args[1].length() > 0) {
                                            final OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[1]);
                                            if (target2 != null) {
                                                if (target2.hasPlayedBefore()) {
                                                    commandSender.sendMessage(ChatColor.GREEN + "Force ban executing for " + ChatColor.RED + target2.getName() + ChatColor.GREEN + "...");
                                                    Check.ban(target2.getName());
                                                }
                                                else {
                                                    commandSender.sendMessage(ChatColor.RED + "This player has never played before!");
                                                }
                                            }
                                        }
                                        else {
                                            commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                        }
                                    }
                                    catch (Exception ignored2) {
                                        commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                    }
                                    break;
                                }
                                case "gui": {
                                    commandSender.sendMessage(ChatColor.GREEN + "Opening GUI...");
                                    Overflow.getInstance().getGuiHelper().getOverflowGUI().openGUI(user);
                                    break;
                                }
                                case "dblogs": {
                                    try {
                                        final OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[1]);
                                        if (target2 != null) {
                                            commandSender.sendMessage(ChatColor.GRAY + "Logs for: " + ChatColor.GREEN + target2.getName());
                                            final GlobalObject globalObject2 = Overflow.getInstance().getGlobalUserManager().getUser(target2.getUniqueId().toString());
                                            if (globalObject2 != null) {
                                                List<String> list;
                                                final OfflinePlayer offlinePlayer;
                                                final List<String> toPaste;
                                                final Iterator<String> iterator;
                                                String s2;
                                                String[] split;
                                                String name;
                                                String check2;
                                                String vl;
                                                String time;
                                                GitHubClient client;
                                                Gist gist;
                                                GistFile file;
                                                StringBuilder sb;
                                                final GistFile gistFile;
                                                String string;
                                                Gist gist2;
                                                Overflow.getInstance().getExecutorService().execute(() -> {
                                                    list = null;
                                                    try {
                                                        list = (List<String>)Overflow.getInstance().getMySQLSocket().getAllData(offlinePlayer.getUniqueId().toString());
                                                    }
                                                    catch (SQLException e) {
                                                        e.printStackTrace();
                                                    }
                                                    toPaste = new ArrayList<String>();
                                                    if (list != null && list.size() > 0) {
                                                        list.iterator();
                                                        while (iterator.hasNext()) {
                                                            s2 = iterator.next();
                                                            split = s2.split(":");
                                                            name = split[1];
                                                            check2 = split[2];
                                                            vl = split[3];
                                                            time = split[4];
                                                            commandSender.sendMessage(ChatColor.GREEN + name + ChatColor.GRAY + " failed " + ChatColor.RED + check2 + ChatColor.RED + " x" + ChatColor.GRAY + vl + ChatColor.GRAY + " at " + ChatColor.RED + time);
                                                            toPaste.add(name + " failed " + check2 + " x" + vl + " at " + time);
                                                        }
                                                        client = new GitHubClient().setCredentials("OverflowAC", "a3373e3d9f1c85743680d01ec8e2feaa78d0cb3c");
                                                        gist = new Gist().setDescription("Overflow anticheat log paste (generated by: " + commandSender.getName() + " logs for: " + offlinePlayer.getPlayer().getName() + ")");
                                                        file = new GistFile();
                                                        toPaste.forEach(s -> {
                                                            sb = new StringBuilder();
                                                            if (gistFile.getContent() != null) {
                                                                string = gistFile.getContent() + "\n";
                                                            }
                                                            else {
                                                                string = "";
                                                            }
                                                            gistFile.setContent(sb.append(string).append(s).toString());
                                                            return;
                                                        });
                                                        gist.setPublic(false);
                                                        gist.setFiles(Collections.singletonMap("Overflow Anticheat Paste | " + offlinePlayer.getPlayer().getName() + " violations lookup (" + TimeUtils.getSystemTime() + ")", file));
                                                        try {
                                                            gist2 = new GistService(client).createGist(gist);
                                                            commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "Paste: " + ChatColor.GRAY + gist2.getHtmlUrl());
                                                        }
                                                        catch (IOException e2) {
                                                            commandSender.sendMessage(ChatColor.RED + "Unable to paste to glist!");
                                                        }
                                                    }
                                                    else {
                                                        commandSender.sendMessage(ChatColor.RED + "No logs have been stored into the database yet!");
                                                    }
                                                    return;
                                                });
                                            }
                                            else {
                                                commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                            }
                                        }
                                        else {
                                            commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                        }
                                    }
                                    catch (Exception ex) {}
                                    break;
                                }
                                case "logs": {
                                    try {
                                        final OfflinePlayer target2 = Bukkit.getOfflinePlayer(args[1]);
                                        if (target2 != null) {
                                            commandSender.sendMessage(ChatColor.GRAY + "Logs for: " + ChatColor.GREEN + target2.getName());
                                            final GlobalObject globalObject2 = Overflow.getInstance().getGlobalUserManager().getUser(target2.getUniqueId().toString());
                                            if (globalObject2 != null) {
                                                final HashMap<Check, Integer> tempMap = new HashMap<Check, Integer>();
                                                globalObject2.getFlaggedChecks().forEach(tempMap::put);
                                                final OfflinePlayer target3;
                                                tempMap.forEach((check, integer) -> commandSender.sendMessage(ChatColor.GREEN + target3.getName() + ChatColor.GRAY + " failed " + ChatColor.RED + check.getCheckName() + ChatColor.GRAY + "(" + ChatColor.RED + check.getType() + ChatColor.GRAY + ")" + ChatColor.RED + " x" + ChatColor.GRAY + integer));
                                            }
                                            else {
                                                commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                            }
                                        }
                                        else {
                                            commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                        }
                                    }
                                    catch (Exception ex2) {}
                                    break;
                                }
                                case "antivpn": {
                                    try {
                                        final String type = args[1];
                                        if (type != null) {
                                            if (type.equalsIgnoreCase("add")) {
                                                try {
                                                    final String name2 = args[2];
                                                    if (name2.length() > 0) {
                                                        final String s4;
                                                        final String UUID;
                                                        new Thread(() -> {
                                                            UUID = HTTPUtil.getUUID(s4);
                                                            if (UUID != null && UUID.length() > 3) {
                                                                if (!Overflow.getInstance().getConfigValues().getAntiVPNWhitelist().contains(UUID)) {
                                                                    Overflow.getInstance().getConfigValues().getAntiVPNWhitelist().add(UUID);
                                                                    ConfigFile.getInstance().getData().set("AntiVPN.Ignore", (Object)Overflow.getInstance().getConfigValues().getAntiVPNWhitelist());
                                                                    ConfigFile.getInstance().saveData();
                                                                    commandSender.sendMessage(ChatColor.GREEN + s4 + " " + ChatColor.GRAY + "has been added to the AntiVPN whitelist!");
                                                                }
                                                                else {
                                                                    commandSender.sendMessage(ChatColor.GREEN + s4 + " " + ChatColor.RED + "is already added to the whitelist!");
                                                                }
                                                            }
                                                            else {
                                                                commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                                            }
                                                            return;
                                                        }).start();
                                                    }
                                                    else {
                                                        commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                                    }
                                                }
                                                catch (Exception ignored3) {
                                                    commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                                }
                                            }
                                            else if (type.equalsIgnoreCase("remove")) {
                                                try {
                                                    final String name2 = args[2];
                                                    if (name2.length() > 0) {
                                                        final String s5;
                                                        final String UUID2;
                                                        new Thread(() -> {
                                                            UUID2 = HTTPUtil.getUUID(s5);
                                                            if (UUID2 != null && UUID2.length() > 3) {
                                                                if (Overflow.getInstance().getConfigValues().getAntiVPNWhitelist().contains(UUID2)) {
                                                                    Overflow.getInstance().getConfigValues().getAntiVPNWhitelist().remove(UUID2);
                                                                    ConfigFile.getInstance().getData().set("AntiVPN.Ignore", (Object)Overflow.getInstance().getConfigValues().getAntiVPNWhitelist());
                                                                    ConfigFile.getInstance().saveData();
                                                                    commandSender.sendMessage(ChatColor.GREEN + s5 + " " + ChatColor.GRAY + "has been removed from the AntiVPN whitelist!");
                                                                }
                                                                else {
                                                                    commandSender.sendMessage(ChatColor.GREEN + s5 + " " + ChatColor.RED + "is not added to the whitelist!");
                                                                }
                                                            }
                                                            else {
                                                                commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                                            }
                                                            return;
                                                        }).start();
                                                    }
                                                    else {
                                                        commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                                    }
                                                }
                                                catch (Exception ignored3) {
                                                    commandSender.sendMessage(ChatColor.RED + "Player could not be found!");
                                                }
                                            }
                                        }
                                        else {
                                            commandSender.sendMessage(ChatColor.RED + "Please supply a mode: add, remove");
                                        }
                                    }
                                    catch (Exception ignored2) {
                                        commandSender.sendMessage(ChatColor.RED + "Please supply a mode: add, remove");
                                    }
                                    break;
                                }
                            }
                        }
                        else {
                            commandSender.sendMessage(ChatColor.RED + (Overflow.getInstance().getConfigValues().isHider() ? Overflow.getInstance().getConfigValues().getCustomAnticheatName() : "Overflow ") + ChatColor.GRAY + "-" + ChatColor.GREEN + " v" + Overflow.getInstance().getDescription().getVersion());
                            final String command = Overflow.getInstance().getConfigValues().getCustomAnticheatCommand().toLowerCase().equalsIgnoreCase("overflow") ? "overflow" : Overflow.getInstance().getConfigValues().getCustomAnticheatCommand();
                            final String[] subs = { "gui", "logs", "dblogs", "antivpn", "forceban", "alerts", "ping", "client", "info" };
                            commandSender.sendMessage(ChatColor.GRAY + "Commands:");
                            for (final String s3 : subs) {
                                commandSender.sendMessage(ChatColor.RED + "/" + command + ChatColor.AQUA + " " + s3);
                            }
                        }
                    }
                    else if (Overflow.getInstance().getConfigValues().isHider()) {
                        commandSender.sendMessage(Overflow.getInstance().getConfigValues().getNoPermissionMessage());
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + "No Permission.");
                    }
                }
                else if (Overflow.getInstance().getConfigValues().isHider()) {
                    commandSender.sendMessage(Overflow.getInstance().getConfigValues().getNoPermissionMessage());
                }
                else {
                    commandSender.sendMessage(ChatColor.RED + "No Permission.");
                }
            }
            else {
                commandSender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            }
            return true;
        }
        return false;
    }
}
