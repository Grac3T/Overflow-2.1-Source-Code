// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.Collection;
import java.util.ArrayList;
import org.eclipse.egit.github.core.util.DateUtils;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

public class Issue implements Serializable
{
    private static final long serialVersionUID = 6358575015023539051L;
    private long id;
    private Date closedAt;
    private Date createdAt;
    private Date updatedAt;
    private int comments;
    private int number;
    private List<Label> labels;
    private Milestone milestone;
    private PullRequest pullRequest;
    private String body;
    private String bodyHtml;
    private String bodyText;
    private String htmlUrl;
    private String state;
    private String title;
    private String url;
    private User assignee;
    private User user;
    private User closedBy;
    
    public Date getClosedAt() {
        return DateUtils.clone(this.closedAt);
    }
    
    public Issue setClosedAt(final Date closedAt) {
        this.closedAt = DateUtils.clone(closedAt);
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Issue setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public Issue setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public int getComments() {
        return this.comments;
    }
    
    public Issue setComments(final int comments) {
        this.comments = comments;
        return this;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public Issue setNumber(final int number) {
        this.number = number;
        return this;
    }
    
    public List<Label> getLabels() {
        return (List<Label>)this.labels;
    }
    
    public Issue setLabels(final List<Label> labels) {
        this.labels = ((labels != null) ? new ArrayList(labels) : null);
        return this;
    }
    
    public Milestone getMilestone() {
        return this.milestone;
    }
    
    public Issue setMilestone(final Milestone milestone) {
        this.milestone = milestone;
        return this;
    }
    
    public PullRequest getPullRequest() {
        return this.pullRequest;
    }
    
    public Issue setPullRequest(final PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public Issue setBody(final String body) {
        this.body = body;
        return this;
    }
    
    public String getBodyHtml() {
        return this.bodyHtml;
    }
    
    public Issue setBodyHtml(final String bodyHtml) {
        this.bodyHtml = bodyHtml;
        return this;
    }
    
    public String getBodyText() {
        return this.bodyText;
    }
    
    public Issue setBodyText(final String bodyText) {
        this.bodyText = bodyText;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public Issue setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getState() {
        return this.state;
    }
    
    public Issue setState(final String state) {
        this.state = state;
        return this;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Issue setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Issue setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getAssignee() {
        return this.assignee;
    }
    
    public Issue setAssignee(final User assignee) {
        this.assignee = assignee;
        return this;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public Issue setUser(final User user) {
        this.user = user;
        return this;
    }
    
    public User getClosedBy() {
        return this.closedBy;
    }
    
    public Issue setClosedBy(final User closedBy) {
        this.closedBy = closedBy;
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public Issue setId(final long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String toString() {
        return "Issue " + this.number;
    }
}
