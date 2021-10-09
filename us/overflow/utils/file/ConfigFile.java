// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.file;

import org.bukkit.plugin.PluginDescriptionFile;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;
import us.overflow.Overflow;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigFile
{
    static ConfigFile instance;
    Plugin p;
    FileConfiguration config;
    File cfile;
    FileConfiguration data;
    File dfile;
    
    public static ConfigFile getInstance() {
        return ConfigFile.instance;
    }
    
    public void setup(final Plugin p) {
        this.config = p.getConfig();
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        this.dfile = new File(Overflow.getInstance().configDIR);
        if (!this.dfile.exists()) {
            try {
                this.dfile.createNewFile();
            }
            catch (IOException ex) {}
        }
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
    }
    
    public FileConfiguration getData() {
        return this.data;
    }
    
    public void writeDefaults() {
        this.data.options().header("Overflow Config File\n \n\nIHaveReadThis:\nFor the sake of us not wanting to get sued, we have to disclose all things that the following feature do:\nDiscordIntegration, and HelpUsOut\n\nOur disclosure is found here: https://docs.google.com/document/d/1VZJTem9tYiwand4Giyy2JJVcL9NumZDju97nja4UNYU/edit\n\"IHaveReadThis\" must be set to true for ANY setting, or feature to work.\n\nPunishment:\nThis is a setting that allows you to edit what happens to the user when they are getting punished. This is the lead feature of this section which means it must be on for all the other features in the section to work.\n\nThe \"ChatAnnouncement\" feature allows you to announce to the server when the user is punished. For this feature to work the \"Punishment\" feature must be enabled.\n\nCustomizer:\nThis feature allows you to rename the anticheat to what you feel is right for your server.\n\nWith this feature set to true the name displayed when commands such as /pl are run will be");
        if (!this.data.contains("IHaveReadThis")) {
            this.data.set("IHaveReadThis", (Object)false);
        }
        if (!this.data.contains("Punishment.enabled")) {
            this.data.set("Punishment.enabled", (Object)true);
        }
        if (!this.data.contains("Punishment.maxViolation")) {
            this.data.set("Punishment.maxViolation", (Object)20);
        }
        if (!this.data.contains("Punishment.violationSplitEnabled")) {
            this.data.set("Punishment.violationSplitEnabled", (Object)false);
        }
        if (!this.data.contains("Punishment.violationSplit")) {
            this.data.set("Punishment.violationSplit", (Object)5);
        }
        if (!this.data.contains("Punishment.commands")) {
            final List<String> array = new ArrayList<String>();
            array.add("/kick %PLAYER% &7[&cOverFlow&7] Unfair Advantage -s");
            this.data.set("Punishment.commands", (Object)array);
        }
        if (!this.data.contains("Punishment.ChatAnnouncement.enabled")) {
            this.data.set("Punishment.ChatAnnouncement.enabled", (Object)true);
        }
        if (!this.data.contains("Punishment.ChatAnnouncement.Announcement")) {
            final List<String> array = new ArrayList<String>();
            array.add("%LINE%");
            array.add("%PREFIX% The anticheat has removed %PLAYER% from the server for cheating!");
            array.add("%LINE%");
            this.data.set("Punishment.ChatAnnouncement.Announcement", (Object)array);
        }
        if (!this.data.contains("Customizer.HideAntiCheat")) {
            this.data.set("Customizer.HideAntiCheat.enabled", (Object)false);
        }
        if (!this.data.contains("Customizer.HideAntiCheat.Name")) {
            this.data.set("Customizer.HideAntiCheat.Name", (Object)"Anticheat");
        }
        if (!this.data.contains("Customizer.HideAntiCheat.Command")) {
            this.data.set("Customizer.HideAntiCheat.Command", (Object)"/anticheat");
        }
        if (!this.data.contains("Customizer.HideAntiCheat.BlockHelpCommand")) {
            this.data.set("Customizer.HideAntiCheat.BlockHelpCommand", (Object)true);
        }
        if (!this.data.contains("Customizer.HideAntiCheat.BlockTabComplete")) {
            this.data.set("Customizer.HideAntiCheat.BlockTabComplete", (Object)true);
        }
        if (!this.data.contains("Customizer.HideAntiCheat.NoPermissionMessage")) {
            this.data.set("Customizer.HideAntiCheat.NoPermissionMessage", (Object)"Unknown command. Type \"/help\" for help.");
        }
        if (!this.data.contains("Customizer.Prefix")) {
            this.data.set("Customizer.Prefix", (Object)"&7[&cOverflow&7]&r");
        }
        if (!this.data.contains("Customizer.Alert")) {
            this.data.set("Customizer.Alert", (Object)"&c%PREFIX% &7%PLAYER% flagged &c%CHECK% &7(&ctype %TYPE%&7) VL: &c%VL%");
        }
        if (!this.data.contains("Permissions.alerts")) {
            this.data.set("Permissions.alerts", (Object)"overflow.alerts");
        }
        if (!this.data.contains("Permissions.command")) {
            this.data.set("Permissions.command", (Object)"overflow.command");
        }
        if (!this.data.contains("Permissions.bypass")) {
            this.data.set("Permissions.bypass", (Object)"overflow.bypass");
        }
        if (!this.data.contains("Bypass.op")) {
            this.data.set("Bypass.op", (Object)false);
        }
        if (!this.data.contains("Bypass.permission")) {
            this.data.set("Bypass.permission", (Object)false);
        }
        if (!this.data.contains("Database.enabled")) {
            this.data.set("Database.enabled", (Object)false);
        }
        if (!this.data.contains("Database.MySQL.ip")) {
            this.data.set("Database.MySQL.ip", (Object)"192.168.0.0");
        }
        if (!this.data.contains("Database.MySQL.port")) {
            this.data.set("Database.MySQL.port", (Object)"3306");
        }
        if (!this.data.contains("Database.MySQL.username")) {
            this.data.set("Database.MySQL.username", (Object)"admin");
        }
        if (!this.data.contains("Database.MySQL.password")) {
            this.data.set("Database.MySQL.password", (Object)"password");
        }
        if (!this.data.contains("Database.MySQL.database")) {
            this.data.set("Database.MySQL.database", (Object)"Overflow");
        }
        if (!this.data.contains("AntiVPN.enabled")) {
            this.data.set("AntiVPN.enabled", (Object)false);
        }
        if (!this.data.contains("AntiVPN.Action.Alert")) {
            this.data.set("AntiVPN.Action.Alert", (Object)false);
        }
        if (!this.data.contains("AntiVPN.Action.Kick")) {
            this.data.set("AntiVPN.Action.Kick", (Object)true);
        }
        if (!this.data.contains("AntiVPN.KickMessage")) {
            this.data.set("AntiVPN.KickMessage", (Object)"&cPlease disconnect from your VPN / Proxy to connect.");
        }
        if (!this.data.contains("AntiVPN.Ignore")) {
            final List<String> list = new ArrayList<String>();
            list.add("UUID_HERE");
            this.data.set("AntiVPN.Ignore", (Object)list);
        }
        if (!this.data.contains("TPSCheck.enabled")) {
            this.data.set("TPSCheck.enabled", (Object)true);
        }
        if (!this.data.contains("TPSCheck.maxTime")) {
            this.data.set("TPSCheck.maxTime", (Object)152);
        }
        if (!this.data.contains("Fix.secondLagFix")) {
            this.data.set("Fix.secondLagFix", (Object)true);
        }
        if (!this.data.contains("Fix.KeepAlives")) {
            this.data.set("Fix.KeepAlives", (Object)false);
        }
        if (!this.data.contains("Fix.MountFix")) {
            this.data.set("Fix.MountFix", (Object)true);
        }
        if (!this.data.contains("Fix.BackupLagCheck")) {
            this.data.set("Fix.BackupLagCheck", (Object)false);
        }
        if (!this.data.contains("MaxCPS")) {
            this.data.set("MaxCPS", (Object)20);
        }
        if (!this.data.contains("API.Events")) {
            this.data.set("API.Events", (Object)false);
        }
        if (!this.data.contains("Bungeecord.Enabled")) {
            this.data.set("Bungeecord.Enabled", (Object)false);
        }
        if (!this.data.contains("Bungeecord.ServerName")) {
            this.data.set("Bungeecord.ServerName", (Object)"KitPvP");
        }
        if (!this.data.contains("Bungeecord.appendServerName")) {
            this.data.set("Bungeecord.appendServerName", (Object)true);
        }
        if (!this.data.contains("Discord.enabled")) {
            this.data.set("Discord.enabled", (Object)false);
        }
        if (!this.data.contains("Discord.webHookURL")) {
            this.data.set("Discord.webHookURL", (Object)"DISCORD_WEB_HOOK_URL_HERE");
        }
        if (!this.data.contains("Discord.headerMessage")) {
            this.data.set("Discord.headerMessage", (Object)"Anticheat | Flag");
        }
        if (!this.data.contains("Discord.bodyMessage")) {
            this.data.set("Discord.bodyMessage", (Object)"%PLAYER% flagged %CHECK% (%TYPE%) x%VL%");
        }
        if (!this.data.contains("Discord.violationSplit")) {
            this.data.set("Discord.violationSplit", (Object)10);
        }
        if (!this.data.contains("Theme")) {
            this.data.set("Theme", (Object)"OVERFLOW_2_0");
        }
        if (!this.data.contains("ServerLagCheck")) {
            this.data.set("ServerLagCheck", (Object)true);
        }
        if (!this.data.contains("MaxLagTime")) {
            this.data.set("MaxLagTime", (Object)5000L);
        }
        this.saveData();
    }
    
    public void saveOptions() {
        if (Overflow.getInstance().getConfigValues().getAntiVPNWhitelist().size() > 0) {
            this.data.set("AntiVPN.Ignore", (Object)Overflow.getInstance().getConfigValues().getAntiVPNWhitelist());
        }
        this.saveData();
    }
    
    public void saveData() {
        try {
            this.data.save(this.dfile);
        }
        catch (IOException ex) {}
    }
    
    public void reloadData() {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
    }
    
    public FileConfiguration getConfig() {
        return this.config;
    }
    
    public void saveConfig() {
        try {
            this.config.save(this.cfile);
        }
        catch (IOException ex) {}
    }
    
    public void reloadConfig() {
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
    }
    
    public PluginDescriptionFile getDesc() {
        return this.p.getDescription();
    }
    
    static {
        ConfigFile.instance = new ConfigFile();
    }
}
