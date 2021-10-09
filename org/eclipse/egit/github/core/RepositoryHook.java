// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Map;
import java.util.Date;
import java.io.Serializable;

public class RepositoryHook implements Serializable
{
    private static final long serialVersionUID = -9023469643749604324L;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
    private long id;
    private RepositoryHookResponse lastResponse;
    private String name;
    private String url;
    private Map<String, String> config;
    
    public boolean isActive() {
        return this.active;
    }
    
    public RepositoryHook setActive(final boolean active) {
        this.active = active;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public RepositoryHook setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public RepositoryHook setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public RepositoryHook setId(final long id) {
        this.id = id;
        return this;
    }
    
    public RepositoryHookResponse getLastResponse() {
        return this.lastResponse;
    }
    
    public RepositoryHook setLastResponse(final RepositoryHookResponse lastResponse) {
        this.lastResponse = lastResponse;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public RepositoryHook setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public RepositoryHook setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public Map<String, String> getConfig() {
        return this.config;
    }
    
    public RepositoryHook setConfig(final Map<String, String> config) {
        this.config = config;
        return this;
    }
}
