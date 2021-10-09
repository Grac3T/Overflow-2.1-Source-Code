// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import java.util.Collections;
import org.eclipse.egit.github.core.IResourceProvider;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import org.eclipse.egit.github.core.util.UrlUtils;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;

public class PageIterator<V> implements Iterator<Collection<V>>, Iterable<Collection<V>>
{
    protected final PagedRequest<V> request;
    protected final GitHubClient client;
    protected int nextPage;
    protected int lastPage;
    protected String next;
    protected String last;
    
    public PageIterator(final PagedRequest<V> request, final GitHubClient client) {
        this.request = request;
        this.client = client;
        this.next = request.getUri();
        this.nextPage = this.parsePageNumber(this.next);
    }
    
    protected int parsePageNumber(final String uri) {
        if (uri == null || uri.length() == 0) {
            return -1;
        }
        URI parsed;
        try {
            parsed = new URI(uri);
        }
        catch (URISyntaxException e) {
            return -1;
        }
        final String param = UrlUtils.getParam(parsed, "page");
        if (param == null || param.length() == 0) {
            return -1;
        }
        try {
            return Integer.parseInt(param);
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }
    
    public int getNextPage() {
        return this.nextPage;
    }
    
    public int getLastPage() {
        return this.lastPage;
    }
    
    public String getNextUri() {
        return this.next;
    }
    
    public String getLastUri() {
        return this.last;
    }
    
    @Override
    public boolean hasNext() {
        return this.nextPage == 0 || this.next != null;
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
    
    @Override
    public Collection<V> next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.next != null) {
            if (this.nextPage < 1) {
                this.request.setUri(this.next);
            }
            else {
                try {
                    this.request.setUri(new URL(this.next).getFile());
                }
                catch (MalformedURLException e2) {
                    this.request.setUri(this.next);
                }
            }
        }
        GitHubResponse response;
        try {
            response = this.client.get(this.request);
        }
        catch (IOException e) {
            throw new NoSuchPageException(e);
        }
        Collection<V> resources = null;
        final Object body = response.getBody();
        if (body != null) {
            if (body instanceof Collection) {
                resources = (Collection<V>)body;
            }
            else if (body instanceof IResourceProvider) {
                resources = (Collection<V>)((IResourceProvider)body).getResources();
            }
            else {
                resources = (Collection<V>)Collections.singletonList(body);
            }
        }
        if (resources == null) {
            resources = (Collection<V>)Collections.emptyList();
        }
        ++this.nextPage;
        this.next = response.getNext();
        this.nextPage = this.parsePageNumber(this.next);
        this.last = response.getLast();
        this.lastPage = this.parsePageNumber(this.last);
        return resources;
    }
    
    public PagedRequest<V> getRequest() {
        return this.request;
    }
    
    @Override
    public Iterator<Collection<V>> iterator() {
        return this;
    }
}
