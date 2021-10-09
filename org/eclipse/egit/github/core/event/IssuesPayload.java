// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Issue;
import java.io.Serializable;

public class IssuesPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 3210795492806809443L;
    private String action;
    private Issue issue;
    
    public String getAction() {
        return this.action;
    }
    
    public IssuesPayload setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public Issue getIssue() {
        return this.issue;
    }
    
    public IssuesPayload setIssue(final Issue issue) {
        this.issue = issue;
        return this;
    }
}
