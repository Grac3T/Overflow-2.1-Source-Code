// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

public class RepositoryIssue extends Issue
{
    private static final long serialVersionUID = 6219926097588214812L;
    private Repository repository;
    
    public Repository getRepository() {
        return this.repository;
    }
    
    public RepositoryIssue setRepository(final Repository repository) {
        this.repository = repository;
        return this;
    }
}
