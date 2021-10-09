// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import java.io.Serializable;

public class DeletePayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -7571623946339106873L;
    private String refType;
    private String ref;
    
    public String getRefType() {
        return this.refType;
    }
    
    public DeletePayload setRefType(final String refType) {
        this.refType = refType;
        return this;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public DeletePayload setRef(final String ref) {
        this.ref = ref;
        return this;
    }
}
