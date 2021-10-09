// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.util.Map;
import java.util.Collections;
import org.eclipse.egit.github.core.Comment;
import java.util.List;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.client.PageIterator;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;

public class GistService extends GitHubService
{
    public GistService() {
    }
    
    public GistService(final GitHubClient client) {
        super(client);
    }
    
    protected String checkGistId(final String gistId) {
        if (gistId == null) {
            throw new IllegalArgumentException("Gist id cannot be null");
        }
        if (gistId.length() == 0) {
            throw new IllegalArgumentException("Gist id cannot be empty");
        }
        return gistId;
    }
    
    public Gist getGist(final String id) throws IOException {
        this.checkGistId(id);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(id);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(Gist.class);
        return (Gist)this.client.get(request).getBody();
    }
    
    public PageIterator<Gist> pageStarredGists() {
        return (PageIterator<Gist>)this.pageStarredGists(100);
    }
    
    public PageIterator<Gist> pageStarredGists(final int size) {
        return (PageIterator<Gist>)this.pageStarredGists(1, size);
    }
    
    public PageIterator<Gist> pageStarredGists(final int start, final int size) {
        final PagedRequest<Gist> request = (PagedRequest<Gist>)this.createPagedRequest(start, size);
        request.setUri("/gists/starred");
        request.setType(new GistService$1(this).getType());
        return (PageIterator<Gist>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<Gist> getStarredGists() throws IOException {
        return (List<Gist>)this.getAll(this.pageStarredGists());
    }
    
    protected PagedRequest<Gist> createUserGistRequest(final String user, final int start, final int size) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/gists");
        final PagedRequest<Gist> request = (PagedRequest<Gist>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new GistService$2(this).getType());
        return request;
    }
    
    public List<Gist> getGists(final String user) throws IOException {
        return (List<Gist>)this.getAll(this.pageGists(user));
    }
    
    public PageIterator<Gist> pageGists(final String user) {
        return (PageIterator<Gist>)this.pageGists(user, 100);
    }
    
    public PageIterator<Gist> pageGists(final String user, final int size) {
        return (PageIterator<Gist>)this.pageGists(user, 1, size);
    }
    
    public PageIterator<Gist> pageGists(final String user, final int start, final int size) {
        final PagedRequest<Gist> request = (PagedRequest<Gist>)this.createUserGistRequest(user, start, size);
        return (PageIterator<Gist>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Gist> pagePublicGists() {
        return (PageIterator<Gist>)this.pagePublicGists(100);
    }
    
    public PageIterator<Gist> pagePublicGists(final int size) {
        return (PageIterator<Gist>)this.pagePublicGists(1, size);
    }
    
    public PageIterator<Gist> pagePublicGists(final int start, final int size) {
        final PagedRequest<Gist> request = (PagedRequest<Gist>)this.createPagedRequest(start, size);
        request.setUri("/gists/public");
        request.setType(new GistService$3(this).getType());
        return (PageIterator<Gist>)this.createPageIterator((PagedRequest)request);
    }
    
    public Gist createGist(final Gist gist) throws IOException {
        if (gist == null) {
            throw new IllegalArgumentException("Gist cannot be null");
        }
        return this.client.post("/gists", gist, Gist.class);
    }
    
    public Gist updateGist(final Gist gist) throws IOException {
        if (gist == null) {
            throw new IllegalArgumentException("Gist cannot be null");
        }
        final String id = gist.getId();
        this.checkGistId(id);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(id);
        return this.client.post(uri.toString(), gist, Gist.class);
    }
    
    public Comment createComment(final String gistId, final String comment) throws IOException {
        this.checkGistId(gistId);
        if (comment == null) {
            throw new IllegalArgumentException("Gist comment cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        uri.append("/comments");
        final Map<String, String> params = Collections.singletonMap("body", comment);
        return this.client.post(uri.toString(), params, Comment.class);
    }
    
    public List<Comment> getComments(final String gistId) throws IOException {
        this.checkGistId(gistId);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        uri.append("/comments");
        final PagedRequest<Comment> request = (PagedRequest<Comment>)this.createPagedRequest();
        request.setUri(uri).setType(new GistService$4(this).getType());
        return (List<Comment>)this.getAll((PagedRequest)request);
    }
    
    public void deleteGist(final String gistId) throws IOException {
        this.checkGistId(gistId);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        this.client.delete(uri.toString());
    }
    
    public Comment getComment(final long commentId) throws IOException {
        final StringBuilder uri = new StringBuilder("/gists/comments");
        uri.append('/').append(commentId);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(Comment.class);
        return (Comment)this.client.get(request).getBody();
    }
    
    public Comment editComment(final Comment comment) throws IOException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/gists/comments");
        uri.append('/').append(comment.getId());
        return this.client.post(uri.toString(), comment, Comment.class);
    }
    
    public void deleteComment(final long commentId) throws IOException {
        final StringBuilder uri = new StringBuilder("/gists/comments");
        uri.append('/').append(commentId);
        this.client.delete(uri.toString());
    }
    
    public void starGist(final String gistId) throws IOException {
        this.checkGistId(gistId);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        uri.append("/star");
        this.client.put(uri.toString());
    }
    
    public void unstarGist(final String gistId) throws IOException {
        this.checkGistId(gistId);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        uri.append("/star");
        this.client.delete(uri.toString());
    }
    
    public boolean isStarred(final String gistId) throws IOException {
        this.checkGistId(gistId);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        uri.append("/star");
        return this.check(uri.toString());
    }
    
    public Gist forkGist(final String gistId) throws IOException {
        this.checkGistId(gistId);
        final StringBuilder uri = new StringBuilder("/gists");
        uri.append('/').append(gistId);
        uri.append("/fork");
        return this.client.post(uri.toString(), null, Gist.class);
    }
}
