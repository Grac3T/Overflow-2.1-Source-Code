// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.user;

import java.util.Iterator;
import java.util.UUID;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class UserManager
{
    public static List<User> Users;
    
    public UserManager() {
        UserManager.Users = Collections.synchronizedList(new ArrayList<User>());
    }
    
    public User getUser(final UUID uuid) {
        for (final User user : UserManager.Users) {
            if (user.getPlayer().getUniqueId() == uuid || user.getPlayer().getUniqueId().equals(uuid)) {
                return user;
            }
        }
        return null;
    }
    
    public void addUser(final User user) {
        if (!UserManager.Users.contains(user) && System.currentTimeMillis() - user.lastJoin < 100L) {
            UserManager.Users.add(user);
        }
    }
    
    public void removeUser(final User user) {
        if (UserManager.Users.contains(user)) {
            UserManager.Users.remove(user);
        }
    }
    
    public List<User> getUsers() {
        return UserManager.Users;
    }
}
