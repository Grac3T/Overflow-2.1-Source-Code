// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.IResourceProvider;
import java.net.URLEncoder;
import org.eclipse.egit.github.core.SearchIssue;
import org.eclipse.egit.github.core.IssueEvent;
import java.util.Iterator;
import org.eclipse.egit.github.core.Milestone;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.Label;
import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.client.PageIterator;
import java.io.IOException;
import java.util.Map;
import org.eclipse.egit.github.core.RepositoryIssue;
import java.util.List;
import org.eclipse.egit.github.core.client.GitHubClient;

public class IssueService extends GitHubService
{
    public static final String FIELD_FILTER = "filter";
    public static final String FILTER_ASSIGNEE = "assignee";
    public static final String FILTER_MILESTONE = "milestone";
    public static final String FILTER_MENTIONED = "mentioned";
    public static final String FILTER_SUBSCRIBED = "subscribed";
    public static final String FILTER_CREATED = "created";
    public static final String FILTER_ASSIGNED = "assigned";
    public static final String FILTER_LABELS = "labels";
    public static final String FILTER_STATE = "state";
    public static final String STATE_OPEN = "open";
    public static final String STATE_CLOSED = "closed";
    public static final String FIELD_BODY = "body";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_SINCE = "since";
    public static final String FIELD_DIRECTION = "direction";
    public static final String DIRECTION_ASCENDING = "asc";
    public static final String DIRECTION_DESCENDING = "desc";
    public static final String FIELD_SORT = "sort";
    public static final String SORT_CREATED = "created";
    public static final String SORT_UPDATED = "updated";
    public static final String SORT_COMMENTS = "comments";
    
    public IssueService() {
    }
    
    public IssueService(final GitHubClient client) {
        super(client);
    }
    
    public List<RepositoryIssue> getIssues() throws IOException {
        return (List<RepositoryIssue>)this.getIssues(null);
    }
    
    public List<RepositoryIssue> getIssues(final Map<String, String> filterData) throws IOException {
        return (List<RepositoryIssue>)this.getAll(this.pageIssues(filterData));
    }
    
    public PageIterator<RepositoryIssue> pageIssues() {
        return (PageIterator<RepositoryIssue>)this.pageIssues((Map)null);
    }
    
    public PageIterator<RepositoryIssue> pageIssues(final Map<String, String> filterData) {
        return (PageIterator<RepositoryIssue>)this.pageIssues(filterData, 100);
    }
    
    public PageIterator<RepositoryIssue> pageIssues(final Map<String, String> filterData, final int size) {
        return (PageIterator<RepositoryIssue>)this.pageIssues(filterData, 1, size);
    }
    
    public PageIterator<RepositoryIssue> pageIssues(final Map<String, String> filterData, final int start, final int size) {
        final PagedRequest<RepositoryIssue> request = (PagedRequest<RepositoryIssue>)this.createPagedRequest(start, size);
        request.setParams(filterData);
        request.setUri("/issues");
        request.setType(new IssueService$1(this).getType());
        return (PageIterator<RepositoryIssue>)this.createPageIterator((PagedRequest)request);
    }
    
    public Issue getIssue(final String user, final String repository, final int issueNumber) throws IOException {
        return this.getIssue(user, repository, Integer.toString(issueNumber));
    }
    
    public Issue getIssue(final String user, final String repository, final String issueNumber) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.getIssue(repoId, issueNumber);
    }
    
    public Issue getIssue(final IRepositoryIdProvider repository, final int issueNumber) throws IOException {
        return this.getIssue(repository, Integer.toString(issueNumber));
    }
    
    public Issue getIssue(final IRepositoryIdProvider repository, final String issueNumber) throws IOException {
        final String repoId = this.getId(repository);
        return this.getIssue(repoId, issueNumber);
    }
    
    private Issue getIssue(final String repoId, final String issueNumber) throws IOException {
        if (issueNumber == null) {
            throw new IllegalArgumentException("Issue number cannot be null");
        }
        if (issueNumber.length() == 0) {
            throw new IllegalArgumentException("Issue number cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues");
        uri.append('/').append(issueNumber);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(Issue.class);
        return (Issue)this.client.get(request).getBody();
    }
    
    public List<Comment> getComments(final String user, final String repository, final int issueNumber) throws IOException {
        return (List<Comment>)this.getComments(user, repository, Integer.toString(issueNumber));
    }
    
    public List<Comment> getComments(final String user, final String repository, final String issueNumber) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return (List<Comment>)this.getComments(repoId, issueNumber);
    }
    
    public List<Comment> getComments(final IRepositoryIdProvider repository, final int issueNumber) throws IOException {
        return (List<Comment>)this.getComments(repository, Integer.toString(issueNumber));
    }
    
    public List<Comment> getComments(final IRepositoryIdProvider repository, final String issueNumber) throws IOException {
        final String repoId = this.getId(repository);
        return (List<Comment>)this.getComments(repoId, issueNumber);
    }
    
    private List<Comment> getComments(final String repoId, final String issueNumber) throws IOException {
        if (issueNumber == null) {
            throw new IllegalArgumentException("Issue number cannot be null");
        }
        if (issueNumber.length() == 0) {
            throw new IllegalArgumentException("Issue number cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues");
        uri.append('/').append(issueNumber);
        uri.append("/comments");
        final PagedRequest<Comment> request = (PagedRequest<Comment>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new IssueService$2(this).getType());
        return (List<Comment>)this.getAll((PagedRequest)request);
    }
    
    protected PagedRequest<Issue> createIssuesRequest(final String repoId, final Map<String, String> filterData, final int start, final int size) {
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues");
        final PagedRequest<Issue> request = (PagedRequest<Issue>)this.createPagedRequest(start, size);
        request.setParams(filterData).setUri(uri);
        request.setType(new IssueService$3(this).getType());
        return request;
    }
    
    public List<Issue> getIssues(final String user, final String repository, final Map<String, String> filterData) throws IOException {
        return (List<Issue>)this.getAll(this.pageIssues(user, repository, filterData));
    }
    
    public List<Issue> getIssues(final IRepositoryIdProvider repository, final Map<String, String> filterData) throws IOException {
        return (List<Issue>)this.getAll(this.pageIssues(repository, filterData));
    }
    
    public PageIterator<Issue> pageIssues(final String user, final String repository) {
        return (PageIterator<Issue>)this.pageIssues(user, repository, null);
    }
    
    public PageIterator<Issue> pageIssues(final String user, final String repository, final Map<String, String> filterData) {
        return (PageIterator<Issue>)this.pageIssues(user, repository, filterData, 100);
    }
    
    public PageIterator<Issue> pageIssues(final String user, final String repository, final Map<String, String> filterData, final int size) {
        return (PageIterator<Issue>)this.pageIssues(user, repository, filterData, 1, size);
    }
    
    public PageIterator<Issue> pageIssues(final String user, final String repository, final Map<String, String> filterData, final int start, final int size) {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        final PagedRequest<Issue> request = (PagedRequest<Issue>)this.createIssuesRequest(repoId, filterData, start, size);
        return (PageIterator<Issue>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Issue> pageIssues(final IRepositoryIdProvider repository) {
        return (PageIterator<Issue>)this.pageIssues(repository, null);
    }
    
    public PageIterator<Issue> pageIssues(final IRepositoryIdProvider repository, final Map<String, String> filterData) {
        return (PageIterator<Issue>)this.pageIssues(repository, filterData, 100);
    }
    
    public PageIterator<Issue> pageIssues(final IRepositoryIdProvider repository, final Map<String, String> filterData, final int size) {
        return (PageIterator<Issue>)this.pageIssues(repository, filterData, 1, size);
    }
    
    public PageIterator<Issue> pageIssues(final IRepositoryIdProvider repository, final Map<String, String> filterData, final int start, final int size) {
        final String repoId = this.getId(repository);
        final PagedRequest<Issue> request = (PagedRequest<Issue>)this.createIssuesRequest(repoId, filterData, start, size);
        return (PageIterator<Issue>)this.createPageIterator((PagedRequest)request);
    }
    
    protected Map<Object, Object> createIssueMap(final Issue issue, final boolean newIssue) {
        final Map<Object, Object> params = new HashMap<Object, Object>();
        if (issue != null) {
            params.put("body", issue.getBody());
            params.put("title", issue.getTitle());
            final User assignee = issue.getAssignee();
            if (assignee != null) {
                params.put("assignee", assignee.getLogin());
            }
            final Milestone milestone = issue.getMilestone();
            if (milestone != null) {
                final int number = milestone.getNumber();
                if (number > 0) {
                    params.put("milestone", Integer.toString(number));
                }
                else if (!newIssue) {
                    params.put("milestone", "");
                }
            }
            final List<Label> labels = issue.getLabels();
            if (labels != null) {
                final List<String> labelNames = new ArrayList<String>(labels.size());
                for (final Label label : labels) {
                    labelNames.add(label.getName());
                }
                params.put("labels", labelNames);
            }
        }
        return params;
    }
    
    public Issue createIssue(final String user, final String repository, final Issue issue) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.createIssue(repoId, issue);
    }
    
    public Issue createIssue(final IRepositoryIdProvider repository, final Issue issue) throws IOException {
        final String repoId = this.getId(repository);
        return this.createIssue(repoId, issue);
    }
    
    private Issue createIssue(final String repoId, final Issue issue) throws IOException {
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues");
        final Map<Object, Object> params = (Map<Object, Object>)this.createIssueMap(issue, true);
        return this.client.post(uri.toString(), params, Issue.class);
    }
    
    public Issue editIssue(final String user, final String repository, final Issue issue) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.editIssue(repoId, issue);
    }
    
    public Issue editIssue(final IRepositoryIdProvider repository, final Issue issue) throws IOException {
        final String repoId = this.getId(repository);
        return this.editIssue(repoId, issue);
    }
    
    private Issue editIssue(final String repoId, final Issue issue) throws IOException {
        if (issue == null) {
            throw new IllegalArgumentException("Issue cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues");
        uri.append('/').append(issue.getNumber());
        final Map<Object, Object> params = (Map<Object, Object>)this.createIssueMap(issue, false);
        final String state = issue.getState();
        if (state != null) {
            params.put("state", state);
        }
        return this.client.post(uri.toString(), params, Issue.class);
    }
    
    public Comment createComment(final String user, final String repository, final int issueNumber, final String comment) throws IOException {
        return this.createComment(user, repository, Integer.toString(issueNumber), comment);
    }
    
    public Comment createComment(final String user, final String repository, final String issueNumber, final String comment) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.createComment(repoId, issueNumber, comment);
    }
    
    public Comment createComment(final IRepositoryIdProvider repository, final int issueNumber, final String comment) throws IOException {
        return this.createComment(repository, Integer.toString(issueNumber), comment);
    }
    
    public Comment createComment(final IRepositoryIdProvider repository, final String issueNumber, final String comment) throws IOException {
        final String repoId = this.getId(repository);
        return this.createComment(repoId, issueNumber, comment);
    }
    
    private Comment createComment(final String repoId, final String issueNumber, final String comment) throws IOException {
        if (issueNumber == null) {
            throw new IllegalArgumentException("Issue number cannot be null");
        }
        if (issueNumber.length() == 0) {
            throw new IllegalArgumentException("Issue number cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues");
        uri.append('/').append(issueNumber);
        uri.append("/comments");
        final Map<String, String> params = new HashMap<String, String>(1, 1.0f);
        params.put("body", comment);
        return this.client.post(uri.toString(), params, Comment.class);
    }
    
    public Comment getComment(final String user, final String repository, final long commentId) throws IOException {
        this.verifyRepository(user, repository);
        final GitHubRequest request = this.createRequest();
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(user).append('/').append(repository);
        uri.append("/issues").append("/comments");
        uri.append('/').append(commentId);
        request.setUri(uri);
        request.setType(Comment.class);
        return (Comment)this.client.get(request).getBody();
    }
    
    public Comment editComment(final String user, final String repository, final Comment comment) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.editComment(repoId, comment);
    }
    
    public Comment editComment(final IRepositoryIdProvider repository, final Comment comment) throws IOException {
        final String repoId = this.getId(repository);
        return this.editComment(repoId, comment);
    }
    
    private Comment editComment(final String repoId, final Comment comment) throws IOException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues").append("/comments");
        uri.append('/').append(comment.getId());
        return this.client.post(uri.toString(), comment, Comment.class);
    }
    
    public void deleteComment(final String user, final String repository, final long commentId) throws IOException {
        this.deleteComment(user, repository, Long.toString(commentId));
    }
    
    public void deleteComment(final String user, final String repository, final String commentId) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        this.deleteComment(repoId, commentId);
    }
    
    public void deleteComment(final IRepositoryIdProvider repository, final long commentId) throws IOException {
        this.deleteComment(repository, Long.toString(commentId));
    }
    
    public void deleteComment(final IRepositoryIdProvider repository, final String commentId) throws IOException {
        final String repoId = this.getId(repository);
        this.deleteComment(repoId, commentId);
    }
    
    private void deleteComment(final String repoId, final String commentId) throws IOException {
        if (commentId == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (commentId.length() == 0) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/issues").append("/comments");
        uri.append('/').append(commentId);
        this.client.delete(uri.toString());
    }
    
    public PageIterator<IssueEvent> pageEvents(final String user, final String repository) {
        return (PageIterator<IssueEvent>)this.pageEvents(user, repository, 100);
    }
    
    public PageIterator<IssueEvent> pageEvents(final String user, final String repository, final int size) {
        return (PageIterator<IssueEvent>)this.pageEvents(user, repository, 1, size);
    }
    
    public PageIterator<IssueEvent> pageEvents(final String user, final String repository, final int start, final int size) {
        this.verifyRepository(user, repository);
        final PagedRequest<IssueEvent> request = (PagedRequest<IssueEvent>)this.createPagedRequest(start, size);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(user).append('/').append(repository);
        uri.append("/issues");
        uri.append("/events");
        request.setUri(uri);
        request.setType(new IssueService$4(this).getType());
        return (PageIterator<IssueEvent>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<IssueEvent> pageIssueEvents(final String user, final String repository, final int issueId) {
        return (PageIterator<IssueEvent>)this.pageIssueEvents(user, repository, issueId, 100);
    }
    
    public PageIterator<IssueEvent> pageIssueEvents(final String user, final String repository, final int issueId, final int size) {
        return (PageIterator<IssueEvent>)this.pageIssueEvents(user, repository, issueId, 1, size);
    }
    
    public PageIterator<IssueEvent> pageIssueEvents(final String user, final String repository, final int issueId, final int start, final int size) {
        this.verifyRepository(user, repository);
        final PagedRequest<IssueEvent> request = (PagedRequest<IssueEvent>)this.createPagedRequest(start, size);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(user).append('/').append(repository);
        uri.append("/issues");
        uri.append('/').append(issueId);
        uri.append("/events");
        request.setUri(uri);
        request.setType(new IssueService$5(this).getType());
        return (PageIterator<IssueEvent>)this.createPageIterator((PagedRequest)request);
    }
    
    public IssueEvent getIssueEvent(final String user, final String repository, final long eventId) throws IOException {
        this.verifyRepository(user, repository);
        final GitHubRequest request = this.createRequest();
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(user).append('/').append(repository);
        uri.append("/issues");
        uri.append("/events");
        uri.append('/').append(eventId);
        request.setUri(uri);
        request.setType(IssueEvent.class);
        return (IssueEvent)this.client.get(request).getBody();
    }
    
    public List<SearchIssue> searchIssues(final IRepositoryIdProvider repository, final String state, final String query) throws IOException {
        final String id = this.getId(repository);
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        if (state.length() == 0) {
            throw new IllegalArgumentException("State cannot be empty");
        }
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }
        if (query.length() == 0) {
            throw new IllegalArgumentException("Query cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/legacy/issues/search");
        uri.append('/').append(id);
        uri.append('/').append(state);
        final String encodedQuery = URLEncoder.encode(query, "UTF-8").replace("+", "%20").replace(".", "%2E");
        uri.append('/').append(encodedQuery);
        final PagedRequest<SearchIssue> request = (PagedRequest<SearchIssue>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(IssueContainer.class);
        return (List<SearchIssue>)this.getAll((PagedRequest)request);
    }
    
    private static class IssueContainer implements IResourceProvider<SearchIssue>
    {
        private List<SearchIssue> issues;
        
        @Override
        public List<SearchIssue> getResources() {
            return (List<SearchIssue>)this.issues;
        }
    }
}
