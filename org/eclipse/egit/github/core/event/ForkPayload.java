// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Repository;
import java.io.Serializable;

public class ForkPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 2110456722558520113L;
    private Repository forkee;
    
    public Repository getForkee() {
        return this.forkee;
    }
    
    public ForkPayload setForkee(final Repository forkee) {
        this.forkee = forkee;
        return this;
    }
}
