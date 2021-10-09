// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

public class SearchIssue implements Serializable
{
    private static final long serialVersionUID = 4853048031771824016L;
    private Date createdAt;
    private Date updatedAt;
    private int comments;
    private int number;
    private int position;
    private int votes;
    private List<String> labels;
    private String body;
    private String gravatarId;
    private String htmlUrl;
    private String state;
    private String title;
    private String user;
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public SearchIssue setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public SearchIssue setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public int getComments() {
        return this.comments;
    }
    
    public SearchIssue setComments(final int comments) {
        this.comments = comments;
        return this;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public SearchIssue setNumber(final int number) {
        this.number = number;
        return this;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public SearchIssue setPosition(final int position) {
        this.position = position;
        return this;
    }
    
    public int getVotes() {
        return this.votes;
    }
    
    public SearchIssue setVotes(final int votes) {
        this.votes = votes;
        return this;
    }
    
    public List<String> getLabels() {
        return this.labels;
    }
    
    public SearchIssue setLabels(final List<String> labels) {
        this.labels = labels;
        return this;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public SearchIssue setBody(final String body) {
        this.body = body;
        return this;
    }
    
    public String getGravatarId() {
        return this.gravatarId;
    }
    
    public SearchIssue setGravatarId(final String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public SearchIssue setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getState() {
        return this.state;
    }
    
    public SearchIssue setState(final String state) {
        this.state = state;
        return this;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public SearchIssue setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public SearchIssue setUser(final String user) {
        this.user = user;
        return this;
    }
}
