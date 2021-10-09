// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.GollumPage;
import java.util.List;
import java.io.Serializable;

public class GollumPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 7111499446827257290L;
    private List<GollumPage> pages;
    
    public List<GollumPage> getPages() {
        return (List<GollumPage>)this.pages;
    }
    
    public GollumPayload setPages(final List<GollumPage> pages) {
        this.pages = pages;
        return this;
    }
}
