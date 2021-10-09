// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Gist implements Serializable
{
    private static final long serialVersionUID = -2221817463228217456L;
    @SerializedName("public")
    private boolean isPublic;
    private Date createdAt;
    private Date updatedAt;
    private int comments;
    private List<GistRevision> history;
    private Map<String, GistFile> files;
    private String description;
    private String gitPullUrl;
    private String gitPushUrl;
    private String htmlUrl;
    private String id;
    private String url;
    private User owner;
    
    public boolean isPublic() {
        return this.isPublic;
    }
    
    public Gist setPublic(final boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Gist setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public Gist setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public int getComments() {
        return this.comments;
    }
    
    public Gist setComments(final int comments) {
        this.comments = comments;
        return this;
    }
    
    public List<GistRevision> getHistory() {
        return (List<GistRevision>)this.history;
    }
    
    public Gist setHistory(final List<GistRevision> history) {
        this.history = history;
        return this;
    }
    
    public Map<String, GistFile> getFiles() {
        return (Map<String, GistFile>)this.files;
    }
    
    public Gist setFiles(final Map<String, GistFile> files) {
        this.files = files;
        return this;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Gist setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public String getGitPullUrl() {
        return this.gitPullUrl;
    }
    
    public Gist setGitPullUrl(final String gitPullUrl) {
        this.gitPullUrl = gitPullUrl;
        return this;
    }
    
    public String getGitPushUrl() {
        return this.gitPushUrl;
    }
    
    public Gist setGitPushUrl(final String gitPushUrl) {
        this.gitPushUrl = gitPushUrl;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public Gist setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Gist setId(final String id) {
        this.id = id;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Gist setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getOwner() {
        return this.owner;
    }
    
    public Gist setOwner(final User owner) {
        this.owner = owner;
        return this;
    }
    
    @Deprecated
    public User getUser() {
        return this.owner;
    }
    
    @Deprecated
    public Gist setUser(final User user) {
        this.owner = user;
        return this;
    }
}
