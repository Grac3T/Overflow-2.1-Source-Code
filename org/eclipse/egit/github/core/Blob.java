// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Blob implements Serializable
{
    private static final long serialVersionUID = -7538850340225102994L;
    public static final String ENCODING_BASE64 = "base64";
    public static final String ENCODING_UTF8 = "utf-8";
    private String content;
    private String encoding;
    
    public String getContent() {
        return this.content;
    }
    
    public Blob setContent(final String content) {
        this.content = content;
        return this;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public Blob setEncoding(final String encoding) {
        this.encoding = encoding;
        return this;
    }
}
