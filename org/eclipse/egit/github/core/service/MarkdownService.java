// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.util.Map;
import java.util.HashMap;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import org.eclipse.egit.github.core.client.GitHubClient;

public class MarkdownService extends GitHubService
{
    public static final String MODE_GFM = "gfm";
    public static final String MODE_MARKDOWN = "markdown";
    
    public MarkdownService() {
    }
    
    public MarkdownService(final GitHubClient client) {
        super(client);
    }
    
    private String readStream(final InputStream stream) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        try {
            final StringBuilder output = new StringBuilder();
            final char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer)) != -1) {
                output.append(buffer, 0, read);
            }
            return output.toString();
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public InputStream getRepositoryStream(final IRepositoryIdProvider repo, final String text) throws IOException {
        final String context = this.getId(repo);
        final Map<String, String> params = new HashMap<String, String>(3, 1.0f);
        params.put("context", context);
        params.put("text", text);
        params.put("mode", "gfm");
        return this.client.postStream("/markdown", params);
    }
    
    public String getRepositoryHtml(final IRepositoryIdProvider repo, final String text) throws IOException {
        return this.readStream(this.getRepositoryStream(repo, text));
    }
    
    public InputStream getStream(final String text, final String mode) throws IOException {
        final Map<String, String> params = new HashMap<String, String>(2, 1.0f);
        params.put("text", text);
        params.put("mode", mode);
        return this.client.postStream("/markdown", params);
    }
    
    public String getHtml(final String text, final String mode) throws IOException {
        return this.readStream(this.getStream(text, mode));
    }
}
