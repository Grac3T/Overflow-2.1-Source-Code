// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Tag implements Serializable
{
    private static final long serialVersionUID = 8505182933582492676L;
    private CommitUser tagger;
    private String message;
    private String sha;
    private String tag;
    private String url;
    private TypedResource object;
    
    public CommitUser getTagger() {
        return this.tagger;
    }
    
    public Tag setTagger(final CommitUser tagger) {
        this.tagger = tagger;
        return this;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Tag setMessage(final String message) {
        this.message = message;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public Tag setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public Tag setTag(final String tag) {
        this.tag = tag;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Tag setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public TypedResource getObject() {
        return this.object;
    }
    
    public Tag setObject(final TypedResource object) {
        this.object = object;
        return this;
    }
}
