// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.claszz;

public class ClassHelper
{
    public static void callClass(final String p) {
        try {
            final Class clazz = Class.forName(p);
            clazz.newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
        }
    }
}
