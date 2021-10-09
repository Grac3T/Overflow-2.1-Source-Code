// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

public class OSUtils
{
    public static OS getPlatform() {
        final String s = System.getProperty("os.name").toLowerCase();
        return s.contains("win") ? OS.WINDOWS : (s.contains("mac") ? OS.MACOS : (s.contains("solaris") ? OS.SOLARIS : (s.contains("sunos") ? OS.SOLARIS : (s.contains("linux") ? OS.LINUX : (s.contains("unix") ? OS.LINUX : OS.UNKNOWN)))));
    }
    
    public enum OS
    {
        public static final OS LINUX;
        public static final OS SOLARIS;
        public static final OS WINDOWS;
        public static final OS MACOS;
        public static final OS UNKNOWN;
        
        public static OS valueOf(final String name) {
            return Enum.valueOf(OS.class, name);
        }
        
        static {
            OS.LINUX = new OS("LINUX", 0);
            OS.SOLARIS = new OS("SOLARIS", 1);
            OS.WINDOWS = new OS("WINDOWS", 2);
            OS.MACOS = new OS("MACOS", 3);
            OS.UNKNOWN = new OS("UNKNOWN", 4);
            OS.$VALUES = new OS[] { OS.LINUX, OS.SOLARIS, OS.WINDOWS, OS.MACOS, OS.UNKNOWN };
        }
    }
}
