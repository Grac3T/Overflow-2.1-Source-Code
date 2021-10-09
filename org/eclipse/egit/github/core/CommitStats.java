// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class CommitStats implements Serializable
{
    private static final long serialVersionUID = -4398346227258322028L;
    private int additions;
    private int deletions;
    private int total;
    
    public int getAdditions() {
        return this.additions;
    }
    
    public CommitStats setAdditions(final int additions) {
        this.additions = additions;
        return this;
    }
    
    public int getDeletions() {
        return this.deletions;
    }
    
    public CommitStats setDeletions(final int deletions) {
        this.deletions = deletions;
        return this;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public CommitStats setTotal(final int total) {
        this.total = total;
        return this;
    }
}
