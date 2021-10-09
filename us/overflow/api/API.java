// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.api;

import java.util.Iterator;
import us.overflow.base.user.User;
import us.overflow.Overflow;
import org.bukkit.entity.Player;

public class API
{
    public void resetViolation(final Player player) {
        final User user = Overflow.getInstance().getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            user.setViolation(0);
            user.setViolationSplit(0);
            user.setVioSplit(0);
        }
    }
    
    public void setViolation(final Player player, final int violation) {
        final User user = Overflow.getInstance().getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            user.setViolation(violation);
        }
    }
    
    public int getRegisterUsers() {
        return Overflow.getInstance().getUserManager().getUsers().size();
    }
    
    public int getPing(final Player player) {
        final User user = Overflow.getInstance().getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            return user.getPing();
        }
        return -1;
    }
    
    public boolean isPlayerRegistered(final Player player) {
        for (final User user : Overflow.getInstance().getUserManager().getUsers()) {
            if (user.getPlayer().getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                return true;
            }
        }
        return false;
    }
}
