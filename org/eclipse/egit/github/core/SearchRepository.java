// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SearchRepository implements IRepositoryIdProvider, Serializable
{
    private static final long serialVersionUID = 978627174722864632L;
    private boolean fork;
    private boolean hasDownloads;
    private boolean hasIssues;
    private boolean hasWiki;
    @SerializedName("private")
    private boolean isPrivate;
    private Date createdAt;
    private Date pushedAt;
    private String description;
    private String homepage;
    private String language;
    private String name;
    private String owner;
    private String url;
    private int forks;
    private int openIssues;
    private int size;
    private int watchers;
    
    public SearchRepository(final String owner, final String name) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        if (owner.length() == 0) {
            throw new IllegalArgumentException("Owner cannot be empty");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.owner = owner;
        this.name = name;
    }
    
    SearchRepository() {
    }
    
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj == this || (obj instanceof SearchRepository && this.getId().equals(((SearchRepository)obj).getId()));
    }
    
    @Override
    public String toString() {
        return this.getId();
    }
    
    public String getId() {
        return this.owner + '/' + this.name;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isFork() {
        return this.fork;
    }
    
    public boolean isHasDownloads() {
        return this.hasDownloads;
    }
    
    public boolean isHasIssues() {
        return this.hasIssues;
    }
    
    public boolean isHasWiki() {
        return this.hasWiki;
    }
    
    public boolean isPrivate() {
        return this.isPrivate;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Date getPushedAt() {
        return DateUtils.clone(this.pushedAt);
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getHomepage() {
        return this.homepage;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public int getForks() {
        return this.forks;
    }
    
    public int getOpenIssues() {
        return this.openIssues;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getWatchers() {
        return this.watchers;
    }
    
    @Override
    public String generateId() {
        final String owner = this.owner;
        if (owner == null || owner.length() == 0) {
            return null;
        }
        final String name = this.name;
        if (name == null || name.length() == 0) {
            return null;
        }
        return owner + "/" + name;
    }
}
