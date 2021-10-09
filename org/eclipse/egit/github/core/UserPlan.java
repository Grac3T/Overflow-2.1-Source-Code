// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class UserPlan implements Serializable
{
    private static final long serialVersionUID = 4759542049129654659L;
    private long collaborators;
    private long privateRepos;
    private long space;
    private String name;
    
    public long getCollaborators() {
        return this.collaborators;
    }
    
    public UserPlan setCollaborators(final long collaborators) {
        this.collaborators = collaborators;
        return this;
    }
    
    public long getPrivateRepos() {
        return this.privateRepos;
    }
    
    public UserPlan setPrivateRepos(final long privateRepos) {
        this.privateRepos = privateRepos;
        return this;
    }
    
    public long getSpace() {
        return this.space;
    }
    
    public UserPlan setSpace(final long space) {
        this.space = space;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public UserPlan setName(final String name) {
        this.name = name;
        return this;
    }
}
