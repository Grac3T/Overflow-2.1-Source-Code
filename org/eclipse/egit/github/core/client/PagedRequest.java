// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import org.eclipse.egit.github.core.util.UrlUtils;

public class PagedRequest<V> extends GitHubRequest
{
    public static final int PAGE_FIRST = 1;
    public static final int PAGE_SIZE = 100;
    private final int pageSize;
    private final int page;
    
    public PagedRequest() {
        this(1, 100);
    }
    
    public PagedRequest(final int start, final int size) {
        this.page = start;
        this.pageSize = size;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    protected void addParams(final StringBuilder uri) {
        super.addParams(uri);
        final int size = this.getPageSize();
        if (size > 0) {
            UrlUtils.addParam("per_page", Integer.toString(size), uri);
        }
        final int number = this.getPage();
        if (number > 0) {
            UrlUtils.addParam("page", Integer.toString(number), uri);
        }
    }
    
    public int getPage() {
        return this.page;
    }
}
