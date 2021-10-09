// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.CommitFile;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.PullRequestMarker;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.egit.github.core.client.PageIterator;
import java.util.List;
import java.util.Collections;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class PullRequestService extends GitHubService
{
    public static final String PR_TITLE = "title";
    public static final String PR_BODY = "body";
    public static final String PR_BASE = "base";
    public static final String PR_HEAD = "head";
    public static final String PR_STATE = "state";
    
    public PullRequestService() {
    }
    
    public PullRequestService(final GitHubClient client) {
        super(client);
    }
    
    public PullRequest getPullRequest(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(PullRequest.class);
        return (PullRequest)this.client.get(request).getBody();
    }
    
    protected PagedRequest<PullRequest> createPullsRequest(final IRepositoryIdProvider provider, final String state, final int start, final int size) {
        final String id = this.getId(provider);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/pulls");
        final PagedRequest<PullRequest> request = (PagedRequest<PullRequest>)this.createPagedRequest(start, size);
        request.setUri(uri);
        if (state != null) {
            request.setParams(Collections.singletonMap("state", state));
        }
        request.setType(new PullRequestService$1(this).getType());
        return request;
    }
    
    public List<PullRequest> getPullRequests(final IRepositoryIdProvider repository, final String state) throws IOException {
        return (List<PullRequest>)this.getAll(this.pagePullRequests(repository, state));
    }
    
    public PageIterator<PullRequest> pagePullRequests(final IRepositoryIdProvider repository, final String state) {
        return (PageIterator<PullRequest>)this.pagePullRequests(repository, state, 100);
    }
    
    public PageIterator<PullRequest> pagePullRequests(final IRepositoryIdProvider repository, final String state, final int size) {
        return (PageIterator<PullRequest>)this.pagePullRequests(repository, state, 1, size);
    }
    
    public PageIterator<PullRequest> pagePullRequests(final IRepositoryIdProvider repository, final String state, final int start, final int size) {
        final PagedRequest<PullRequest> request = (PagedRequest<PullRequest>)this.createPullsRequest(repository, state, start, size);
        return (PageIterator<PullRequest>)this.createPageIterator((PagedRequest)request);
    }
    
    private Map<String, String> createPrMap(final PullRequest request) {
        final Map<String, String> params = new HashMap<String, String>();
        if (request != null) {
            final String title = request.getTitle();
            if (title != null) {
                params.put("title", title);
            }
            final String body = request.getBody();
            if (body != null) {
                params.put("body", body);
            }
            final PullRequestMarker baseMarker = request.getBase();
            if (baseMarker != null) {
                final String base = baseMarker.getLabel();
                if (base != null) {
                    params.put("base", base);
                }
            }
            final PullRequestMarker headMarker = request.getHead();
            if (headMarker != null) {
                final String head = headMarker.getLabel();
                if (head != null) {
                    params.put("head", head);
                }
            }
        }
        return params;
    }
    
    private Map<String, String> editPrMap(final PullRequest request) {
        final Map<String, String> params = new HashMap<String, String>();
        final String title = request.getTitle();
        if (title != null) {
            params.put("title", title);
        }
        final String body = request.getBody();
        if (body != null) {
            params.put("body", body);
        }
        final String state = request.getState();
        if (state != null) {
            params.put("state", state);
        }
        return params;
    }
    
    public PullRequest createPullRequest(final IRepositoryIdProvider repository, final PullRequest request) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/pulls");
        final Map<String, String> params = (Map<String, String>)this.createPrMap(request);
        return this.client.post(uri.toString(), params, PullRequest.class);
    }
    
    public PullRequest createPullRequest(final IRepositoryIdProvider repository, final int issueId, final String head, final String base) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/pulls");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("issue", issueId);
        params.put("head", head);
        params.put("base", base);
        return this.client.post(uri.toString(), params, PullRequest.class);
    }
    
    public PullRequest editPullRequest(final IRepositoryIdProvider repository, final PullRequest request) throws IOException {
        final String id = this.getId(repository);
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/pulls");
        uri.append('/').append(request.getNumber());
        final Map<String, String> params = (Map<String, String>)this.editPrMap(request);
        return this.client.post(uri.toString(), params, PullRequest.class);
    }
    
    public List<RepositoryCommit> getCommits(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        uri.append("/commits");
        final PagedRequest<RepositoryCommit> request = (PagedRequest<RepositoryCommit>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new PullRequestService$2(this).getType());
        return (List<RepositoryCommit>)this.getAll((PagedRequest)request);
    }
    
    public List<CommitFile> getFiles(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        uri.append("/files");
        final PagedRequest<CommitFile> request = (PagedRequest<CommitFile>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new PullRequestService$3(this).getType());
        return (List<CommitFile>)this.getAll((PagedRequest)request);
    }
    
    public boolean isMerged(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        uri.append("/merge");
        return this.check(uri.toString());
    }
    
    public MergeStatus merge(final IRepositoryIdProvider repository, final int id, final String commitMessage) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        uri.append("/merge");
        return this.client.put(uri.toString(), Collections.singletonMap("commit_message", commitMessage), MergeStatus.class);
    }
    
    public List<CommitComment> getComments(final IRepositoryIdProvider repository, final int id) throws IOException {
        return (List<CommitComment>)this.getAll(this.pageComments(repository, id));
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final int id) {
        return (PageIterator<CommitComment>)this.pageComments(repository, id, 100);
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final int id, final int size) {
        return (PageIterator<CommitComment>)this.pageComments(repository, id, 1, size);
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final int id, final int start, final int size) {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        uri.append("/comments");
        final PagedRequest<CommitComment> request = (PagedRequest<CommitComment>)this.createPagedRequest(start, size);
        request.setUri(uri);
        request.setType(new PullRequestService$4(this).getType());
        return (PageIterator<CommitComment>)this.createPageIterator((PagedRequest)request);
    }
    
    public CommitComment getComment(final IRepositoryIdProvider repository, final long commentId) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append("/comments");
        uri.append('/').append(commentId);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(CommitComment.class);
        return (CommitComment)this.client.get(request).getBody();
    }
    
    public CommitComment createComment(final IRepositoryIdProvider repository, final int id, final CommitComment comment) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(id);
        uri.append("/comments");
        return this.client.post(uri.toString(), comment, CommitComment.class);
    }
    
    public CommitComment replyToComment(final IRepositoryIdProvider repository, final int pullRequestId, final int commentId, final String body) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append('/').append(pullRequestId);
        uri.append("/comments");
        final Map<String, String> params = new HashMap<String, String>();
        params.put("in_reply_to", Integer.toString(commentId));
        params.put("body", body);
        return this.client.post(uri.toString(), params, CommitComment.class);
    }
    
    public CommitComment editComment(final IRepositoryIdProvider repository, final CommitComment comment) throws IOException {
        final String repoId = this.getId(repository);
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append("/comments");
        uri.append('/').append(comment.getId());
        return this.client.post(uri.toString(), comment, CommitComment.class);
    }
    
    public void deleteComment(final IRepositoryIdProvider repository, final long commentId) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/pulls");
        uri.append("/comments");
        uri.append('/').append(commentId);
        this.client.delete(uri.toString());
    }
}
