// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.reflections.types;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Method;

public class WrappedMethod
{
    private final WrappedClass parent;
    private final Method method;
    private final String name;
    private final List<Class<?>> parameters;
    
    public WrappedMethod(final WrappedClass parent, final Method method) {
        this.parent = parent;
        this.method = method;
        this.name = method.getName();
        this.parameters = Arrays.asList(method.getParameterTypes());
    }
    
    public <T> T invoke(final Object object, final Object... args) {
        try {
            return (T)this.method.invoke(object, args);
        }
        catch (IllegalAccessException | InvocationTargetException ex) {
            return null;
        }
    }
    
    public int getModifiers() {
        return this.method.getModifiers();
    }
    
    public WrappedClass getParent() {
        return this.parent;
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Class<?>> getParameters() {
        return (List<Class<?>>)this.parameters;
    }
}
