// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events;

import java.lang.reflect.Method;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedMethod;
import org.bukkit.plugin.Plugin;

class ListenerMethod
{
    public Plugin plugin;
    public WrappedMethod method;
    public WrappedClass listenerClass;
    public OverflowListener listener;
    public ListenerPriority listenerPriority;
    public String className;
    public boolean ignoreCancelled;
    
    public ListenerMethod(final Plugin plugin, final Method method, final OverflowListener listener, final ListenerPriority listenerPriority) {
        this.plugin = plugin;
        this.listenerClass = new WrappedClass((Class)listener.getClass());
        this.method = new WrappedMethod(this.listenerClass, method);
        this.listener = listener;
        this.listenerPriority = listenerPriority;
        this.ignoreCancelled = method.getAnnotation(Listen.class).ignoreCancelled();
        this.className = method.getParameterTypes()[0].getName();
    }
}
