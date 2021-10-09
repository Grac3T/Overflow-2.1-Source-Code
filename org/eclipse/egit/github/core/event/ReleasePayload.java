// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Release;
import java.io.Serializable;

public class ReleasePayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 3309944674574815351L;
    private String action;
    private Release release;
    
    public String getAction() {
        return this.action;
    }
    
    public ReleasePayload setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public Release getRelease() {
        return this.release;
    }
    
    public ReleasePayload setRelease(final Release release) {
        this.release = release;
        return this;
    }
}
