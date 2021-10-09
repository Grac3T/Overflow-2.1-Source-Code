// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.user.global;

import java.util.Iterator;
import us.overflow.Overflow;

public class GlobalUserManager
{
    public GlobalObject getUser(final String uuid) {
        for (final GlobalObject user : Overflow.getInstance().getUsers()) {
            if (user.getUuid().equalsIgnoreCase(uuid)) {
                return user;
            }
        }
        return null;
    }
    
    public void addUser(final GlobalObject user) {
        if (!Overflow.getInstance().getUsers().contains(user)) {
            Overflow.getInstance().getUsers().add(user);
        }
    }
    
    public void removeUser(final GlobalObject user) {
        if (Overflow.getInstance().getUsers().contains(user)) {
            Overflow.getInstance().getUsers().remove(user);
        }
    }
}
