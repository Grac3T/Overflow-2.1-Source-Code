// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class GistRevision implements Serializable
{
    private static final long serialVersionUID = -7863453407918499259L;
    private Date committedAt;
    private GistChangeStatus changeStatus;
    private String url;
    private String version;
    private User user;
    
    public Date getCommittedAt() {
        return DateUtils.clone(this.committedAt);
    }
    
    public GistRevision setCommittedAt(final Date committedAt) {
        this.committedAt = DateUtils.clone(committedAt);
        return this;
    }
    
    public GistChangeStatus getChangeStatus() {
        return this.changeStatus;
    }
    
    public GistRevision setChangeStatus(final GistChangeStatus changeStatus) {
        this.changeStatus = changeStatus;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public GistRevision setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public GistRevision setVersion(final String version) {
        this.version = version;
        return this;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public GistRevision setUser(final User user) {
        this.user = user;
        return this;
    }
}
