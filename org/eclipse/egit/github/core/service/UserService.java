// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.Key;
import org.eclipse.egit.github.core.client.PageIterator;
import java.util.List;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;

public class UserService extends GitHubService
{
    public UserService() {
    }
    
    public UserService(final GitHubClient client) {
        super(client);
    }
    
    public User getUser(final String login) throws IOException {
        if (login == null) {
            throw new IllegalArgumentException("Login name cannot be null");
        }
        if (login.length() == 0) {
            throw new IllegalArgumentException("Login name cannot be empty");
        }
        final GitHubRequest request = this.createRequest();
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(login);
        request.setUri(uri);
        request.setType(User.class);
        return (User)this.client.get(request).getBody();
    }
    
    public User getUser() throws IOException {
        final GitHubRequest request = this.createRequest();
        request.setUri("/user");
        request.setType(User.class);
        return (User)this.client.get(request).getBody();
    }
    
    public User editUser(final User user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return this.client.post("/user", user, User.class);
    }
    
    protected PagedRequest<User> createFollowersRequest(final int start, final int size, final String user) {
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest(start, size);
        if (user == null) {
            request.setUri("/user/followers");
        }
        else {
            final StringBuilder uri = new StringBuilder("/users");
            uri.append('/').append(user);
            uri.append("/followers");
            request.setUri(uri);
        }
        request.setType(new UserService$1(this).getType());
        return request;
    }
    
    protected PagedRequest<User> createFollowingRequest(final int start, final int size, final String user) {
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest(start, size);
        if (user == null) {
            request.setUri("/user/following");
        }
        else {
            final StringBuilder uri = new StringBuilder("/users");
            uri.append('/').append(user);
            uri.append("/following");
            request.setUri(uri);
        }
        request.setType(new UserService$2(this).getType());
        return request;
    }
    
    public List<User> getFollowers() throws IOException {
        return (List<User>)this.getAll(this.pageFollowers());
    }
    
    public PageIterator<User> pageFollowers() {
        return (PageIterator<User>)this.pageFollowers(100);
    }
    
    public PageIterator<User> pageFollowers(final int size) {
        return (PageIterator<User>)this.pageFollowers(1, size);
    }
    
    public PageIterator<User> pageFollowers(final int start, final int size) {
        final PagedRequest<User> request = (PagedRequest<User>)this.createFollowersRequest(start, size, null);
        return (PageIterator<User>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<User> getFollowers(final String user) throws IOException {
        return (List<User>)this.getAll(this.pageFollowers(user));
    }
    
    public PageIterator<User> pageFollowers(final String user) {
        return (PageIterator<User>)this.pageFollowers(user, 100);
    }
    
    public PageIterator<User> pageFollowers(final String user, final int size) {
        return (PageIterator<User>)this.pageFollowers(user, 1, size);
    }
    
    public PageIterator<User> pageFollowers(final String user, final int start, final int size) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final PagedRequest<User> request = (PagedRequest<User>)this.createFollowersRequest(start, size, user);
        return (PageIterator<User>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<User> getFollowing() throws IOException {
        return (List<User>)this.getAll(this.pageFollowing());
    }
    
    public PageIterator<User> pageFollowing() {
        return (PageIterator<User>)this.pageFollowing(100);
    }
    
    public PageIterator<User> pageFollowing(final int size) {
        return (PageIterator<User>)this.pageFollowing(1, size);
    }
    
    public PageIterator<User> pageFollowing(final int start, final int size) {
        final PagedRequest<User> request = (PagedRequest<User>)this.createFollowingRequest(start, size, null);
        return (PageIterator<User>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<User> getFollowing(final String user) throws IOException {
        return (List<User>)this.getAll(this.pageFollowing(user));
    }
    
    public PageIterator<User> pageFollowing(final String user) {
        return (PageIterator<User>)this.pageFollowing(user, 100);
    }
    
    public PageIterator<User> pageFollowing(final String user, final int size) {
        return (PageIterator<User>)this.pageFollowing(user, 1, size);
    }
    
    public PageIterator<User> pageFollowing(final String user, final int start, final int size) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final PagedRequest<User> request = (PagedRequest<User>)this.createFollowingRequest(start, size, user);
        return (PageIterator<User>)this.createPageIterator((PagedRequest)request);
    }
    
    public boolean isFollowing(final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/user/following");
        uri.append('/').append(user);
        return this.check(uri.toString());
    }
    
    public void follow(final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/user/following");
        uri.append('/').append(user);
        this.client.put(uri.toString());
    }
    
    public void unfollow(final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/user/following");
        uri.append('/').append(user);
        this.client.delete(uri.toString());
    }
    
    public List<String> getEmails() throws IOException {
        final PagedRequest<String> request = (PagedRequest<String>)this.createPagedRequest();
        request.setUri("/user/emails");
        request.setType(new UserService$3(this).getType());
        return (List<String>)this.getAll((PagedRequest)request);
    }
    
    public void addEmail(final String... emails) throws IOException {
        if (emails == null) {
            throw new IllegalArgumentException("Emails cannot be null");
        }
        if (emails.length == 0) {
            throw new IllegalArgumentException("Emails cannot be empty");
        }
        this.client.post("/user/emails", emails, null);
    }
    
    public void removeEmail(final String... emails) throws IOException {
        if (emails == null) {
            throw new IllegalArgumentException("Emails cannot be null");
        }
        if (emails.length == 0) {
            throw new IllegalArgumentException("Emails cannot be empty");
        }
        this.client.delete("/user/emails", emails);
    }
    
    public List<Key> getKeys() throws IOException {
        final PagedRequest<Key> request = (PagedRequest<Key>)this.createPagedRequest();
        request.setUri("/user/keys");
        request.setType(new UserService$4(this).getType());
        return (List<Key>)this.getAll((PagedRequest)request);
    }
    
    public Key getKey(final int id) throws IOException {
        final GitHubRequest request = this.createRequest();
        final StringBuilder uri = new StringBuilder("/user/keys");
        uri.append('/').append(id);
        request.setUri(uri);
        request.setType(Key.class);
        return (Key)this.client.get(request).getBody();
    }
    
    public Key createKey(final Key key) throws IOException {
        return this.client.post("/user/keys", key, Key.class);
    }
    
    public Key editKey(final Key key) throws IOException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/user/keys");
        uri.append('/').append(key.getId());
        return this.client.post(uri.toString(), key, Key.class);
    }
    
    public void deleteKey(final int id) throws IOException {
        final StringBuilder uri = new StringBuilder("/user/keys");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
}
