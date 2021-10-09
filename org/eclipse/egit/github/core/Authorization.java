// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

public class Authorization implements Serializable
{
    private static final long serialVersionUID = -5564926246696914047L;
    private Application app;
    private Date createdAt;
    private Date updatedAt;
    private int id;
    private List<String> scopes;
    private String note;
    private String noteUrl;
    private String token;
    private String url;
    
    public Application getApp() {
        return this.app;
    }
    
    public Authorization setApp(final Application app) {
        this.app = app;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Authorization setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public Authorization setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Authorization setId(final int id) {
        this.id = id;
        return this;
    }
    
    public String getNote() {
        return this.note;
    }
    
    public Authorization setNote(final String note) {
        this.note = note;
        return this;
    }
    
    public String getNoteUrl() {
        return this.noteUrl;
    }
    
    public Authorization setNoteUrl(final String noteUrl) {
        this.noteUrl = noteUrl;
        return this;
    }
    
    public List<String> getScopes() {
        return this.scopes;
    }
    
    public Authorization setScopes(final List<String> scopes) {
        this.scopes = scopes;
        return this;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public Authorization setToken(final String token) {
        this.token = token;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Authorization setUrl(final String url) {
        this.url = url;
        return this;
    }
}
