// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.Serializable;

public class RepositoryId implements IRepositoryIdProvider, Serializable
{
    private static final long serialVersionUID = -57313931704393200L;
    private final String owner;
    private final String name;
    
    public static RepositoryId createFromUrl(final URL url) {
        return (url != null) ? createFromId(url.getPath()) : null;
    }
    
    public static RepositoryId createFromId(final String id) {
        if (id == null || id.length() == 0) {
            return null;
        }
        String owner = null;
        String name = null;
        for (final String segment : id.split("/")) {
            if (segment.length() > 0) {
                if (owner == null) {
                    owner = segment;
                }
                else {
                    if (name != null) {
                        break;
                    }
                    name = segment;
                }
            }
        }
        return (owner != null && owner.length() > 0 && name != null && name.length() > 0) ? new RepositoryId(owner, name) : null;
    }
    
    public static RepositoryId createFromUrl(final String url) {
        if (url == null || url.length() == 0) {
            return null;
        }
        try {
            return createFromUrl(new URL(url));
        }
        catch (MalformedURLException e) {
            return null;
        }
    }
    
    public static RepositoryId create(final String owner, final String name) {
        return new RepositoryId(owner, name);
    }
    
    public RepositoryId(final String owner, final String name) {
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
    
    public String getOwner() {
        return this.owner;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String generateId() {
        return this.owner + "/" + this.name;
    }
    
    @Override
    public int hashCode() {
        return this.generateId().hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RepositoryId)) {
            return false;
        }
        final RepositoryId other = (RepositoryId)obj;
        return this.name.equals(other.name) && this.owner.equals(other.owner);
    }
    
    @Override
    public String toString() {
        return this.generateId();
    }
}
