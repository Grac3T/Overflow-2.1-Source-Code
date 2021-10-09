// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Rename implements Serializable
{
    private static final long serialVersionUID = -4700399891066053425L;
    private String from;
    private String to;
    
    public String getFrom() {
        return this.from;
    }
    
    public Rename setFrom(final String from) {
        this.from = from;
        return this;
    }
    
    public String getTo() {
        return this.to;
    }
    
    public Rename setTo(final String to) {
        this.to = to;
        return this;
    }
}
