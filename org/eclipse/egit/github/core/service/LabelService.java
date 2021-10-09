// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.io.IOException;
import org.eclipse.egit.github.core.Label;
import java.util.List;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class LabelService extends GitHubService
{
    public LabelService() {
    }
    
    public LabelService(final GitHubClient client) {
        super(client);
    }
    
    public List<Label> getLabels(final IRepositoryIdProvider repository) throws IOException {
        final String repoId = this.getId(repository);
        return (List<Label>)this.getLabels(repoId);
    }
    
    public List<Label> getLabels(final String user, final String repository) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return (List<Label>)this.getLabels(repoId);
    }
    
    private List<Label> getLabels(final String id) throws IOException {
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/labels");
        final PagedRequest<Label> request = (PagedRequest<Label>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new LabelService$1(this).getType());
        return (List<Label>)this.getAll((PagedRequest)request);
    }
    
    public List<Label> setLabels(final IRepositoryIdProvider repository, final String issueId, final List<Label> labels) throws IOException {
        final String repoId = this.getId(repository);
        return (List<Label>)this.setLabels(repoId, issueId, labels);
    }
    
    public List<Label> setLabels(final String user, final String repository, final String issueId, final List<Label> labels) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return (List<Label>)this.setLabels(repoId, issueId, labels);
    }
    
    private List<Label> setLabels(final String id, final String issueId, final List<Label> labels) throws IOException {
        if (issueId == null) {
            throw new IllegalArgumentException("Issue id cannot be null");
        }
        if (issueId.length() == 0) {
            throw new IllegalArgumentException("Issue id cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/issues");
        uri.append('/').append(issueId);
        uri.append("/labels");
        return this.client.put(uri.toString(), labels, new LabelService$2(this).getType());
    }
    
    public Label createLabel(final IRepositoryIdProvider repository, final Label label) throws IOException {
        final String repoId = this.getId(repository);
        return this.createLabel(repoId, label);
    }
    
    public Label createLabel(final String user, final String repository, final Label label) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.createLabel(repoId, label);
    }
    
    private Label createLabel(final String id, final Label label) throws IOException {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/labels");
        return this.client.post(uri.toString(), label, Label.class);
    }
    
    public Label getLabel(final IRepositoryIdProvider repository, final String label) throws IOException {
        final String repoId = this.getId(repository);
        return this.getLabel(repoId, label);
    }
    
    public Label getLabel(final String user, final String repository, final String label) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.getLabel(repoId, label);
    }
    
    private Label getLabel(final String id, final String label) throws IOException {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null");
        }
        if (label.length() == 0) {
            throw new IllegalArgumentException("Label cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/labels");
        uri.append('/').append(label);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(Label.class);
        return (Label)this.client.get(request).getBody();
    }
    
    public void deleteLabel(final IRepositoryIdProvider repository, final String label) throws IOException {
        final String repoId = this.getId(repository);
        this.deleteLabel(repoId, label);
    }
    
    public void deleteLabel(final String user, final String repository, final String label) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        this.deleteLabel(repoId, label);
    }
    
    private void deleteLabel(final String id, final String label) throws IOException {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null");
        }
        if (label.length() == 0) {
            throw new IllegalArgumentException("Label cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/labels");
        uri.append('/').append(label);
        this.client.delete(uri.toString());
    }
    
    public Label editLabel(final IRepositoryIdProvider repository, final Label label) throws IOException {
        final String repoId = this.getId(repository);
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null");
        }
        final String name = label.getName();
        if (name == null) {
            throw new IllegalArgumentException("Label name cannot be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Label name cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/labels");
        uri.append('/').append(name);
        return this.client.post(uri.toString(), label, Label.class);
    }
}
