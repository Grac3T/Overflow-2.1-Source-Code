// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class RepositoryBranch implements Serializable
{
    private static final long serialVersionUID = 4927461901146433920L;
    private String name;
    private TypedResource commit;
    
    public String getName() {
        return this.name;
    }
    
    public RepositoryBranch setName(final String name) {
        this.name = name;
        return this;
    }
    
    public TypedResource getCommit() {
        return this.commit;
    }
    
    public RepositoryBranch setCommit(final TypedResource commit) {
        this.commit = commit;
        return this;
    }
}
