// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class MergeStatus implements Serializable
{
    private static final long serialVersionUID = 2003332803236436488L;
    private boolean merged;
    private String sha;
    private String message;
    
    public boolean isMerged() {
        return this.merged;
    }
    
    public MergeStatus setMerged(final boolean merged) {
        this.merged = merged;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public MergeStatus setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public MergeStatus setMessage(final String message) {
        this.message = message;
        return this;
    }
}
