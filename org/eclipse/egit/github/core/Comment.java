// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class Comment implements Serializable
{
    private static final long serialVersionUID = 5128896032791651031L;
    private Date createdAt;
    private Date updatedAt;
    private String body;
    private String bodyHtml;
    private String bodyText;
    private long id;
    private String url;
    private User user;
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Comment setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public Comment setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public Comment setBody(final String body) {
        this.body = body;
        return this;
    }
    
    public String getBodyHtml() {
        return this.bodyHtml;
    }
    
    public Comment setBodyHtml(final String bodyHtml) {
        this.bodyHtml = bodyHtml;
        return this;
    }
    
    public String getBodyText() {
        return this.bodyText;
    }
    
    public Comment setBodyText(final String bodyText) {
        this.bodyText = bodyText;
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public Comment setId(final long id) {
        this.id = id;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Comment setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public Comment setUser(final User user) {
        this.user = user;
        return this;
    }
}
