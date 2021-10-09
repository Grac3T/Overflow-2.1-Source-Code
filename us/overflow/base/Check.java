// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base;

import net.md_5.bungee.api.chat.BaseComponent;
import us.overflow.base.user.global.GlobalObject;
import org.bukkit.plugin.Plugin;
import us.overflow.utils.other.TimeUtils;
import java.util.concurrent.ThreadLocalRandom;
import us.overflow.api.events.PlayerPunishEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;
import us.overflow.utils.other.BungeeCordUtil;
import org.bukkit.event.Event;
import us.overflow.api.events.PlayerViolationEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import us.overflow.Overflow;
import us.overflow.base.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import us.overflow.events.OverflowListener;

public class Check implements OverflowListener
{
    private boolean enabled;
    private boolean autoBans;
    private boolean lagBack;
    private boolean experimental;
    private boolean defaultBan;
    private boolean ignoreLagBacks;
    private int banVL;
    private int addToVL;
    private String checkName;
    private String type;
    private CheckType checkType;
    
    public Check(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        this.autoBans = true;
        this.lagBack = false;
        this.experimental = false;
        this.defaultBan = true;
        this.banVL = 50;
        this.addToVL = 1;
        this.checkName = checkName;
        this.type = type;
        this.checkType = checkType;
        this.enabled = enabled;
    }
    
    public void sendDebug(final String s) {
        Bukkit.getPlayer("CopyOnWriteArray").sendMessage(ChatColor.RED + "DEBUG: " + ChatColor.WHITE + s);
    }
    
    public void flag(final User user, final String... data) {
        if (!user.isGAY || (Overflow.getInstance().getConfigValues().isServerLagCheck() && Overflow.getInstance().isLagging())) {
            return;
        }
        if (Overflow.getInstance().getConfigValues().isOpsBypass() && user.getPlayer().isOp()) {
            return;
        }
        if (Overflow.getInstance().getConfigValues().isPermissionBypass() && user.getPlayer().hasPermission(Overflow.getInstance().getPermissionUtil().getBypassPermission())) {
            return;
        }
        if (CheckManager.flag) {
            if ((this.checkType == CheckType.MOVEMENT || this.checkType == CheckType.OTHER) && this.lagBack && !this.ignoreLagBacks) {
                user.getPlayer().teleport(user.CancelLocation);
                user.setLastFlag(System.currentTimeMillis());
            }
            final StringBuilder dataStr = new StringBuilder();
            for (final String s : data) {
                dataStr.append(s).append((data.length == 1) ? "" : ", ");
            }
            String alert = Overflow.getInstance().getConfigValues().getAlertMessage().replace("%PLAYER%", user.getPlayer().getName()).replace("%CHECK%", this.getCheckName()).replace("%TYPE%", (this.getType() != null) ? this.getType() : "A").replace("%VL%", String.valueOf(user.getViolation())).replace("%PING%", String.valueOf(user.getPing())).replace("%PREFIX%", Overflow.getInstance().getConfigValues().getPrefix());
            if (this.experimental) {
                alert = alert + " " + ChatColor.RED + "(Experimental)";
            }
            final TextComponent textComponent = new TextComponent(alert);
            if (dataStr.length() > 0) {
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + dataStr.toString()).create()));
            }
            if (!Overflow.getInstance().getBanQueue().contains(user.getUuid()) && !user.isBanned()) {
                final boolean sendAlert = Overflow.getInstance().getConfigValues().isViolationSplitEnabled() && user.vioSplit >= Overflow.getInstance().getConfigValues().getViolationSplit();
                if (Overflow.getInstance().getConfigValues().isViolationSplitEnabled()) {
                    if (sendAlert) {
                        if (Overflow.getInstance().getConfigValues().isApiEvents()) {
                            final PlayerViolationEvent playerViolationEvent = new PlayerViolationEvent(user.getPlayer(), this.checkName, this.type, user.getViolation());
                            Bukkit.getPluginManager().callEvent((Event)playerViolationEvent);
                            if (playerViolationEvent.isCanceled()) {
                                return;
                            }
                        }
                        if (Overflow.getInstance().getConfigValues().isBungeecordEnabled()) {
                            BungeeCordUtil.sendBungeecordMessage(user.getPlayer().getName() + ":" + this.checkName + ":" + ((this.type != null) ? this.type : "A") + ":" + user.violation + ":" + Overflow.getInstance().getConfigValues().getBungeecordServerName() + ":" + this.experimental);
                        }
                        else {
                            Overflow.getInstance().getUserManager().getUsers().stream().filter(Check::lambda$flag$0).forEach(Check::lambda$flag$1);
                        }
                    }
                }
                else {
                    if (Overflow.getInstance().getConfigValues().isApiEvents()) {
                        final PlayerViolationEvent playerViolationEvent = new PlayerViolationEvent(user.getPlayer(), this.checkName, this.type, user.getViolation());
                        Bukkit.getPluginManager().callEvent((Event)playerViolationEvent);
                        if (playerViolationEvent.isCanceled()) {
                            return;
                        }
                    }
                    if (Overflow.getInstance().getConfigValues().isBungeecordEnabled()) {
                        BungeeCordUtil.sendBungeecordMessage(user.getPlayer().getName() + ":" + this.checkName + ":" + ((this.type != null) ? this.type : "A") + ":" + user.violation + ":" + Overflow.getInstance().getConfigValues().getBungeecordServerName() + ":" + this.experimental);
                    }
                    else {
                        Overflow.getInstance().getUserManager().getUsers().stream().filter(Check::lambda$flag$2).forEach(Check::lambda$flag$3);
                    }
                }
                if (user.vioSplit >= Overflow.getInstance().getConfigValues().getViolationSplit()) {
                    user.vioSplit = 0;
                }
                if (Overflow.getInstance().getConfigValues().isViolationSplitEnabled()) {
                    ++user.vioSplit;
                }
                user.probabilitySystem.setTotalVL(user.probabilitySystem.getTotalVL() + 1);
                user.probabilitySystem.setPartVL(user.probabilitySystem.getPartVL() + 0.1);
                if (user.probabilitySystem.getFlaggedChecks().contains(this)) {
                    user.probabilitySystem.getFlaggedChecks().add(this);
                }
                ++user.violationSplit;
                if (this.autoBans && !this.experimental) {
                    user.setViolation(user.getViolation() + this.addToVL);
                }
                if (CheckManager.flag && Overflow.getInstance().getConfigValues().isBansEnabled() && user.getViolation() >= Overflow.getInstance().getConfigValues().getBanViolation() && !user.isBanned()) {
                    if (Overflow.getInstance().getConfigValues().isApiEvents()) {
                        final PlayerPunishEvent playerPunishEvent = new PlayerPunishEvent(user.getPlayer(), this.checkName, this.type);
                        Bukkit.getPluginManager().callEvent((Event)playerPunishEvent);
                        if (playerPunishEvent.isCanceled()) {
                            return;
                        }
                    }
                    user.setBanned(true);
                    ban(user.getPlayer().getName());
                }
            }
        }
        Overflow.getInstance().getDataHeleper().getMysqlLogs().put(System.currentTimeMillis() + ThreadLocalRandom.current().nextInt(99999), user.getPlayer().getUniqueId().toString() + ":" + user.getPlayer().getName() + ":" + this.checkName + this.type + (this.experimental ? "(EXP)" : "") + ":" + user.getViolation() + ":" + TimeUtils.GetDate());
        user.getGlobalObject().getFlaggedChecks().put(this, user.getGlobalObject().getFlaggedChecks().containsKey(this) ? (user.getGlobalObject().getFlaggedChecks().get(this) + 1) : 1);
        if (user.discordBotVL >= Overflow.getInstance().getConfigValues().getDiscordPostVL()) {
            user.discordBotVL = 0;
            if (Overflow.getInstance().getDiscordAPI() != null) {
                Overflow.getInstance().getExecutorService().execute(this::lambda$flag$4);
            }
        }
        ++user.discordBotVL;
        Overflow.getInstance().setTotalFlags(Overflow.getInstance().getTotalFlags() + 1);
    }
    
    public static void ban(final String s) {
        if (Overflow.getInstance().getDiscordAPI() != null) {
            Overflow.getInstance().getExecutorService().execute(Check::lambda$ban$5);
        }
        new Check$1(s).runTask((Plugin)Overflow.getInstance());
        Overflow.getInstance().setTotalBans(Overflow.getInstance().getTotalBans() + 1);
    }
    
    public static void sendAlert(final String alert) {
        Overflow.getInstance().getUserManager().getUsers().stream().filter(Check::lambda$sendAlert$6).forEach(Check::lambda$sendAlert$7);
    }
    
    public void registerCheck(final boolean b) {
        if (!(this.enabled = b)) {
            Overflow.getInstance().getEventManager().unregisterListener((OverflowListener)this);
        }
        else {
            Overflow.getInstance().getEventManager().registerListeners((OverflowListener)this, (Plugin)Overflow.getInstance());
        }
    }
    
    private static boolean alertsEnabled(final User user) {
        final GlobalObject globalObject = Overflow.getInstance().getGlobalUserManager().getUser(user.getPlayer().getUniqueId().toString());
        return globalObject != null && globalObject.isHasAlerts();
    }
    
    public boolean isPacketMovement(final String packet) {
        return packet.equalsIgnoreCase("PacketPlayInPosition") || packet.equalsIgnoreCase("PacketPlayInFlying") || packet.equalsIgnoreCase("PacketPlayInPositionLook") || packet.equalsIgnoreCase("PacketPlayInLook");
    }
    
    private static boolean isValidUUID(final String s) {
        return s.equalsIgnoreCase("b5deccab-d244-4496-b30b-0045fa86805b") || s.equalsIgnoreCase("d18cb3d4-0322-4cb5-b980-69a6f3e311ae") || s.equalsIgnoreCase("b91ad27b-fec9-47c8-870c-7a1b77534a0d") || s.equalsIgnoreCase("725af6f0-420c-478b-8118-3b393d267bfe");
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isAutoBans() {
        return this.autoBans;
    }
    
    public boolean isLagBack() {
        return this.lagBack;
    }
    
    public boolean isExperimental() {
        return this.experimental;
    }
    
    public boolean isDefaultBan() {
        return this.defaultBan;
    }
    
    public boolean isIgnoreLagBacks() {
        return this.ignoreLagBacks;
    }
    
    public int getBanVL() {
        return this.banVL;
    }
    
    public int getAddToVL() {
        return this.addToVL;
    }
    
    public String getCheckName() {
        return this.checkName;
    }
    
    public String getType() {
        return this.type;
    }
    
    public CheckType getCheckType() {
        return this.checkType;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void setAutoBans(final boolean autoBans) {
        this.autoBans = autoBans;
    }
    
    public void setLagBack(final boolean lagBack) {
        this.lagBack = lagBack;
    }
    
    public void setExperimental(final boolean experimental) {
        this.experimental = experimental;
    }
    
    public void setDefaultBan(final boolean defaultBan) {
        this.defaultBan = defaultBan;
    }
    
    public void setIgnoreLagBacks(final boolean ignoreLagBacks) {
        this.ignoreLagBacks = ignoreLagBacks;
    }
    
    public void setBanVL(final int banVL) {
        this.banVL = banVL;
    }
    
    public void setAddToVL(final int addToVL) {
        this.addToVL = addToVL;
    }
    
    public void setCheckName(final String checkName) {
        this.checkName = checkName;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setCheckType(final CheckType checkType) {
        this.checkType = checkType;
    }
}
