// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigValues
{
    private String discordEmbedAuthor;
    private String discordBotToken;
    private String discordChannel;
    private String discordMessageBody;
    private String discordMessageHeader;
    private String bungeecordServerName;
    private String prefix;
    private String alertMessage;
    private String customAnticheatName;
    private String customAnticheatCommand;
    private String noPermissionMessage;
    private String mysqlIP;
    private String mysqlPassword;
    private String mysqlUsername;
    private String mysqlDB;
    private String antiVPNKickMessage;
    private List<String> banCommands;
    private List<String> banMessages;
    private List<String> antiVPNWhitelist;
    private int discordPostVL;
    private int maxCPS;
    private int violationSplit;
    private int maxTPSTime;
    private int banWaitTime;
    private int banViolation;
    private int mysqlPort;
    private boolean serverLagCheck;
    private boolean banMessagesEnabled;
    private boolean discordEnabled;
    private boolean appendBungeecordServerName;
    private boolean bungeecordEnabled;
    private boolean apiEvents;
    private boolean violationSplitEnabled;
    private boolean useBackupLagCheck;
    private boolean tpsCheckEnabled;
    private boolean antiVPNKick;
    private boolean antiVPNAlert;
    private boolean antiVPNEnabled;
    private boolean mountFix;
    private boolean keepAliveFix;
    private boolean secondLagFix;
    private boolean bansEnabled;
    private boolean hasAccepted;
    private boolean hider;
    private boolean blockHelpCommand;
    private boolean blockTabComplete;
    private boolean opsBypass;
    private boolean permissionBypass;
    private boolean mysqlEnabled;
    private long maxServerLagTime;
    
    public ConfigValues() {
        this.banCommands = new ArrayList();
        this.banMessages = new ArrayList();
        this.antiVPNWhitelist = new ArrayList();
    }
    
    public String getDiscordEmbedAuthor() {
        return this.discordEmbedAuthor;
    }
    
    public String getDiscordBotToken() {
        return this.discordBotToken;
    }
    
    public String getDiscordChannel() {
        return this.discordChannel;
    }
    
    public String getDiscordMessageBody() {
        return this.discordMessageBody;
    }
    
    public String getDiscordMessageHeader() {
        return this.discordMessageHeader;
    }
    
    public String getBungeecordServerName() {
        return this.bungeecordServerName;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getAlertMessage() {
        return this.alertMessage;
    }
    
    public String getCustomAnticheatName() {
        return this.customAnticheatName;
    }
    
    public String getCustomAnticheatCommand() {
        return this.customAnticheatCommand;
    }
    
    public String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
    
    public String getMysqlIP() {
        return this.mysqlIP;
    }
    
    public String getMysqlPassword() {
        return this.mysqlPassword;
    }
    
    public String getMysqlUsername() {
        return this.mysqlUsername;
    }
    
    public String getMysqlDB() {
        return this.mysqlDB;
    }
    
    public String getAntiVPNKickMessage() {
        return this.antiVPNKickMessage;
    }
    
    public List<String> getBanCommands() {
        return (List<String>)this.banCommands;
    }
    
    public List<String> getBanMessages() {
        return (List<String>)this.banMessages;
    }
    
    public List<String> getAntiVPNWhitelist() {
        return (List<String>)this.antiVPNWhitelist;
    }
    
    public int getDiscordPostVL() {
        return this.discordPostVL;
    }
    
    public int getMaxCPS() {
        return this.maxCPS;
    }
    
    public int getViolationSplit() {
        return this.violationSplit;
    }
    
    public int getMaxTPSTime() {
        return this.maxTPSTime;
    }
    
    public int getBanWaitTime() {
        return this.banWaitTime;
    }
    
    public int getBanViolation() {
        return this.banViolation;
    }
    
    public int getMysqlPort() {
        return this.mysqlPort;
    }
    
    public boolean isServerLagCheck() {
        return this.serverLagCheck;
    }
    
    public boolean isBanMessagesEnabled() {
        return this.banMessagesEnabled;
    }
    
    public boolean isDiscordEnabled() {
        return this.discordEnabled;
    }
    
    public boolean isAppendBungeecordServerName() {
        return this.appendBungeecordServerName;
    }
    
    public boolean isBungeecordEnabled() {
        return this.bungeecordEnabled;
    }
    
    public boolean isApiEvents() {
        return this.apiEvents;
    }
    
    public boolean isViolationSplitEnabled() {
        return this.violationSplitEnabled;
    }
    
    public boolean isUseBackupLagCheck() {
        return this.useBackupLagCheck;
    }
    
    public boolean isTpsCheckEnabled() {
        return this.tpsCheckEnabled;
    }
    
    public boolean isAntiVPNKick() {
        return this.antiVPNKick;
    }
    
    public boolean isAntiVPNAlert() {
        return this.antiVPNAlert;
    }
    
    public boolean isAntiVPNEnabled() {
        return this.antiVPNEnabled;
    }
    
    public boolean isMountFix() {
        return this.mountFix;
    }
    
    public boolean isKeepAliveFix() {
        return this.keepAliveFix;
    }
    
    public boolean isSecondLagFix() {
        return this.secondLagFix;
    }
    
    public boolean isBansEnabled() {
        return this.bansEnabled;
    }
    
    public boolean isHasAccepted() {
        return this.hasAccepted;
    }
    
    public boolean isHider() {
        return this.hider;
    }
    
    public boolean isBlockHelpCommand() {
        return this.blockHelpCommand;
    }
    
    public boolean isBlockTabComplete() {
        return this.blockTabComplete;
    }
    
    public boolean isOpsBypass() {
        return this.opsBypass;
    }
    
    public boolean isPermissionBypass() {
        return this.permissionBypass;
    }
    
    public boolean isMysqlEnabled() {
        return this.mysqlEnabled;
    }
    
    public long getMaxServerLagTime() {
        return this.maxServerLagTime;
    }
    
    public void setDiscordEmbedAuthor(final String discordEmbedAuthor) {
        this.discordEmbedAuthor = discordEmbedAuthor;
    }
    
    public void setDiscordBotToken(final String discordBotToken) {
        this.discordBotToken = discordBotToken;
    }
    
    public void setDiscordChannel(final String discordChannel) {
        this.discordChannel = discordChannel;
    }
    
    public void setDiscordMessageBody(final String discordMessageBody) {
        this.discordMessageBody = discordMessageBody;
    }
    
    public void setDiscordMessageHeader(final String discordMessageHeader) {
        this.discordMessageHeader = discordMessageHeader;
    }
    
    public void setBungeecordServerName(final String bungeecordServerName) {
        this.bungeecordServerName = bungeecordServerName;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public void setAlertMessage(final String alertMessage) {
        this.alertMessage = alertMessage;
    }
    
    public void setCustomAnticheatName(final String customAnticheatName) {
        this.customAnticheatName = customAnticheatName;
    }
    
    public void setCustomAnticheatCommand(final String customAnticheatCommand) {
        this.customAnticheatCommand = customAnticheatCommand;
    }
    
    public void setNoPermissionMessage(final String noPermissionMessage) {
        this.noPermissionMessage = noPermissionMessage;
    }
    
    public void setMysqlIP(final String mysqlIP) {
        this.mysqlIP = mysqlIP;
    }
    
    public void setMysqlPassword(final String mysqlPassword) {
        this.mysqlPassword = mysqlPassword;
    }
    
    public void setMysqlUsername(final String mysqlUsername) {
        this.mysqlUsername = mysqlUsername;
    }
    
    public void setMysqlDB(final String mysqlDB) {
        this.mysqlDB = mysqlDB;
    }
    
    public void setAntiVPNKickMessage(final String antiVPNKickMessage) {
        this.antiVPNKickMessage = antiVPNKickMessage;
    }
    
    public void setBanCommands(final List<String> banCommands) {
        this.banCommands = banCommands;
    }
    
    public void setBanMessages(final List<String> banMessages) {
        this.banMessages = banMessages;
    }
    
    public void setAntiVPNWhitelist(final List<String> antiVPNWhitelist) {
        this.antiVPNWhitelist = antiVPNWhitelist;
    }
    
    public void setDiscordPostVL(final int discordPostVL) {
        this.discordPostVL = discordPostVL;
    }
    
    public void setMaxCPS(final int maxCPS) {
        this.maxCPS = maxCPS;
    }
    
    public void setViolationSplit(final int violationSplit) {
        this.violationSplit = violationSplit;
    }
    
    public void setMaxTPSTime(final int maxTPSTime) {
        this.maxTPSTime = maxTPSTime;
    }
    
    public void setBanWaitTime(final int banWaitTime) {
        this.banWaitTime = banWaitTime;
    }
    
    public void setBanViolation(final int banViolation) {
        this.banViolation = banViolation;
    }
    
    public void setMysqlPort(final int mysqlPort) {
        this.mysqlPort = mysqlPort;
    }
    
    public void setServerLagCheck(final boolean serverLagCheck) {
        this.serverLagCheck = serverLagCheck;
    }
    
    public void setBanMessagesEnabled(final boolean banMessagesEnabled) {
        this.banMessagesEnabled = banMessagesEnabled;
    }
    
    public void setDiscordEnabled(final boolean discordEnabled) {
        this.discordEnabled = discordEnabled;
    }
    
    public void setAppendBungeecordServerName(final boolean appendBungeecordServerName) {
        this.appendBungeecordServerName = appendBungeecordServerName;
    }
    
    public void setBungeecordEnabled(final boolean bungeecordEnabled) {
        this.bungeecordEnabled = bungeecordEnabled;
    }
    
    public void setApiEvents(final boolean apiEvents) {
        this.apiEvents = apiEvents;
    }
    
    public void setViolationSplitEnabled(final boolean violationSplitEnabled) {
        this.violationSplitEnabled = violationSplitEnabled;
    }
    
    public void setUseBackupLagCheck(final boolean useBackupLagCheck) {
        this.useBackupLagCheck = useBackupLagCheck;
    }
    
    public void setTpsCheckEnabled(final boolean tpsCheckEnabled) {
        this.tpsCheckEnabled = tpsCheckEnabled;
    }
    
    public void setAntiVPNKick(final boolean antiVPNKick) {
        this.antiVPNKick = antiVPNKick;
    }
    
    public void setAntiVPNAlert(final boolean antiVPNAlert) {
        this.antiVPNAlert = antiVPNAlert;
    }
    
    public void setAntiVPNEnabled(final boolean antiVPNEnabled) {
        this.antiVPNEnabled = antiVPNEnabled;
    }
    
    public void setMountFix(final boolean mountFix) {
        this.mountFix = mountFix;
    }
    
    public void setKeepAliveFix(final boolean keepAliveFix) {
        this.keepAliveFix = keepAliveFix;
    }
    
    public void setSecondLagFix(final boolean secondLagFix) {
        this.secondLagFix = secondLagFix;
    }
    
    public void setBansEnabled(final boolean bansEnabled) {
        this.bansEnabled = bansEnabled;
    }
    
    public void setHasAccepted(final boolean hasAccepted) {
        this.hasAccepted = hasAccepted;
    }
    
    public void setHider(final boolean hider) {
        this.hider = hider;
    }
    
    public void setBlockHelpCommand(final boolean blockHelpCommand) {
        this.blockHelpCommand = blockHelpCommand;
    }
    
    public void setBlockTabComplete(final boolean blockTabComplete) {
        this.blockTabComplete = blockTabComplete;
    }
    
    public void setOpsBypass(final boolean opsBypass) {
        this.opsBypass = opsBypass;
    }
    
    public void setPermissionBypass(final boolean permissionBypass) {
        this.permissionBypass = permissionBypass;
    }
    
    public void setMysqlEnabled(final boolean mysqlEnabled) {
        this.mysqlEnabled = mysqlEnabled;
    }
    
    public void setMaxServerLagTime(final long maxServerLagTime) {
        this.maxServerLagTime = maxServerLagTime;
    }
}
