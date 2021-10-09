// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Gist;
import java.io.Serializable;

public class GistPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 8916400800708594462L;
    private String action;
    private Gist gist;
    
    public String getAction() {
        return this.action;
    }
    
    public GistPayload setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public Gist getGist() {
        return this.gist;
    }
    
    public GistPayload setGist(final Gist gist) {
        this.gist = gist;
        return this;
    }
}
