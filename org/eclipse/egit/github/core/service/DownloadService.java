// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.io.FileInputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.util.Map;
import org.eclipse.egit.github.core.util.MultiPartUtils;
import java.util.LinkedHashMap;
import java.io.InputStream;
import org.eclipse.egit.github.core.DownloadResource;
import org.eclipse.egit.github.core.client.PageIterator;
import java.util.List;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.Download;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class DownloadService extends GitHubService
{
    public static final String UPLOAD_KEY = "key";
    public static final String UPLOAD_ACL = "acl";
    public static final String UPLOAD_SUCCESS_ACTION_STATUS = "success_action_status";
    public static final String UPLOAD_FILENAME = "Filename";
    public static final String UPLOAD_AWS_ACCESS_KEY_ID = "AWSAccessKeyId";
    public static final String UPLOAD_POLICY = "Policy";
    public static final String UPLOAD_SIGNATURE = "Signature";
    public static final String UPLOAD_FILE = "file";
    public static final String UPLOAD_CONTENT_TYPE = "Content-Type";
    
    public DownloadService() {
    }
    
    public DownloadService(final GitHubClient client) {
        super(client);
    }
    
    public Download getDownload(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/downloads");
        uri.append('/').append(id);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType((Type)Download.class);
        return (Download)this.client.get(request).getBody();
    }
    
    protected PagedRequest<Download> createDownloadsRequest(final IRepositoryIdProvider repository, final int start, final int size) {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/downloads");
        final PagedRequest<Download> request = (PagedRequest<Download>)this.createPagedRequest(start, size);
        request.setType(new DownloadService$1(this).getType());
        request.setUri(uri);
        return request;
    }
    
    public List<Download> getDownloads(final IRepositoryIdProvider repository) throws IOException {
        return (List<Download>)this.getAll(this.pageDownloads(repository));
    }
    
    public PageIterator<Download> pageDownloads(final IRepositoryIdProvider repository) {
        return (PageIterator<Download>)this.pageDownloads(repository, 100);
    }
    
    public PageIterator<Download> pageDownloads(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<Download>)this.pageDownloads(repository, 1, size);
    }
    
    public PageIterator<Download> pageDownloads(final IRepositoryIdProvider repository, final int start, final int size) {
        final PagedRequest<Download> request = (PagedRequest<Download>)this.createDownloadsRequest(repository, start, size);
        return (PageIterator<Download>)this.createPageIterator((PagedRequest)request);
    }
    
    public void deleteDownload(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/downloads");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
    
    public DownloadResource createResource(final IRepositoryIdProvider repository, final Download download) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/downloads");
        return this.client.post(uri.toString(), download, DownloadResource.class);
    }
    
    public void uploadResource(final DownloadResource resource, final InputStream content, final long size) throws IOException {
        if (resource == null) {
            throw new IllegalArgumentException("Download resource cannot be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content input stream cannot be null");
        }
        final Map<String, Object> parts = new LinkedHashMap<String, Object>();
        parts.put("key", resource.getPath());
        parts.put("acl", resource.getAcl());
        parts.put("success_action_status", Integer.toString(201));
        parts.put("Filename", resource.getName());
        parts.put("AWSAccessKeyId", resource.getAccesskeyid());
        parts.put("Policy", resource.getPolicy());
        parts.put("Signature", resource.getSignature());
        parts.put("Content-Type", resource.getMimeType());
        parts.put("file", content);
        final HttpURLConnection connection = MultiPartUtils.post(resource.getS3Url(), (Map)parts);
        final int status = connection.getResponseCode();
        if (status != 201) {
            throw new IOException("Unexpected response status of " + status);
        }
    }
    
    public DownloadResource createDownload(final IRepositoryIdProvider repository, final Download download, final InputStream content, final long size) throws IOException {
        final DownloadResource resource = this.createResource(repository, download);
        this.uploadResource(resource, content, size);
        return resource;
    }
    
    public DownloadResource createDownload(final IRepositoryIdProvider repository, final Download download, final File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        return this.createDownload(repository, download, new FileInputStream(file), file.length());
    }
}
