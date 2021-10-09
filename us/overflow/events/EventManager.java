// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events;

import java.lang.annotation.Annotation;
import us.overflow.Overflow;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Comparator;
import us.overflow.events.exceptions.ListenParamaterException;
import org.bukkit.plugin.Plugin;
import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class EventManager
{
    private final List<ListenerMethod> listenerMethods;
    public boolean paused;
    
    public EventManager() {
        this.listenerMethods = new CopyOnWriteArrayList();
        this.paused = false;
    }
    
    public void registerListener(final Method method, final OverflowListener listener, final Plugin plugin) throws ListenParamaterException {
        if (method.getParameterTypes().length != 1) {
            throw new ListenParamaterException("Method " + method.getDeclaringClass().getName() + "#" + method.getName() + " has an invalid amount of paramters (count=" + method.getParameterTypes().length + ")!");
        }
        if (method.getParameterTypes()[0].getSuperclass().equals(OverflowEvent.class)) {
            final Listen listen = method.getAnnotation(Listen.class);
            final ListenerMethod lm = new ListenerMethod(plugin, method, listener, listen.priority());
            if (!listen.priority().equals((Object)ListenerPriority.NONE)) {
                lm.listenerPriority = listen.priority();
            }
            this.listenerMethods.add(lm);
            this.listenerMethods.sort(Comparator.comparing((Function<? super E, ?>)EventManager::lambda$registerListener$0, Comparator.reverseOrder()));
            return;
        }
        throw new ListenParamaterException("Method " + method.getDeclaringClass().getName() + "#" + method.getName() + "'s paramater: " + method.getParameterTypes()[0].getName() + " is not an instanceof " + OverflowEvent.class.getSimpleName() + "!");
    }
    
    public void clearAllRegistered() {
        this.listenerMethods.clear();
    }
    
    public void unregisterAll(final Plugin plugin) {
        this.listenerMethods.stream().filter(EventManager::lambda$unregisterAll$1).forEach(this.listenerMethods::remove);
    }
    
    public void unregisterListener(final OverflowListener listener) {
        this.listenerMethods.stream().filter(EventManager::lambda$unregisterListener$2).forEach(this.listenerMethods::remove);
    }
    
    public void registerListeners(final OverflowListener listener, final Plugin plugin) {
        Arrays.stream(listener.getClass().getMethods()).filter(EventManager::lambda$registerListeners$3).forEach(this::lambda$registerListeners$4);
    }
    
    public void callEvent(final OverflowEvent event) {
        if (!this.paused && event != null) {
            final List<ListenerMethod> methods = ((Stream)this.listenerMethods.parallelStream().filter(EventManager::lambda$callEvent$5).sequential()).sorted(Comparator.comparing((Function<? super Object, ?>)EventManager::lambda$callEvent$6, Comparator.reverseOrder())).collect((Collector<? super Object, ?, List<ListenerMethod>>)Collectors.toList());
            if (event instanceof Cancellable) {
                final Cancellable cancellable = (Cancellable)event;
                for (final ListenerMethod lm : methods) {
                    if (!cancellable.isCancelled() || !lm.ignoreCancelled) {
                        lm.method.invoke((Object)lm.listener, new Object[] { cancellable });
                    }
                }
            }
        }
    }
    
    public void callEventAsync(final OverflowEvent event) {
        Overflow.getInstance().getExecutorService().execute(this::lambda$callEventAsync$7);
    }
    
    public List<ListenerMethod> getListenerMethods() {
        return (List<ListenerMethod>)this.listenerMethods;
    }
    
    public boolean isPaused() {
        return this.paused;
    }
}
