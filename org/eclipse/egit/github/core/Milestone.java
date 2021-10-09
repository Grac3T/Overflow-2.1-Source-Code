// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class Milestone implements Serializable
{
    private static final long serialVersionUID = 8017385076255266092L;
    private Date createdAt;
    private Date dueOn;
    private int closedIssues;
    private int number;
    private int openIssues;
    private String description;
    private String state;
    private String title;
    private String url;
    private User creator;
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Milestone setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getDueOn() {
        return DateUtils.clone(this.dueOn);
    }
    
    public Milestone setDueOn(final Date dueOn) {
        this.dueOn = DateUtils.clone(dueOn);
        return this;
    }
    
    public int getClosedIssues() {
        return this.closedIssues;
    }
    
    public Milestone setClosedIssues(final int closedIssues) {
        this.closedIssues = closedIssues;
        return this;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public Milestone setNumber(final int number) {
        this.number = number;
        return this;
    }
    
    public int getOpenIssues() {
        return this.openIssues;
    }
    
    public Milestone setOpenIssues(final int openIssues) {
        this.openIssues = openIssues;
        return this;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Milestone setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public String getState() {
        return this.state;
    }
    
    public Milestone setState(final String state) {
        this.state = state;
        return this;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Milestone setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Milestone setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getCreator() {
        return this.creator;
    }
    
    public Milestone setCreator(final User creator) {
        this.creator = creator;
        return this;
    }
}
