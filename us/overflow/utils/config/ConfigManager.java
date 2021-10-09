// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import us.overflow.utils.command.CommandUtils;
import us.overflow.utils.file.ConfigFile;
import us.overflow.Overflow;

public class ConfigManager
{
    public ConfigManager() {
        this.doAction(Action.LOAD);
        Overflow.getInstance().getThemeManager().load(ConfigFile.getInstance().getData().getString("Theme"));
    }
    
    public void doAction(final Action action) {
        if (Overflow.getInstance().getDiscordAPI() != null) {
            Overflow.getInstance().getDiscordAPI().disconnect();
        }
        CommandUtils.unRegisterBukkitCommand("alerts");
        CommandUtils.unRegisterBukkitCommand("overflow");
        CommandUtils.unRegisterBukkitCommand("forceban");
        CommandUtils.unRegisterBukkitCommand("fb");
        CommandUtils.unRegisterBukkitCommand("client");
        CommandUtils.unRegisterBukkitCommand(Overflow.getInstance().getConfigValues().getCustomAnticheatCommand());
        ConfigFile.getInstance().setup((Plugin)Overflow.getInstance());
        if (action == Action.LOAD) {
            if (Overflow.getInstance().getMySQLSocket().isConnected()) {
                Overflow.getInstance().getMySQLSocket().disconnect();
            }
            Overflow.getInstance().getLogger().info("Loading config...");
            ConfigFile.getInstance().writeDefaults();
            Overflow.getInstance().getConfigValues().setHasAccepted(ConfigFile.getInstance().getData().getBoolean("IHaveReadThis"));
            Overflow.getInstance().getConfigValues().setPrefix(fixedColor(ConfigFile.getInstance().getData().getString("Customizer.Prefix")));
            Overflow.getInstance().getConfigValues().setAlertMessage(fixedColor(ConfigFile.getInstance().getData().getString("Customizer.Alert")));
            Overflow.getInstance().getConfigValues().setBanCommands(ConfigFile.getInstance().getData().getStringList("Punishment.commands"));
            Overflow.getInstance().getConfigValues().setBanMessagesEnabled(ConfigFile.getInstance().getData().getBoolean("Punishment.ChatAnnouncement.enabled"));
            Overflow.getInstance().getConfigValues().setBanMessages(ConfigFile.getInstance().getData().getStringList("Punishment.ChatAnnouncement.Announcement"));
            Overflow.getInstance().getConfigValues().setBansEnabled(ConfigFile.getInstance().getData().getBoolean("Punishment.enabled"));
            Overflow.getInstance().getConfigValues().setBanViolation(ConfigFile.getInstance().getData().getInt("Punishment.maxViolation"));
            Overflow.getInstance().getConfigValues().setHider(ConfigFile.getInstance().getData().getBoolean("Customizer.HideAntiCheat.enabled"));
            Overflow.getInstance().getConfigValues().setCustomAnticheatName(ConfigFile.getInstance().getData().getString("Customizer.HideAntiCheat.Name"));
            Overflow.getInstance().getConfigValues().setCustomAnticheatCommand(ConfigFile.getInstance().getData().getString("Customizer.HideAntiCheat.Command").replace("/", ""));
            Overflow.getInstance().getConfigValues().setBlockHelpCommand(ConfigFile.getInstance().getData().getBoolean("Customizer.HideAntiCheat.BlockHelpCommand"));
            Overflow.getInstance().getConfigValues().setBlockTabComplete(ConfigFile.getInstance().getData().getBoolean("Customizer.HideAntiCheat.BlockTabComplete"));
            Overflow.getInstance().getConfigValues().setNoPermissionMessage(fixedColor(ConfigFile.getInstance().getData().getString("Customizer.HideAntiCheat.NoPermissionMessage")));
            Overflow.getInstance().getPermissionUtil().setAlertsPermission(ConfigFile.getInstance().getData().getString("Permissions.alerts"));
            Overflow.getInstance().getPermissionUtil().setCommandPermission(ConfigFile.getInstance().getData().getString("Permissions.command"));
            Overflow.getInstance().getPermissionUtil().setBypassPermission(ConfigFile.getInstance().getData().getString("Permissions.bypass"));
            Overflow.getInstance().getConfigValues().setOpsBypass(ConfigFile.getInstance().getData().getBoolean("Bypass.op"));
            Overflow.getInstance().getConfigValues().setPermissionBypass(ConfigFile.getInstance().getData().getBoolean("Bypass.permission"));
            Overflow.getInstance().getConfigValues().setMysqlEnabled(ConfigFile.getInstance().getData().getBoolean("Database.enabled"));
            Overflow.getInstance().getConfigValues().setSecondLagFix(ConfigFile.getInstance().getData().getBoolean("Fix.secondLagFix"));
            Overflow.getInstance().getConfigValues().setKeepAliveFix(ConfigFile.getInstance().getData().getBoolean("Fix.KeepAlives"));
            Overflow.getInstance().getConfigValues().setMountFix(ConfigFile.getInstance().getData().getBoolean("Fix.MountFix"));
            Overflow.getInstance().getConfigValues().setAntiVPNEnabled(ConfigFile.getInstance().getData().getBoolean("AntiVPN.enabled"));
            Overflow.getInstance().getConfigValues().setAntiVPNAlert(ConfigFile.getInstance().getData().getBoolean("AntiVPN.Action.Alert"));
            Overflow.getInstance().getConfigValues().setAntiVPNKick(ConfigFile.getInstance().getData().getBoolean("AntiVPN.Action.Kick"));
            Overflow.getInstance().getConfigValues().setAntiVPNKickMessage(fixedColor(ConfigFile.getInstance().getData().getString("AntiVPN.KickMessage")));
            Overflow.getInstance().getConfigValues().setAntiVPNWhitelist(ConfigFile.getInstance().getData().getStringList("AntiVPN.Ignore"));
            Overflow.getInstance().getConfigValues().setTpsCheckEnabled(ConfigFile.getInstance().getData().getBoolean("TPSCheck.enabled"));
            Overflow.getInstance().getConfigValues().setMaxTPSTime(ConfigFile.getInstance().getData().getInt("TPSCheck.maxTime"));
            Overflow.getInstance().getConfigValues().setUseBackupLagCheck(ConfigFile.getInstance().getData().getBoolean("Fix.BackupLagCheck"));
            Overflow.getInstance().getConfigValues().setViolationSplitEnabled(ConfigFile.getInstance().getData().getBoolean("Punishment.violationSplitEnabled"));
            Overflow.getInstance().getConfigValues().setViolationSplit(ConfigFile.getInstance().getData().getInt("Punishment.violationSplit"));
            Overflow.getInstance().getConfigValues().setMaxCPS(ConfigFile.getInstance().getData().getInt("MaxCPS"));
            Overflow.getInstance().getConfigValues().setApiEvents(ConfigFile.getInstance().getData().getBoolean("API.Events"));
            Overflow.getInstance().getConfigValues().setBungeecordEnabled(ConfigFile.getInstance().getConfig().getBoolean("Bungeecord.Enabled"));
            Overflow.getInstance().getConfigValues().setBungeecordServerName(ConfigFile.getInstance().getConfig().getString("Bungeecord.ServerName"));
            Overflow.getInstance().getConfigValues().setAppendBungeecordServerName(ConfigFile.getInstance().getConfig().getBoolean("Bungeecord.appendServerName"));
            Overflow.getInstance().getConfigValues().setDiscordEnabled(ConfigFile.getInstance().getData().getBoolean("Discord.enabled"));
            Overflow.getInstance().getConfigValues().setDiscordBotToken(ConfigFile.getInstance().getData().getString("Discord.webHookURL"));
            Overflow.getInstance().getConfigValues().setDiscordMessageHeader(ConfigFile.getInstance().getData().getString("Discord.headerMessage"));
            Overflow.getInstance().getConfigValues().setDiscordMessageBody(ConfigFile.getInstance().getData().getString("Discord.bodyMessage"));
            Overflow.getInstance().getConfigValues().setDiscordPostVL(ConfigFile.getInstance().getData().getInt("Discord.violationSplit"));
            Overflow.getInstance().getConfigValues().setServerLagCheck(ConfigFile.getInstance().getData().getBoolean("ServerLagCheck"));
            Overflow.getInstance().getConfigValues().setMaxServerLagTime(ConfigFile.getInstance().getData().getLong("MaxLagTime"));
            Overflow.getInstance().getLogger().info("Config loaded!");
            if (Overflow.getInstance().getConfigValues().isMysqlEnabled()) {
                Overflow.getInstance().getLogger().info("Connecting to MYSQL Database...");
                Overflow.getInstance().getMySQLSocket().setIP(ConfigFile.getInstance().getData().getString("Database.MySQL.ip"));
                Overflow.getInstance().getMySQLSocket().setUSERNAME(ConfigFile.getInstance().getData().getString("Database.MySQL.username"));
                Overflow.getInstance().getMySQLSocket().setPASSWORD(ConfigFile.getInstance().getData().getString("Database.MySQL.password"));
                Overflow.getInstance().getMySQLSocket().setDB(ConfigFile.getInstance().getData().getString("Database.MySQL.database"));
                Overflow.getInstance().getMySQLSocket().setPORT(ConfigFile.getInstance().getData().getString("Database.MySQL.port"));
                Overflow.getInstance().getMySQLSocket().connect();
                Overflow.getInstance().getMySQLSocket().createTablesIfNotExist();
            }
        }
        else if (action == Action.SAVE) {}
        if (!Overflow.getInstance().getConfigValues().isHasAccepted()) {
            Overflow.getInstance().getLogger().warning("Please turn IHaveReadThis from false to true in the config.yml!");
            Bukkit.getPluginManager().disablePlugin((Plugin)Overflow.getInstance());
        }
        else {
            if (!Overflow.getInstance().getConfigValues().getCustomAnticheatCommand().toLowerCase().equalsIgnoreCase("overflow") && Overflow.getInstance().getConfigValues().isHider()) {
                CommandUtils.registerCommand(Overflow.getInstance().getConfigValues().getCustomAnticheatCommand());
            }
            else {
                CommandUtils.registerCommand("overflow");
            }
            CommandUtils.registerCommand("alerts");
            CommandUtils.registerCommand("forceban");
            CommandUtils.registerCommand("fb");
            CommandUtils.registerCommand("client");
            if (Overflow.getInstance().getDiscordAPI() != null && Overflow.getInstance().getConfigValues().isDiscordEnabled()) {
                Overflow.getInstance().getDiscordAPI().connect();
            }
        }
    }
    
    public static String fixedColor(final String s) {
        return s.replace("&", "§");
    }
    
    public enum Action
    {
        public static final Action LOAD;
        public static final Action SAVE;
        
        public static Action valueOf(final String name) {
            return Enum.valueOf(Action.class, name);
        }
        
        static {
            Action.LOAD = new Action("LOAD", 0);
            Action.SAVE = new Action("SAVE", 1);
            Action.$VALUES = new Action[] { Action.LOAD, Action.SAVE };
        }
    }
}
