// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.CommitStatus;
import org.eclipse.egit.github.core.RepositoryCommitCompare;
import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.egit.github.core.client.PageIterator;
import java.io.IOException;
import org.eclipse.egit.github.core.RepositoryCommit;
import java.util.List;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class CommitService extends GitHubService
{
    public CommitService() {
    }
    
    public CommitService(final GitHubClient client) {
        super(client);
    }
    
    public List<RepositoryCommit> getCommits(final IRepositoryIdProvider repository) throws IOException {
        return (List<RepositoryCommit>)this.getCommits(repository, null, null);
    }
    
    public List<RepositoryCommit> getCommits(final IRepositoryIdProvider repository, final String sha, final String path) throws IOException {
        return (List<RepositoryCommit>)this.getAll(this.pageCommits(repository, sha, path));
    }
    
    public PageIterator<RepositoryCommit> pageCommits(final IRepositoryIdProvider repository) {
        return (PageIterator<RepositoryCommit>)this.pageCommits(repository, null, null);
    }
    
    public PageIterator<RepositoryCommit> pageCommits(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<RepositoryCommit>)this.pageCommits(repository, null, null, size);
    }
    
    public PageIterator<RepositoryCommit> pageCommits(final IRepositoryIdProvider repository, final String sha, final String path) {
        return (PageIterator<RepositoryCommit>)this.pageCommits(repository, sha, path, 100);
    }
    
    public PageIterator<RepositoryCommit> pageCommits(final IRepositoryIdProvider repository, final String sha, final String path, final int size) {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/commits");
        final PagedRequest<RepositoryCommit> request = (PagedRequest<RepositoryCommit>)this.createPagedRequest(1, size);
        request.setUri(uri);
        request.setType(new CommitService$1(this).getType());
        if (sha != null || path != null) {
            final Map<String, String> params = new HashMap<String, String>();
            if (sha != null) {
                params.put("sha", sha);
            }
            if (path != null) {
                params.put("path", path);
            }
            request.setParams((Map)params);
        }
        return (PageIterator<RepositoryCommit>)this.createPageIterator((PagedRequest)request);
    }
    
    public RepositoryCommit getCommit(final IRepositoryIdProvider repository, final String sha) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("Sha cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("Sha cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/commits");
        uri.append('/').append(sha);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType((Type)RepositoryCommit.class);
        return (RepositoryCommit)this.client.get(request).getBody();
    }
    
    public List<CommitComment> getComments(final IRepositoryIdProvider repository, final String sha) throws IOException {
        return (List<CommitComment>)this.getAll(this.pageComments(repository, sha));
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final String sha) {
        return (PageIterator<CommitComment>)this.pageComments(repository, sha, 100);
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final String sha, final int size) {
        return (PageIterator<CommitComment>)this.pageComments(repository, sha, 1, size);
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final String sha, final int start, final int size) {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("Sha cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("Sha cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/commits");
        uri.append('/').append(sha);
        uri.append("/comments");
        final PagedRequest<CommitComment> request = (PagedRequest<CommitComment>)this.createPagedRequest(start, size);
        request.setUri(uri);
        request.setType(new CommitService$2(this).getType());
        return (PageIterator<CommitComment>)this.createPageIterator((PagedRequest)request);
    }
    
    public CommitComment getComment(final IRepositoryIdProvider repository, final long commentId) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/comments");
        uri.append('/').append(commentId);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType((Type)CommitComment.class);
        return (CommitComment)this.client.get(request).getBody();
    }
    
    public CommitComment addComment(final IRepositoryIdProvider repository, final String sha, final CommitComment comment) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("Sha cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("Sha cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/commits");
        uri.append('/').append(sha);
        uri.append("/comments");
        return this.client.post(uri.toString(), comment, CommitComment.class);
    }
    
    public CommitComment editComment(final IRepositoryIdProvider repository, final CommitComment comment) throws IOException {
        final String id = this.getId(repository);
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/comments");
        uri.append('/').append(comment.getId());
        return this.client.post(uri.toString(), comment, CommitComment.class);
    }
    
    public void deleteComment(final IRepositoryIdProvider repository, final long commentId) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/comments");
        uri.append('/').append(commentId);
        this.client.delete(uri.toString());
    }
    
    public RepositoryCommitCompare compare(final IRepositoryIdProvider repository, final String base, final String head) throws IOException {
        final String id = this.getId(repository);
        if (base == null) {
            throw new IllegalArgumentException("Base cannot be null");
        }
        if (base.length() == 0) {
            throw new IllegalArgumentException("Base cannot be empty");
        }
        if (head == null) {
            throw new IllegalArgumentException("Head cannot be null");
        }
        if (head.length() == 0) {
            throw new IllegalArgumentException("Head cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/compare");
        uri.append('/').append(base).append("...").append(head);
        final GitHubRequest request = this.createRequest();
        request.setType((Type)RepositoryCommitCompare.class);
        request.setUri(uri);
        return (RepositoryCommitCompare)this.client.get(request).getBody();
    }
    
    public List<CommitStatus> getStatuses(final IRepositoryIdProvider repository, final String sha) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("SHA-1 cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("SHA-1 cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/statuses");
        uri.append('/').append(sha);
        final PagedRequest<CommitStatus> request = (PagedRequest<CommitStatus>)this.createPagedRequest();
        request.setType(new CommitService$3(this).getType());
        request.setUri(uri);
        return (List<CommitStatus>)this.getAll((PagedRequest)request);
    }
    
    public CommitStatus createStatus(final IRepositoryIdProvider repository, final String sha, final CommitStatus status) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("SHA-1 cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("SHA-1 cannot be empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        final Map<String, String> params = new HashMap<String, String>(3, 1.0f);
        if (status.getState() != null) {
            params.put("state", status.getState());
        }
        if (status.getTargetUrl() != null) {
            params.put("target_url", status.getTargetUrl());
        }
        if (status.getDescription() != null) {
            params.put("description", status.getDescription());
        }
        if (status.getContext() != null) {
            params.put("context", status.getContext());
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/statuses");
        uri.append('/').append(sha);
        return this.client.post(uri.toString(), params, CommitStatus.class);
    }
    
    public List<CommitComment> getComments(final IRepositoryIdProvider repository) throws IOException {
        return (List<CommitComment>)this.getAll(this.pageComments(repository));
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository) {
        return (PageIterator<CommitComment>)this.pageComments(repository, 100);
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<CommitComment>)this.pageComments(repository, 1, size);
    }
    
    public PageIterator<CommitComment> pageComments(final IRepositoryIdProvider repository, final int start, final int size) {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/comments");
        final PagedRequest<CommitComment> request = (PagedRequest<CommitComment>)this.createPagedRequest(start, size);
        request.setUri(uri);
        request.setType(new CommitService$4(this).getType());
        return (PageIterator<CommitComment>)this.createPageIterator((PagedRequest)request);
    }
}
