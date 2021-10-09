// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import java.net.HttpURLConnection;

public class GitHubResponse
{
    protected final HttpURLConnection response;
    protected final Object body;
    protected PageLinks links;
    
    public GitHubResponse(final HttpURLConnection response, final Object body) {
        this.response = response;
        this.body = body;
    }
    
    public String getHeader(final String name) {
        return this.response.getHeaderField(name);
    }
    
    protected PageLinks getLinks() {
        if (this.links == null) {
            this.links = new PageLinks(this);
        }
        return this.links;
    }
    
    public String getFirst() {
        return this.getLinks().getFirst();
    }
    
    public String getPrevious() {
        return this.getLinks().getPrev();
    }
    
    public String getNext() {
        return this.getLinks().getNext();
    }
    
    public String getLast() {
        return this.getLinks().getLast();
    }
    
    public Object getBody() {
        return this.body;
    }
}
