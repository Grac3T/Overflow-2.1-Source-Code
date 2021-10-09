// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.CommitComment;
import java.io.Serializable;

public class CommitCommentPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -2606554911096551099L;
    private CommitComment comment;
    
    public CommitComment getComment() {
        return this.comment;
    }
    
    public CommitCommentPayload setComment(final CommitComment comment) {
        this.comment = comment;
        return this;
    }
}
