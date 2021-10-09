// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

import java.util.Collection;
import java.util.LinkedList;

public final class EvictingList<T> extends LinkedList<T>
{
    private int maxSize;
    
    public EvictingList(final int maxSize) {
        this.maxSize = maxSize;
    }
    
    public EvictingList(final Collection<? extends T> c, final int maxSize) {
        super(c);
        this.maxSize = maxSize;
    }
    
    public int getMaxSize() {
        return this.maxSize;
    }
    
    @Override
    public boolean add(final T t) {
        if (this.size() >= this.getMaxSize()) {
            this.removeFirst();
        }
        return super.add(t);
    }
}
