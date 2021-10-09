// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.reflections.types;

import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.function.Function;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import java.util.function.Predicate;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class WrappedClass
{
    private final Class parent;
    
    public WrappedClass(final Class parent) {
        this.parent = parent;
    }
    
    public WrappedField getFieldByName(final String name) {
        Field tempField = null;
        for (final Field field : this.parent.getDeclaredFields()) {
            if (field.getName().equals(name)) {
                tempField = field;
                break;
            }
        }
        if (tempField != null) {
            tempField.setAccessible(true);
            return new WrappedField(this, tempField);
        }
        return null;
    }
    
    public WrappedConstructor getConstructor(final Class... types) {
        try {
            return new WrappedConstructor(this, (Constructor)this.parent.getDeclaredConstructor((Class[])types));
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<WrappedField> getFields(final Predicate<WrappedField>... parameters) {
        return (List<WrappedField>)this.getFields().stream().filter(WrappedClass::lambda$getFields$1).collect(Collectors.toList());
    }
    
    public List<WrappedMethod> getMethods(final Predicate<WrappedMethod>... parameters) {
        return (List<WrappedMethod>)this.getMethods().stream().filter(WrappedClass::lambda$getMethods$3).collect(Collectors.toList());
    }
    
    public List<WrappedConstructor> getConstructors() {
        return Arrays.stream(this.parent.getConstructors()).map((Function<? super Constructor, ?>)this::lambda$getConstructors$4).collect((Collector<? super Object, ?, List<WrappedConstructor>>)Collectors.toList());
    }
    
    public WrappedConstructor getConstructor() {
        final Optional<Constructor> optional = (Optional<Constructor>)Arrays.stream(this.parent.getConstructors()).filter(WrappedClass::lambda$getConstructor$5).findFirst();
        return optional.map((Function<? super Constructor, ? extends WrappedConstructor>)this::lambda$getConstructor$6).orElse(null);
    }
    
    public WrappedConstructor getConstructorAtIndex(final int index) {
        return new WrappedConstructor(this, (Constructor)this.parent.getConstructors()[index]);
    }
    
    public boolean isAnnotationPresent(final Class<? extends Annotation> annClass) {
        return this.parent.isAnnotationPresent(annClass);
    }
    
    public <T> T getAnnotation(final Class<T> annClass) {
        return this.parent.getDeclaredAnnotation(annClass);
    }
    
    public WrappedField getFieldByType(final Class<?> type, int index) {
        for (final Field field : this.parent.getDeclaredFields()) {
            if (field.getType().equals(type) && index-- <= 0) {
                return new WrappedField(this, field);
            }
        }
        throw new NullPointerException("Could not find field with type " + type.getSimpleName() + " at index " + index);
    }
    
    public WrappedField getFirstFieldByType(final Class<?> type) {
        return this.getFieldByType(type, 0);
    }
    
    public WrappedMethod getMethod(final String name, final Class... parameters) {
        for (final Method method : this.parent.getDeclaredMethods()) {
            if (method.getName().equals(name)) {
                if (parameters.length == method.getParameterTypes().length) {
                    boolean same = true;
                    for (int x = 0; x < method.getParameterTypes().length; ++x) {
                        if (method.getParameterTypes()[x] != parameters[x]) {
                            same = false;
                            break;
                        }
                    }
                    if (same) {
                        return new WrappedMethod(this, method);
                    }
                }
            }
        }
        for (final Method method : this.parent.getMethods()) {
            if (method.getName().equals(name)) {
                if (parameters.length == method.getParameterTypes().length) {
                    boolean same = true;
                    for (int x = 0; x < method.getParameterTypes().length; ++x) {
                        if (method.getParameterTypes()[x] != parameters[x]) {
                            same = false;
                            break;
                        }
                    }
                    if (same) {
                        return new WrappedMethod(this, method);
                    }
                }
            }
        }
        return null;
    }
    
    public WrappedMethod getDeclaredMethodByType(final Class<?> type, int index) {
        for (final Method method : this.parent.getDeclaredMethods()) {
            if (method.getReturnType().equals(type) && index-- <= 0) {
                return new WrappedMethod(this, method);
            }
        }
        throw new NullPointerException("Could not find method with return type " + type.getSimpleName() + " at index " + index);
    }
    
    public WrappedMethod getMethodByType(final Class<?> type, int index) throws NullPointerException {
        for (final Method method : this.parent.getMethods()) {
            if (method.getReturnType().equals(type) && index-- <= 0) {
                return new WrappedMethod(this, method);
            }
        }
        System.out.println("Shit didnt get: " + type.getName());
        throw new NullPointerException("Could not find method with return type " + type.getSimpleName() + " at index " + index);
    }
    
    public List<WrappedMethod> getMethods() {
        return Arrays.stream(this.parent.getMethods()).map((Function<? super Method, ?>)this::lambda$getMethods$7).collect((Collector<? super Object, ?, List<WrappedMethod>>)Collectors.toList());
    }
    
    public List<WrappedMethod> getMethods(final boolean noStatic, final boolean noFinal) {
        return Arrays.stream(this.parent.getMethods()).filter(WrappedClass::lambda$getMethods$8).map((Function<? super Method, ?>)this::lambda$getMethods$9).collect((Collector<? super Object, ?, List<WrappedMethod>>)Collectors.toList());
    }
    
    public List<WrappedMethod> getMethods(final boolean noStatic) {
        return (List<WrappedMethod>)this.getMethods(noStatic, false);
    }
    
    public List<WrappedField> getFields() {
        return Arrays.stream(this.parent.getDeclaredFields()).map((Function<? super Field, ?>)this::lambda$getFields$10).collect((Collector<? super Object, ?, List<WrappedField>>)Collectors.toList());
    }
    
    public List<WrappedField> getFields(final boolean noStatic, final boolean noFinal) {
        return Arrays.stream(this.parent.getFields()).filter(WrappedClass::lambda$getFields$11).map((Function<? super Field, ?>)this::lambda$getFields$12).collect((Collector<? super Object, ?, List<WrappedField>>)Collectors.toList());
    }
    
    public List<WrappedField> getFields(final boolean noStatic) {
        return (List<WrappedField>)this.getFields(noStatic, false);
    }
    
    public Enum getEnum(final String name) {
        return Enum.valueOf((Class<Enum>)this.parent, name);
    }
    
    public Class getParent() {
        return this.parent;
    }
}
