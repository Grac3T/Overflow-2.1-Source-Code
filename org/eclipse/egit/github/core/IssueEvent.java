// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class IssueEvent implements Serializable
{
    public static final String TYPE_CLOSED = "closed";
    public static final String TYPE_REOPENED = "reopened";
    public static final String TYPE_SUBSCRIBED = "subscribed";
    public static final String TYPE_MERGED = "merged";
    public static final String TYPE_REFERENCED = "referenced";
    public static final String TYPE_MENTIONED = "mentioned";
    public static final String TYPE_ASSIGNED = "assigned";
    public static final String TYPE_UNASSIGNED = "unassigned";
    public static final String TYPE_LABELED = "labeled";
    public static final String TYPE_UNLABELED = "unlabeled";
    public static final String TYPE_MILESTONED = "milestoned";
    public static final String TYPE_DEMILESTONED = "demilestoned";
    public static final String TYPE_RENAMED = "renamed";
    public static final String TYPE_LOCKED = "locked";
    public static final String TYPE_UNLOCKED = "unlocked";
    public static final String TYPE_HEAD_REF_DELETED = "head_ref_deleted";
    public static final String TYPE_HEAD_REF_RESTORED = "head_ref_restored";
    private static final long serialVersionUID = -842754108817725707L;
    private long id;
    private String url;
    private User actor;
    private String commitId;
    private String event;
    private Date createdAt;
    private Label label;
    private User assignee;
    private User assigner;
    private Milestone milestone;
    private Rename rename;
    private Issue issue;
    
    public long getId() {
        return this.id;
    }
    
    public IssueEvent setId(final long id) {
        this.id = id;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public IssueEvent setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getActor() {
        return this.actor;
    }
    
    public IssueEvent setActor(final User actor) {
        this.actor = actor;
        return this;
    }
    
    public String getCommitId() {
        return this.commitId;
    }
    
    public IssueEvent setCommitId(final String commitId) {
        this.commitId = commitId;
        return this;
    }
    
    public String getEvent() {
        return this.event;
    }
    
    public IssueEvent setEvent(final String event) {
        this.event = event;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public IssueEvent setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Label getLabel() {
        return this.label;
    }
    
    public IssueEvent setLabel(final Label label) {
        this.label = label;
        return this;
    }
    
    public User getAssignee() {
        return this.assignee;
    }
    
    public IssueEvent setAssignee(final User assignee) {
        this.assignee = assignee;
        return this;
    }
    
    public User getAssigner() {
        return this.assigner;
    }
    
    public IssueEvent setAssigner(final User assigner) {
        this.assigner = assigner;
        return this;
    }
    
    public Milestone getMilestone() {
        return this.milestone;
    }
    
    public IssueEvent setMilestone(final Milestone milestone) {
        this.milestone = milestone;
        return this;
    }
    
    public Rename getRename() {
        return this.rename;
    }
    
    public IssueEvent setRename(final Rename rename) {
        this.rename = rename;
        return this;
    }
    
    public Issue getIssue() {
        return this.issue;
    }
    
    public IssueEvent setIssue(final Issue issue) {
        this.issue = issue;
        return this;
    }
}
