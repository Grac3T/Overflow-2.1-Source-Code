// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.User;
import java.io.Serializable;

public class MemberPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -4261757812093447848L;
    private User member;
    private String action;
    
    public User getMember() {
        return this.member;
    }
    
    public MemberPayload setMember(final User member) {
        this.member = member;
        return this;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public MemberPayload setAction(final String action) {
        this.action = action;
        return this;
    }
}
