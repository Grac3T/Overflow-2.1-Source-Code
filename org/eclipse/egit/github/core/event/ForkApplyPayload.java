// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import java.io.Serializable;

public class ForkApplyPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -7527740351672699770L;
    private String head;
    private String before;
    private String after;
    
    public String getHead() {
        return this.head;
    }
    
    public ForkApplyPayload setHead(final String head) {
        this.head = head;
        return this;
    }
    
    public String getBefore() {
        return this.before;
    }
    
    public ForkApplyPayload setBefore(final String before) {
        this.before = before;
        return this;
    }
    
    public String getAfter() {
        return this.after;
    }
    
    public ForkApplyPayload setAfter(final String after) {
        this.after = after;
        return this;
    }
}
