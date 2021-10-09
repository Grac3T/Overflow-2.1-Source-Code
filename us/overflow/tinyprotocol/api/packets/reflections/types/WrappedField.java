// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.reflections.types;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class WrappedField
{
    private final WrappedClass parent;
    private final Field field;
    private final Class<?> type;
    
    public WrappedField(final WrappedClass parent, final Field field) {
        this.parent = parent;
        this.field = field;
        this.type = field.getType();
        this.field.setAccessible(true);
    }
    
    public <T> T get(final Object parent) {
        try {
            return (T)this.field.get(parent);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void set(final Object parent, final Object value) {
        try {
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(this.field, this.field.getModifiers() & 0xFFFFFFEF);
            this.field.setAccessible(true);
            this.field.set(parent, value);
        }
        catch (IllegalAccessException | NoSuchFieldException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
        }
    }
    
    public boolean isAnnotationPresent(final Class<? extends Annotation> annClass) {
        return this.field.isAnnotationPresent(annClass);
    }
    
    public <T> T getAnnotation(final Class<? extends Annotation> annClass) {
        return this.field.getAnnotation((Class<T>)annClass);
    }
    
    public int getModifiers() {
        return this.field.getModifiers();
    }
    
    public WrappedClass getParent() {
        return this.parent;
    }
    
    public Field getField() {
        return this.field;
    }
    
    public Class<?> getType() {
        return (Class<?>)this.type;
    }
}
