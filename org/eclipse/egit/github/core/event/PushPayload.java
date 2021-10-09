// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Commit;
import java.util.List;
import java.io.Serializable;

public class PushPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = -1542484898531583478L;
    private String before;
    private String head;
    private String ref;
    private int size;
    private List<Commit> commits;
    
    public String getBefore() {
        return this.before;
    }
    
    public PushPayload setBefore(final String before) {
        this.before = before;
        return this;
    }
    
    public String getHead() {
        return this.head;
    }
    
    public PushPayload setHead(final String head) {
        this.head = head;
        return this;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public PushPayload setRef(final String ref) {
        this.ref = ref;
        return this;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public PushPayload setSize(final int size) {
        this.size = size;
        return this;
    }
    
    public List<Commit> getCommits() {
        return (List<Commit>)this.commits;
    }
    
    public PushPayload setCommits(final List<Commit> commits) {
        this.commits = commits;
        return this;
    }
}
