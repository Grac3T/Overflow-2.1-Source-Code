// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import java.io.Serializable;

public class CreatePayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -7033027645721954674L;
    private String refType;
    private String ref;
    private String masterBranch;
    private String description;
    
    public String getRefType() {
        return this.refType;
    }
    
    public CreatePayload setRefType(final String refType) {
        this.refType = refType;
        return this;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public CreatePayload setRef(final String ref) {
        this.ref = ref;
        return this;
    }
    
    public String getMasterBranch() {
        return this.masterBranch;
    }
    
    public CreatePayload setMasterBranch(final String masterBranch) {
        this.masterBranch = masterBranch;
        return this;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public CreatePayload setDescription(final String description) {
        this.description = description;
        return this;
    }
}
