// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.antivpn;

import us.overflow.base.user.User;
import org.bukkit.ChatColor;
import java.util.stream.Stream;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import us.overflow.utils.http.HTTPUtil;
import us.overflow.Overflow;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class AntiVPNListener implements Listener
{
    private static HashMap<String, String> headers;
    private List<String> alertList;
    
    public AntiVPNListener() {
        this.alertList = new CopyOnWriteArrayList<String>();
    }
    
    @EventHandler
    public void onPreLogin(final AsyncPlayerPreLoginEvent e) {
        if (Overflow.getInstance().getConfigValues().getAntiVPNWhitelist().contains(e.getUniqueId().toString())) {
            return;
        }
        if (Overflow.getInstance().getConfigValues().isAntiVPNEnabled()) {
            final String result = HTTPUtil.getResponse(String.format("http://service.overflowac.pw/vpnCheck.php?IP=%s", e.getAddress().getHostAddress().replace("/", "").replace(" ", "")), (HashMap)AntiVPNListener.headers);
            if (result.length() > 1 && result.contains("true")) {
                if (Overflow.getInstance().getConfigValues().isAntiVPNAlert() && !this.alertList.contains(e.getUniqueId().toString())) {
                    this.alertList.add(e.getUniqueId().toString());
                }
                if (Overflow.getInstance().getConfigValues().isAntiVPNKick()) {
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Overflow.getInstance().getConfigValues().getAntiVPNKickMessage());
                }
            }
        }
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        if (this.alertList.contains(e.getPlayer().getUniqueId().toString())) {
            this.alertList.remove(e.getPlayer().getUniqueId().toString());
            this.alertStaff(e.getPlayer());
        }
    }
    
    private void alertStaff(final Player player) {
        Overflow.getInstance().getExecutorService().execute(() -> ((Stream)Overflow.getInstance().getUserManager().getUsers().stream().parallel()).filter(staff -> staff.getPlayer().isOp() || staff.getPlayer().hasPermission("overflow.alerts")).forEach(staff -> staff.getPlayer().sendMessage(Overflow.getInstance().getConfigValues().getPrefix() + " " + ChatColor.RED + player.getName() + ChatColor.GRAY + " is using a VPN!")));
    }
    
    static {
        (AntiVPNListener.headers = new HashMap<String, String>()).put("KEY", "gnEFOWNYUGuATicENIpHAlIMpLIVEnoDErbAblEcAshiNeUrGA");
    }
}
