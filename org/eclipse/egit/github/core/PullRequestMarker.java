// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class PullRequestMarker implements Serializable
{
    private static final long serialVersionUID = 5052026861072656918L;
    private Repository repo;
    private String label;
    private String ref;
    private String sha;
    private User user;
    
    public Repository getRepo() {
        return this.repo;
    }
    
    public PullRequestMarker setRepo(final Repository repo) {
        this.repo = repo;
        return this;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public PullRequestMarker setLabel(final String label) {
        this.label = label;
        return this;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public PullRequestMarker setRef(final String ref) {
        this.ref = ref;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public PullRequestMarker setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public PullRequestMarker setUser(final User user) {
        this.user = user;
        return this;
    }
}
