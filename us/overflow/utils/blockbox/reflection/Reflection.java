// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.reflection;

import org.bukkit.Bukkit;
import java.util.regex.Matcher;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

public final class Reflection
{
    public static String OBC_PREFIX;
    public static String NMS_PREFIX;
    public static String VERSION;
    private static Pattern MATCH_VARIABLE;
    
    private Reflection() {
    }
    
    public static <T> FieldAccessor<T> getField(final Class<?> target, final String name, final Class<T> fieldType) {
        return (FieldAccessor<T>)getField((Class)target, name, (Class)fieldType, 0);
    }
    
    public static <T> FieldAccessor<T> getField(final String className, final String name, final Class<T> fieldType) {
        return (FieldAccessor<T>)getField(getClass(className), name, (Class)fieldType, 0);
    }
    
    public static <T> FieldAccessor<T> getField(final Class<?> target, final Class<T> fieldType, final int index) {
        return (FieldAccessor<T>)getField((Class)target, (String)null, (Class)fieldType, index);
    }
    
    public static <T> FieldAccessor<T> getField(final String className, final Class<T> fieldType, final int index) {
        return (FieldAccessor<T>)getField(getClass(className), (Class)fieldType, index);
    }
    
    public static <T> FieldAccessor<T> getFieldSafe(final String className, final Class<T> fieldType, final int index) {
        try {
            return (FieldAccessor<T>)getField(getClass(className), (Class)fieldType, index);
        }
        catch (Exception e) {
            System.out.println("[WARN] Failed to find field at " + index + " in " + className + " with type " + fieldType.getSimpleName());
            return null;
        }
    }
    
    private static <T> FieldAccessor<T> getField(final Class<?> target, final String name, final Class<T> fieldType, int index) {
        for (final Field[] array2 : new Field[][] { target.getDeclaredFields(), target.getFields() }) {
            final Field[] fields = array2;
            for (final Field field : array2) {
                if ((name == null || field.getName().equals(name)) && fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
                    field.setAccessible(true);
                    return (FieldAccessor<T>)new Reflection$1(field);
                }
            }
        }
        if (target.getSuperclass() != null) {
            return (FieldAccessor<T>)getField((Class)target.getSuperclass(), name, (Class)fieldType, index);
        }
        throw new IllegalArgumentException("Cannot find field with type " + fieldType);
    }
    
    public static <T> FieldAccessor<T> getField(final Class<?> target, final String name, int index) {
        for (final Field[] array2 : new Field[][] { target.getDeclaredFields(), target.getFields() }) {
            final Field[] fields = array2;
            for (final Field field : array2) {
                if ((name == null || field.getName().equals(name)) && index-- <= 0) {
                    field.setAccessible(true);
                    return (FieldAccessor<T>)new Reflection$2(field);
                }
            }
        }
        if (target.getSuperclass() != null) {
            return (FieldAccessor<T>)getField((Class)target.getSuperclass(), name, index);
        }
        throw new IllegalArgumentException("Cannot find field");
    }
    
    public static MethodInvoker getMethod(final String className, final String methodName, final Class<?>... params) {
        return getTypedMethod(getClass(className), methodName, (Class)null, (Class[])params);
    }
    
    public static MethodInvoker getMethod(final Class<?> clazz, final String methodName, final Class<?>... params) {
        return getTypedMethod((Class)clazz, methodName, (Class)null, (Class[])params);
    }
    
    public static MethodInvoker getMethod(final Class<?> clazz, final int index, final Class<?>... params) {
        return getTypedMethod((Class)clazz, index, (Class)null, (Class[])params);
    }
    
    public static MethodInvoker getMethod(final Class<?> clazz, final Class<?> returnType, final int index, final Class<?>... params) {
        return getTypedMethod((Class)clazz, index, (Class)returnType, (Class[])params);
    }
    
    public static MethodInvoker getTypedMethod(final Class<?> clazz, final String methodName, final Class<?> returnType, final Class<?>... params) {
        for (final Method method : clazz.getDeclaredMethods()) {
            if ((methodName == null || method.getName().equals(methodName)) && (returnType == null || method.getReturnType().equals(returnType)) && Arrays.equals(method.getParameterTypes(), params)) {
                method.setAccessible(true);
                return (MethodInvoker)new Reflection$3(method);
            }
        }
        if (clazz.getSuperclass() != null) {
            return getMethod((Class)clazz.getSuperclass(), methodName, (Class[])params);
        }
        throw new IllegalStateException(String.format("Unable to find method %s (%s).", methodName, Arrays.asList(params)));
    }
    
    public static MethodInvoker getTypedMethod(final Class<?> clazz, int index, final Class<?> returnType, final Class<?>... params) {
        for (final Method method : clazz.getDeclaredMethods()) {
            if ((returnType == null || method.getReturnType().equals(returnType)) && Arrays.equals(method.getParameterTypes(), params) && index-- <= 0) {
                method.setAccessible(true);
                return (MethodInvoker)new Reflection$4(method);
            }
        }
        if (clazz.getSuperclass() != null) {
            return getMethod((Class)clazz.getSuperclass(), index, (Class[])params);
        }
        throw new IllegalStateException(String.format("Unable to find method %s (%s).", index, Arrays.asList(params)));
    }
    
    public static ConstructorInvoker getConstructor(final String className, final Class<?>... params) {
        return getConstructor(getClass(className), (Class[])params);
    }
    
    public static ConstructorInvoker getConstructor(final Class<?> clazz, final Class<?>... params) {
        for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (Arrays.equals(constructor.getParameterTypes(), params)) {
                constructor.setAccessible(true);
                return (ConstructorInvoker)new Reflection$5((Constructor)constructor);
            }
        }
        throw new IllegalStateException(String.format("Unable to find constructor for %s (%s).", clazz, Arrays.asList(params)));
    }
    
    public static Class<Object> getUntypedClass(final String lookupName) {
        final Class<Object> clazz = (Class<Object>)getClass(lookupName);
        return clazz;
    }
    
    public static Class<?> getClass(final String lookupName) {
        return (Class<?>)getCanonicalClass(expandVariables(lookupName));
    }
    
    public static Class<?> getMinecraftClass(final String name) {
        return (Class<?>)getCanonicalClass(Reflection.NMS_PREFIX + "." + name);
    }
    
    public static Class<?> getCraftBukkitClass(final String name) {
        return (Class<?>)getCanonicalClass(Reflection.OBC_PREFIX + "." + name);
    }
    
    private static Class<?> getCanonicalClass(final String canonicalName) {
        try {
            return Class.forName(canonicalName);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot find " + canonicalName, e);
        }
    }
    
    private static String expandVariables(final String name) {
        final StringBuffer output = new StringBuffer();
        final Matcher matcher = Reflection.MATCH_VARIABLE.matcher(name);
        while (matcher.find()) {
            final String variable = matcher.group(1);
            String replacement = "";
            if ("nms".equalsIgnoreCase(variable)) {
                replacement = Reflection.NMS_PREFIX;
            }
            else if ("obc".equalsIgnoreCase(variable)) {
                replacement = Reflection.OBC_PREFIX;
            }
            else {
                if (!"version".equalsIgnoreCase(variable)) {
                    throw new IllegalArgumentException("Unknown variable: " + variable);
                }
                replacement = Reflection.VERSION;
            }
            if (replacement.length() > 0 && matcher.end() < name.length() && name.charAt(matcher.end()) != '.') {
                replacement += ".";
            }
            matcher.appendReplacement(output, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(output);
        return output.toString();
    }
    
    static {
        Reflection.OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
        Reflection.NMS_PREFIX = Reflection.OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");
        Reflection.VERSION = Reflection.OBC_PREFIX.replace("org.bukkit.craftbukkit", "").replace(".", "");
        Reflection.MATCH_VARIABLE = Pattern.compile("\\{([^\\}]+)\\}");
    }
}
