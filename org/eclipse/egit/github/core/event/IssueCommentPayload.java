// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import java.io.Serializable;

public class IssueCommentPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 2661548417314120170L;
    private String action;
    private Issue issue;
    private Comment comment;
    
    public String getAction() {
        return this.action;
    }
    
    public IssueCommentPayload setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public Issue getIssue() {
        return this.issue;
    }
    
    public IssueCommentPayload setIssue(final Issue issue) {
        this.issue = issue;
        return this;
    }
    
    public Comment getComment() {
        return this.comment;
    }
    
    public IssueCommentPayload setComment(final Comment comment) {
        this.comment = comment;
        return this;
    }
}
