// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.Tag;
import java.util.Iterator;
import java.util.ArrayList;
import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.TypedResource;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.util.List;
import org.eclipse.egit.github.core.Reference;
import java.util.HashMap;
import org.eclipse.egit.github.core.TreeEntry;
import java.util.Collection;
import java.util.Map;
import java.util.Collections;
import org.eclipse.egit.github.core.Tree;
import org.eclipse.egit.github.core.ShaResource;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.Blob;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class DataService extends GitHubService
{
    public DataService() {
    }
    
    public DataService(final GitHubClient client) {
        super(client);
    }
    
    public Blob getBlob(final IRepositoryIdProvider repository, final String sha) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("SHA-1 cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("SHA-1 cannot be empty");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/blobs");
        uri.append('/').append(sha);
        final GitHubRequest request = this.createRequest();
        request.setType((Type)Blob.class);
        request.setUri(uri);
        return (Blob)this.client.get(request).getBody();
    }
    
    public String createBlob(final IRepositoryIdProvider repository, final Blob blob) throws IOException {
        final String id = this.getId(repository);
        if (blob == null) {
            throw new IllegalArgumentException("Blob cannot be null");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/blobs");
        final ShaResource created = this.client.post(uri.toString(), blob, ShaResource.class);
        return (created != null) ? created.getSha() : null;
    }
    
    public Tree getTree(final IRepositoryIdProvider repository, final String sha) throws IOException {
        return this.getTree(repository, sha, false);
    }
    
    public Tree getTree(final IRepositoryIdProvider repository, final String sha, final boolean recursive) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("SHA-1 cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("SHA-1 cannot be empty");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/trees");
        uri.append('/').append(sha);
        final GitHubRequest request = this.createRequest();
        request.setType((Type)Tree.class);
        request.setUri(uri);
        if (recursive) {
            request.setParams((Map)Collections.singletonMap("recursive", "1"));
        }
        return (Tree)this.client.get(request).getBody();
    }
    
    public Tree createTree(final IRepositoryIdProvider repository, final Collection<TreeEntry> entries) throws IOException {
        return this.createTree(repository, entries, null);
    }
    
    public Tree createTree(final IRepositoryIdProvider repository, final Collection<TreeEntry> entries, final String baseTree) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/trees");
        final GitHubRequest request = this.createRequest();
        request.setType((Type)Tree.class);
        request.setUri(uri);
        final Map<String, Object> params = new HashMap<String, Object>();
        if (entries != null) {
            params.put("tree", entries.toArray());
        }
        if (baseTree != null) {
            params.put("base_tree", baseTree);
        }
        return this.client.post(uri.toString(), params, Tree.class);
    }
    
    public Reference getReference(final IRepositoryIdProvider repository, final String name) throws IOException {
        final String id = this.getId(repository);
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        if (!name.startsWith("refs/")) {
            uri.append("/refs");
        }
        uri.append('/').append(name);
        final GitHubRequest request = this.createRequest();
        request.setType((Type)Reference.class);
        request.setUri(uri);
        return (Reference)this.client.get(request).getBody();
    }
    
    public List<Reference> getReferences(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/refs");
        final PagedRequest<Reference> request = (PagedRequest<Reference>)this.createPagedRequest();
        request.setType(new DataService$1(this).getType());
        request.setUri(uri);
        return (List<Reference>)this.getAll((PagedRequest)request);
    }
    
    public Reference createReference(final IRepositoryIdProvider repository, final Reference reference) throws IOException {
        final String id = this.getId(repository);
        if (reference == null) {
            throw new IllegalArgumentException("Reference cannot be null");
        }
        final TypedResource object = reference.getObject();
        if (object == null) {
            throw new IllegalArgumentException("Reference object cannot be null");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/refs");
        final Map<String, String> params = new HashMap<String, String>();
        params.put("sha", object.getSha());
        params.put("ref", reference.getRef());
        return this.client.post(uri.toString(), params, Reference.class);
    }
    
    public Reference editReference(final IRepositoryIdProvider repository, final Reference reference) throws IOException {
        return this.editReference(repository, reference, false);
    }
    
    public Reference editReference(final IRepositoryIdProvider repository, final Reference reference, final boolean force) throws IOException {
        final String id = this.getId(repository);
        if (reference == null) {
            throw new IllegalArgumentException("Reference cannot be null");
        }
        final TypedResource object = reference.getObject();
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        final String ref = reference.getRef();
        if (ref == null) {
            throw new IllegalArgumentException("Ref cannot be null");
        }
        if (ref.length() == 0) {
            throw new IllegalArgumentException("Ref cannot be empty");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        if (!ref.startsWith("refs/")) {
            uri.append("/refs");
        }
        uri.append('/').append(ref);
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("sha", object.getSha());
        if (force) {
            params.put("force", true);
        }
        return this.client.post(uri.toString(), params, Reference.class);
    }
    
    public Commit getCommit(final IRepositoryIdProvider repository, final String sha) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("SHA-1 cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("SHA-1 cannot be empty");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/commits");
        uri.append('/').append(sha);
        final GitHubRequest request = this.createRequest();
        request.setType((Type)Commit.class);
        request.setUri(uri);
        return (Commit)this.client.get(request).getBody();
    }
    
    public Commit createCommit(final IRepositoryIdProvider repository, final Commit commit) throws IOException {
        final String id = this.getId(repository);
        if (commit == null) {
            throw new IllegalArgumentException("Commit cannot be null");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/commits");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("author", commit.getAuthor());
        params.put("committer", commit.getCommitter());
        params.put("message", commit.getMessage());
        final List<Commit> parents = commit.getParents();
        if (parents != null && parents.size() > 0) {
            final List<String> parentIds = new ArrayList<String>();
            for (final Commit parent : parents) {
                parentIds.add(parent.getSha());
            }
            params.put("parents", parentIds);
        }
        final Tree tree = commit.getTree();
        if (tree != null) {
            params.put("tree", tree.getSha());
        }
        return this.client.post(uri.toString(), params, Commit.class);
    }
    
    public Tag getTag(final IRepositoryIdProvider repository, final String sha) throws IOException {
        final String id = this.getId(repository);
        if (sha == null) {
            throw new IllegalArgumentException("SHA-1 cannot be null");
        }
        if (sha.length() == 0) {
            throw new IllegalArgumentException("SHA-1 cannot be empty");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/tags");
        uri.append('/').append(sha);
        final GitHubRequest request = this.createRequest();
        request.setType((Type)Tag.class);
        request.setUri(uri);
        return (Tag)this.client.get(request).getBody();
    }
    
    public Tag createTag(final IRepositoryIdProvider repository, final Tag tag) throws IOException {
        final String id = this.getId(repository);
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }
        final StringBuilder uri = new StringBuilder();
        uri.append("/repos");
        uri.append('/').append(id);
        uri.append("/git");
        uri.append("/tags");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("tag", tag.getTag());
        params.put("message", tag.getMessage());
        final TypedResource object = tag.getObject();
        if (object != null) {
            params.put("object", object.getSha());
            params.put("type", object.getType());
        }
        params.put("tagger", tag.getTagger());
        return this.client.post(uri.toString(), params, Tag.class);
    }
}
