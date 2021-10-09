// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.CommitComment;
import java.io.Serializable;

public class PullRequestReviewCommentPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -2403658752886394741L;
    private String action;
    private CommitComment comment;
    private PullRequest pullRequest;
    
    public String getAction() {
        return this.action;
    }
    
    public PullRequestReviewCommentPayload setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public CommitComment getComment() {
        return this.comment;
    }
    
    public PullRequestReviewCommentPayload setComment(final CommitComment comment) {
        this.comment = comment;
        return this;
    }
    
    public PullRequest getPullRequest() {
        return this.pullRequest;
    }
    
    public PullRequestReviewCommentPayload setPullRequest(final PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }
}
