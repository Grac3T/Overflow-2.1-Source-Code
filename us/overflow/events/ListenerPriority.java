// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events;

public enum ListenerPriority
{
    public static final ListenerPriority NONE;
    public static final ListenerPriority LOWEST;
    public static final ListenerPriority LOW;
    public static final ListenerPriority NORMAL;
    public static final ListenerPriority HIGH;
    public static final ListenerPriority HIGHEST;
    private int priority;
    
    public static ListenerPriority valueOf(final String name) {
        return Enum.valueOf(ListenerPriority.class, name);
    }
    
    private ListenerPriority(final int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    static {
        ListenerPriority.NONE = new ListenerPriority("NONE", 0, 2);
        ListenerPriority.LOWEST = new ListenerPriority("LOWEST", 1, 0);
        ListenerPriority.LOW = new ListenerPriority("LOW", 2, 1);
        ListenerPriority.NORMAL = new ListenerPriority("NORMAL", 3, 2);
        ListenerPriority.HIGH = new ListenerPriority("HIGH", 4, 3);
        ListenerPriority.HIGHEST = new ListenerPriority("HIGHEST", 5, 4);
        ListenerPriority.$VALUES = new ListenerPriority[] { ListenerPriority.NONE, ListenerPriority.LOWEST, ListenerPriority.LOW, ListenerPriority.NORMAL, ListenerPriority.HIGH, ListenerPriority.HIGHEST };
    }
}
