// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

import us.overflow.Overflow;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.plugin.Plugin;

public class RunUtils
{
    public static BukkitTask taskTimer(final Runnable runnable, final Plugin plugin, final long delay, final long interval) {
        return Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, interval);
    }
    
    public static BukkitTask taskTimer(final Runnable runnable, final long delay, final long interval) {
        return taskTimer(runnable, (Plugin)Overflow.getInstance(), delay, interval);
    }
    
    public static BukkitTask taskTimerAsync(final Runnable runnable, final Plugin plugin, final long delay, final long interval) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, interval);
    }
    
    public static BukkitTask taskTimerAsync(final Runnable runnable, final long delay, final long interval) {
        return taskTimerAsync(runnable, (Plugin)Overflow.getInstance(), delay, interval);
    }
    
    public static BukkitTask task(final Runnable runnable, final Plugin plugin) {
        return Bukkit.getScheduler().runTask(plugin, runnable);
    }
    
    public static BukkitTask task(final Runnable runnable) {
        return task(runnable, (Plugin)Overflow.getInstance());
    }
    
    public static BukkitTask taskAsync(final Runnable runnable, final Plugin plugin) {
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }
    
    public static BukkitTask taskAsync(final Runnable runnable) {
        return taskAsync(runnable, (Plugin)Overflow.getInstance());
    }
    
    public static BukkitTask taskLater(final Runnable runnable, final Plugin plugin, final long delay) {
        return Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }
    
    public static BukkitTask taskLater(final Runnable runnable, final long delay) {
        return taskLater(runnable, (Plugin)Overflow.getInstance(), delay);
    }
    
    public static BukkitTask taskLaterAsync(final Runnable runnable, final Plugin plugin, final long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }
    
    public static BukkitTask taskLaterAsync(final Runnable runnable, final long delay) {
        return taskLaterAsync(runnable, (Plugin)Overflow.getInstance(), delay);
    }
}
