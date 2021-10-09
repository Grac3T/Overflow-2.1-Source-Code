// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.reflection;

public interface FieldAccessor<T>
{
    T get(final Object p0);
    
    void set(final Object p0, final Object p1);
    
    boolean hasField(final Object p0);
}
