// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.PullRequest;
import java.io.Serializable;

public class PullRequestPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -8234504270587265625L;
    private String action;
    private int number;
    private PullRequest pullRequest;
    
    public String getAction() {
        return this.action;
    }
    
    public PullRequestPayload setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public PullRequestPayload setNumber(final int number) {
        this.number = number;
        return this;
    }
    
    public PullRequest getPullRequest() {
        return this.pullRequest;
    }
    
    public PullRequestPayload setPullRequest(final PullRequest pullRequest) {
        this.pullRequest = pullRequest;
        return this;
    }
}
