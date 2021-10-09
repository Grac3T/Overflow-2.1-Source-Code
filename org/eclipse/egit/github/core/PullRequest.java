// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class PullRequest implements Serializable
{
    private static final long serialVersionUID = 7858604768525096763L;
    private boolean mergeable;
    private boolean merged;
    private Date closedAt;
    private Date mergedAt;
    private Date updatedAt;
    private Date createdAt;
    private long id;
    private int additions;
    private int changedFiles;
    private int comments;
    private int reviewComments;
    private int commits;
    private int deletions;
    private int number;
    private Milestone milestone;
    private PullRequestMarker base;
    private PullRequestMarker head;
    private String body;
    private String bodyHtml;
    private String bodyText;
    private String diffUrl;
    private String htmlUrl;
    private String issueUrl;
    private String patchUrl;
    private String state;
    private String title;
    private String url;
    private User assignee;
    private User mergedBy;
    private User user;
    
    public boolean isMergeable() {
        return this.mergeable;
    }
    
    public PullRequest setMergeable(final boolean mergeable) {
        this.mergeable = mergeable;
        return this;
    }
    
    public boolean isMerged() {
        return this.merged;
    }
    
    public PullRequest setMerged(final boolean merged) {
        this.merged = merged;
        return this;
    }
    
    public Date getClosedAt() {
        return DateUtils.clone(this.closedAt);
    }
    
    public PullRequest setClosedAt(final Date closedAt) {
        this.closedAt = DateUtils.clone(closedAt);
        return this;
    }
    
    public Date getMergedAt() {
        return DateUtils.clone(this.mergedAt);
    }
    
    public PullRequest setMergedAt(final Date mergedAt) {
        this.mergedAt = DateUtils.clone(mergedAt);
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public PullRequest setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public PullRequest setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public int getAdditions() {
        return this.additions;
    }
    
    public PullRequest setAdditions(final int additions) {
        this.additions = additions;
        return this;
    }
    
    public int getChangedFiles() {
        return this.changedFiles;
    }
    
    public PullRequest setChangedFiles(final int changedFiles) {
        this.changedFiles = changedFiles;
        return this;
    }
    
    public int getComments() {
        return this.comments;
    }
    
    public PullRequest setComments(final int comments) {
        this.comments = comments;
        return this;
    }
    
    public int getReviewComments() {
        return this.reviewComments;
    }
    
    public PullRequest setReviewComments(final int reviewComments) {
        this.reviewComments = reviewComments;
        return this;
    }
    
    public int getCommits() {
        return this.commits;
    }
    
    public PullRequest setCommits(final int commits) {
        this.commits = commits;
        return this;
    }
    
    public int getDeletions() {
        return this.deletions;
    }
    
    public PullRequest setDeletions(final int deletions) {
        this.deletions = deletions;
        return this;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public PullRequest setNumber(final int number) {
        this.number = number;
        return this;
    }
    
    public PullRequestMarker getBase() {
        return this.base;
    }
    
    public PullRequest setBase(final PullRequestMarker base) {
        this.base = base;
        return this;
    }
    
    public PullRequestMarker getHead() {
        return this.head;
    }
    
    public PullRequest setHead(final PullRequestMarker head) {
        this.head = head;
        return this;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public PullRequest setBody(final String body) {
        this.body = body;
        return this;
    }
    
    public String getBodyHtml() {
        return this.bodyHtml;
    }
    
    public PullRequest setBodyHtml(final String bodyHtml) {
        this.bodyHtml = bodyHtml;
        return this;
    }
    
    public String getBodyText() {
        return this.bodyText;
    }
    
    public PullRequest setBodyText(final String bodyText) {
        this.bodyText = bodyText;
        return this;
    }
    
    public String getDiffUrl() {
        return this.diffUrl;
    }
    
    public PullRequest setDiffUrl(final String diffUrl) {
        this.diffUrl = diffUrl;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public PullRequest setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getIssueUrl() {
        return this.issueUrl;
    }
    
    public PullRequest setIssueUrl(final String issueUrl) {
        this.issueUrl = issueUrl;
        return this;
    }
    
    public String getPatchUrl() {
        return this.patchUrl;
    }
    
    public PullRequest setPatchUrl(final String patchUrl) {
        this.patchUrl = patchUrl;
        return this;
    }
    
    public String getState() {
        return this.state;
    }
    
    public PullRequest setState(final String state) {
        this.state = state;
        return this;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public PullRequest setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public PullRequest setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getMergedBy() {
        return this.mergedBy;
    }
    
    public PullRequest setMergedBy(final User mergedBy) {
        this.mergedBy = mergedBy;
        return this;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public PullRequest setUser(final User user) {
        this.user = user;
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public PullRequest setId(final long id) {
        this.id = id;
        return this;
    }
    
    public Milestone getMilestone() {
        return this.milestone;
    }
    
    public PullRequest setMilestone(final Milestone milestone) {
        this.milestone = milestone;
        return this;
    }
    
    public User getAssignee() {
        return this.assignee;
    }
    
    public PullRequest setAssignee(final User assignee) {
        this.assignee = assignee;
        return this;
    }
    
    @Override
    public String toString() {
        return "Pull Request " + this.number;
    }
}
