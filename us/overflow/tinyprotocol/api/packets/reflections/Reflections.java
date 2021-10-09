// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.reflections;

import org.bukkit.Bukkit;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;

public class Reflections
{
    private static final String craftBukkitString;
    private static final String netMinecraftServerString;
    
    public static boolean classExists(final String name) {
        try {
            Class.forName(name);
            return true;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    public static WrappedClass getCBClass(final String name) {
        return getClass(Reflections.craftBukkitString + name);
    }
    
    public static WrappedClass getNMSClass(final String name) {
        return getClass(Reflections.netMinecraftServerString + name);
    }
    
    public static WrappedClass getClass(final String name) {
        try {
            return new WrappedClass((Class)Class.forName(name));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static WrappedClass getClass(final Class clazz) {
        return new WrappedClass(clazz);
    }
    
    static {
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        Reflections.craftBukkitString = "org.bukkit.craftbukkit." + version + ".";
        Reflections.netMinecraftServerString = "net.minecraft.server." + version + ".";
    }
}
