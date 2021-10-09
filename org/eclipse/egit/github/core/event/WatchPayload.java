// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import java.io.Serializable;

public class WatchPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -1600566006173513492L;
    private String action;
    
    public String getAction() {
        return this.action;
    }
    
    public WatchPayload setAction(final String action) {
        this.action = action;
        return this;
    }
}
