// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.User;
import java.io.Serializable;

public class FollowPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -4345668254608800406L;
    private User target;
    
    public User getTarget() {
        return this.target;
    }
    
    public FollowPayload setTarget(final User target) {
        this.target = target;
        return this;
    }
}
