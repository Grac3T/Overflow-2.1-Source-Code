// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base;

import java.io.IOException;
import org.bukkit.ChatColor;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import us.overflow.Overflow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeecordListener implements PluginMessageListener
{
    private long last;
    
    public void onPluginMessageReceived(final String s, final Player player, final byte[] bytes) {
        if (Overflow.getInstance().getConfigValues().isBungeecordEnabled()) {
            final DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
            try {
                if (System.currentTimeMillis() - this.last > 50L) {
                    final String cmd = in.readUTF();
                    final String[] stringArray = cmd.split(":");
                    final String playerName = stringArray[0];
                    final String checkName = stringArray[1];
                    final String checkType = stringArray[2];
                    final String violation = stringArray[3];
                    final String serverName = stringArray[4];
                    final boolean experimental = Boolean.parseBoolean(stringArray[5]);
                    String alert = Overflow.getInstance().getConfigValues().getAlertMessage().replace("%PLAYER%", playerName).replace("%CHECK%", checkName).replace("%TYPE%", checkType).replace("%VL%", violation).replace("%PREFIX%", Overflow.getInstance().getConfigValues().getPrefix()).replace("%SERVER_NAME%", serverName) + (experimental ? (ChatColor.RED + " (EXP)") : "");
                    if (Overflow.getInstance().getConfigValues().isAppendBungeecordServerName()) {
                        alert = alert + ChatColor.RED + " (" + ChatColor.GRAY + serverName + ChatColor.RED + ")";
                    }
                    Check.sendAlert(alert);
                    this.last = System.currentTimeMillis();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
