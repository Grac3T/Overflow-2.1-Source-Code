// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class RepositoryTag implements Serializable
{
    private static final long serialVersionUID = 1070566274663989459L;
    private String name;
    private String tarballUrl;
    private String zipballUrl;
    private TypedResource commit;
    
    public String getName() {
        return this.name;
    }
    
    public RepositoryTag setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getTarballUrl() {
        return this.tarballUrl;
    }
    
    public RepositoryTag setTarballUrl(final String tarballUrl) {
        this.tarballUrl = tarballUrl;
        return this;
    }
    
    public String getZipballUrl() {
        return this.zipballUrl;
    }
    
    public RepositoryTag setZipballUrl(final String zipballUrl) {
        this.zipballUrl = zipballUrl;
        return this;
    }
    
    public TypedResource getCommit() {
        return this.commit;
    }
    
    public RepositoryTag setCommit(final TypedResource commit) {
        this.commit = commit;
        return this;
    }
}
