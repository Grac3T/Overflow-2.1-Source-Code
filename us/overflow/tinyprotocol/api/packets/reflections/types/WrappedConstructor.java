// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.reflections.types;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

public class WrappedConstructor
{
    private final WrappedClass parent;
    private Constructor constructor;
    
    public <T> T newInstance(final Object... args) {
        try {
            this.constructor.setAccessible(true);
            return this.constructor.newInstance(args);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public <T> T newInstance() {
        try {
            this.constructor.setAccessible(true);
            return this.constructor.newInstance(new Object[0]);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public WrappedConstructor(final WrappedClass parent) {
        this.parent = parent;
    }
    
    public WrappedConstructor(final WrappedClass parent, final Constructor constructor) {
        this.parent = parent;
        this.constructor = constructor;
    }
    
    public WrappedClass getParent() {
        return this.parent;
    }
    
    public Constructor getConstructor() {
        return this.constructor;
    }
}
