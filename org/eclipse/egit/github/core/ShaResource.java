// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class ShaResource implements Serializable
{
    private static final long serialVersionUID = 7029184412278953778L;
    private String sha;
    
    public String getSha() {
        return this.sha;
    }
    
    public ShaResource setSha(final String sha) {
        this.sha = sha;
        return this;
    }
}
