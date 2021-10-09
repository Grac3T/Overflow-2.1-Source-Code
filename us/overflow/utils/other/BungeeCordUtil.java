// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

import org.bukkit.plugin.Plugin;
import us.overflow.Overflow;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class BungeeCordUtil
{
    public static void sendBungeecordMessage(final String s) {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF(s);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        Bukkit.getServer().sendPluginMessage((Plugin)Overflow.getInstance(), "OverflowAlerts", b.toByteArray());
    }
}
