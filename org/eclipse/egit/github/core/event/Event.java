// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import org.eclipse.egit.github.core.User;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Event implements Serializable
{
    public static final String TYPE_COMMIT_COMMENT = "CommitCommentEvent";
    public static final String TYPE_CREATE = "CreateEvent";
    public static final String TYPE_DELETE = "DeleteEvent";
    public static final String TYPE_DOWNLOAD = "DownloadEvent";
    public static final String TYPE_FOLLOW = "FollowEvent";
    public static final String TYPE_FORK = "ForkEvent";
    public static final String TYPE_FORK_APPLY = "ForkApplyEvent";
    public static final String TYPE_GIST = "GistEvent";
    public static final String TYPE_GOLLUM = "GollumEvent";
    public static final String TYPE_ISSUE_COMMENT = "IssueCommentEvent";
    public static final String TYPE_ISSUES = "IssuesEvent";
    public static final String TYPE_MEMBER = "MemberEvent";
    public static final String TYPE_PUBLIC = "PublicEvent";
    public static final String TYPE_PULL_REQUEST = "PullRequestEvent";
    public static final String TYPE_PULL_REQUEST_REVIEW_COMMENT = "PullRequestReviewCommentEvent";
    public static final String TYPE_PUSH = "PushEvent";
    public static final String TYPE_RELEASE = "ReleaseEvent";
    public static final String TYPE_TEAM_ADD = "TeamAddEvent";
    public static final String TYPE_WATCH = "WatchEvent";
    private static final long serialVersionUID = 3633702964380402233L;
    private String type;
    @SerializedName("public")
    private boolean isPublic;
    private EventPayload payload;
    private EventRepository repo;
    private String id;
    private User actor;
    private User org;
    private Date createdAt;
    
    public String getType() {
        return this.type;
    }
    
    public Event setType(final String type) {
        this.type = type;
        return this;
    }
    
    public boolean isPublic() {
        return this.isPublic;
    }
    
    public Event setPublic(final boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }
    
    public EventRepository getRepo() {
        return this.repo;
    }
    
    public Event setRepo(final EventRepository repo) {
        this.repo = repo;
        return this;
    }
    
    public User getActor() {
        return this.actor;
    }
    
    public Event setActor(final User actor) {
        this.actor = actor;
        return this;
    }
    
    public User getOrg() {
        return this.org;
    }
    
    public Event setOrg(final User org) {
        this.org = org;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Event setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public EventPayload getPayload() {
        return this.payload;
    }
    
    public Event setPayload(final EventPayload payload) {
        this.payload = payload;
        return this;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Event setId(final String id) {
        this.id = id;
        return this;
    }
}
