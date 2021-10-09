// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.text.MessageFormat;
import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class CommitStatus implements Serializable
{
    private static final long serialVersionUID = -7701789812780758070L;
    public static final String STATE_ERROR = "error";
    public static final String STATE_FAILURE = "failure";
    public static final String STATE_PENDING = "pending";
    public static final String STATE_SUCCESS = "success";
    private Date createdAt;
    private Date updatedAt;
    private long id;
    private String context;
    private String description;
    private String state;
    private String targetUrl;
    private String url;
    private User creator;
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public CommitStatus setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public CommitStatus setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public CommitStatus setId(final long id) {
        this.id = id;
        return this;
    }
    
    public String getContext() {
        return this.context;
    }
    
    public CommitStatus setContext(final String context) {
        this.context = context;
        return this;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public CommitStatus setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public String getState() {
        return this.state;
    }
    
    public CommitStatus setState(final String state) {
        if ("error".equals(state) || "failure".equals(state) || "pending".equals(state) || "success".equals(state)) {
            this.state = state;
            return this;
        }
        throw new IllegalArgumentException(MessageFormat.format("Invalid state {0}", state));
    }
    
    public String getTargetUrl() {
        return this.targetUrl;
    }
    
    public CommitStatus setTargetUrl(final String targetUrl) {
        this.targetUrl = targetUrl;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public CommitStatus setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getCreator() {
        return this.creator;
    }
    
    public CommitStatus setCreator(final User creator) {
        this.creator = creator;
        return this;
    }
}
