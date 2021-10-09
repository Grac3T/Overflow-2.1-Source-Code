// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import java.io.BufferedOutputStream;
import org.eclipse.egit.github.core.RequestError;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.io.InputStream;
import com.google.gson.JsonParseException;
import org.eclipse.egit.github.core.util.EncodingUtils;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;
import com.google.gson.Gson;

public class GitHubClient
{
    protected static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected static final String HEADER_ACCEPT = "Accept";
    protected static final String HEADER_AUTHORIZATION = "Authorization";
    protected static final String HEADER_USER_AGENT = "User-Agent";
    protected static final String METHOD_GET = "GET";
    protected static final String METHOD_PUT = "PUT";
    protected static final String METHOD_POST = "POST";
    protected static final String METHOD_DELETE = "DELETE";
    protected static final String USER_AGENT = "GitHubJava/2.1.0";
    protected static final int HTTP_UNPROCESSABLE_ENTITY = 422;
    protected final String baseUri;
    protected final String prefix;
    protected Gson gson;
    private String user;
    private String credentials;
    private String userAgent;
    private String headerAccept;
    private int bufferSize;
    private int requestLimit;
    private int remainingRequests;
    
    public static GitHubClient createClient(final String url) {
        try {
            String host = new URL(url).getHost();
            if ("github.com".equals(host) || "gist.github.com".equals(host)) {
                host = "api.github.com";
            }
            return new GitHubClient(host);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public GitHubClient() {
        this("api.github.com");
    }
    
    public GitHubClient(final String hostname) {
        this(hostname, -1, "https");
    }
    
    public GitHubClient(final String hostname, final int port, final String scheme) {
        this.gson = GsonUtils.getGson();
        this.userAgent = "GitHubJava/2.1.0";
        this.headerAccept = "application/vnd.github.v3.full+json";
        this.bufferSize = 8192;
        this.requestLimit = -1;
        this.remainingRequests = -1;
        final StringBuilder uri = new StringBuilder(scheme);
        uri.append("://");
        uri.append(hostname);
        if (port > 0) {
            uri.append(':').append(port);
        }
        this.baseUri = uri.toString();
        if ("api.github.com".equals(hostname)) {
            this.prefix = null;
        }
        else {
            this.prefix = "/api/v3";
        }
    }
    
    public GitHubClient setSerializeNulls(final boolean serializeNulls) {
        this.gson = GsonUtils.getGson(serializeNulls);
        return this;
    }
    
    public GitHubClient setUserAgent(final String agent) {
        if (agent != null && agent.length() > 0) {
            this.userAgent = agent;
        }
        else {
            this.userAgent = "GitHubJava/2.1.0";
        }
        return this;
    }
    
    public GitHubClient setHeaderAccept(final String header) {
        if (header != null && header.length() > 0) {
            this.headerAccept = header;
        }
        else {
            this.headerAccept = "application/vnd.github.v3.full+json";
        }
        return this;
    }
    
    public String getHeaderAccept() {
        if (this.headerAccept != null && this.headerAccept.length() > 0) {
            return this.headerAccept;
        }
        return "application/vnd.github.v3.full+json";
    }
    
    protected HttpURLConnection configureRequest(final HttpURLConnection request) {
        if (this.credentials != null) {
            request.setRequestProperty("Authorization", this.credentials);
        }
        request.setRequestProperty("User-Agent", this.userAgent);
        request.setRequestProperty("Accept", this.getHeaderAccept());
        return request;
    }
    
    protected String configureUri(final String uri) {
        if (this.prefix == null || uri.startsWith(this.prefix)) {
            return uri;
        }
        return this.prefix + uri;
    }
    
    protected HttpURLConnection createConnection(final String uri) throws IOException {
        final URL url = new URL(this.createUri(uri));
        return (HttpURLConnection)url.openConnection();
    }
    
    protected HttpURLConnection createConnection(final String uri, final String method) throws IOException {
        final HttpURLConnection connection = this.createConnection(uri);
        connection.setRequestMethod(method);
        return this.configureRequest(connection);
    }
    
    protected HttpURLConnection createGet(final String uri) throws IOException {
        return this.createConnection(uri, "GET");
    }
    
    protected HttpURLConnection createPost(final String uri) throws IOException {
        return this.createConnection(uri, "POST");
    }
    
    protected HttpURLConnection createPut(final String uri) throws IOException {
        return this.createConnection(uri, "PUT");
    }
    
    protected HttpURLConnection createDelete(final String uri) throws IOException {
        return this.createConnection(uri, "DELETE");
    }
    
    public GitHubClient setCredentials(final String user, final String password) {
        this.user = user;
        if (user != null && user.length() > 0 && password != null && password.length() > 0) {
            this.credentials = "Basic " + EncodingUtils.toBase64(user + ':' + password);
        }
        else {
            this.credentials = null;
        }
        return this;
    }
    
    public GitHubClient setOAuth2Token(final String token) {
        if (token != null && token.length() > 0) {
            this.credentials = "token " + token;
        }
        else {
            this.credentials = null;
        }
        return this;
    }
    
    public GitHubClient setBufferSize(final int bufferSize) {
        if (bufferSize < 1) {
            throw new IllegalArgumentException("Buffer size must be greater than zero");
        }
        this.bufferSize = bufferSize;
        return this;
    }
    
    public String getUser() {
        return this.user;
    }
    
    protected String toJson(final Object object) throws IOException {
        try {
            return this.gson.toJson(object);
        }
        catch (JsonParseException jpe) {
            final IOException ioe = new IOException("Parse exception converting object to JSON");
            ioe.initCause((Throwable)jpe);
            throw ioe;
        }
    }
    
    protected <V> V parseJson(final InputStream stream, final Type type) throws IOException {
        return this.parseJson(stream, type, null);
    }
    
    protected <V> V parseJson(final InputStream stream, final Type type, final Type listType) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"), this.bufferSize);
        if (listType == null) {
            try {
                return (V)this.gson.fromJson((Reader)reader, type);
            }
            catch (JsonParseException jpe) {
                final IOException ioe = new IOException("Parse exception converting JSON to object");
                ioe.initCause((Throwable)jpe);
                throw ioe;
            }
            finally {
                try {
                    reader.close();
                }
                catch (IOException ex) {}
            }
        }
        final JsonReader jsonReader = new JsonReader((Reader)reader);
        try {
            if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                return (V)this.gson.fromJson(jsonReader, listType);
            }
            return (V)this.gson.fromJson(jsonReader, type);
        }
        catch (JsonParseException jpe2) {
            final IOException ioe2 = new IOException("Parse exception converting JSON to object");
            ioe2.initCause((Throwable)jpe2);
            throw ioe2;
        }
        finally {
            try {
                jsonReader.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    protected boolean isError(final int code) {
        switch (code) {
            case 400:
            case 401:
            case 403:
            case 404:
            case 409:
            case 410:
            case 422:
            case 500: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isOk(final int code) {
        switch (code) {
            case 200:
            case 201:
            case 202: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isEmpty(final int code) {
        return 204 == code;
    }
    
    protected RequestError parseError(final InputStream response) throws IOException {
        return this.parseJson(response, RequestError.class);
    }
    
    protected Object getBody(final GitHubRequest request, final InputStream stream) throws IOException {
        final Type type = request.getType();
        if (type != null) {
            return this.parseJson(stream, type, request.getArrayType());
        }
        return null;
    }
    
    protected IOException createException(final InputStream response, final int code, final String status) {
        if (this.isError(code)) {
            RequestError error;
            try {
                error = this.parseError(response);
            }
            catch (IOException e) {
                return e;
            }
            if (error != null) {
                return (IOException)new RequestException(error, code);
            }
        }
        else {
            try {
                response.close();
            }
            catch (IOException ex) {}
        }
        String message;
        if (status != null && status.length() > 0) {
            message = status + " (" + code + ')';
        }
        else {
            message = "Unknown error occurred (" + code + ')';
        }
        return new IOException(message);
    }
    
    public void post(final String uri) throws IOException {
        this.post(uri, null, null);
    }
    
    public void put(final String uri) throws IOException {
        this.put(uri, null, null);
    }
    
    public void delete(final String uri) throws IOException {
        this.delete(uri, null);
    }
    
    protected void sendParams(final HttpURLConnection request, final Object params) throws IOException {
        request.setDoOutput(true);
        if (params != null) {
            request.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            final byte[] data = this.toJson(params).getBytes("UTF-8");
            request.setFixedLengthStreamingMode(data.length);
            final BufferedOutputStream output = new BufferedOutputStream(request.getOutputStream(), this.bufferSize);
            try {
                output.write(data);
                output.flush();
            }
            finally {
                try {
                    output.close();
                }
                catch (IOException ex) {}
            }
        }
        else {
            request.setFixedLengthStreamingMode(0);
            request.setRequestProperty("Content-Length", "0");
        }
    }
    
    private <V> V sendJson(final HttpURLConnection request, final Object params, final Type type) throws IOException {
        this.sendParams(request, params);
        final int code = request.getResponseCode();
        this.updateRateLimits(request);
        if (this.isOk(code)) {
            if (type != null) {
                return this.parseJson(this.getStream(request), type);
            }
            return null;
        }
        else {
            if (this.isEmpty(code)) {
                return null;
            }
            throw this.createException(this.getStream(request), code, request.getResponseMessage());
        }
    }
    
    protected String createUri(final String path) {
        return this.baseUri + this.configureUri(path);
    }
    
    public InputStream getStream(final GitHubRequest request) throws IOException {
        return this.getResponseStream(this.createGet(request.generateUri()));
    }
    
    public InputStream postStream(final String uri, final Object params) throws IOException {
        final HttpURLConnection connection = this.createPost(uri);
        this.sendParams(connection, params);
        return this.getResponseStream(connection);
    }
    
    protected InputStream getResponseStream(final HttpURLConnection request) throws IOException {
        final InputStream stream = this.getStream(request);
        final int code = request.getResponseCode();
        this.updateRateLimits(request);
        if (this.isOk(code)) {
            return stream;
        }
        throw this.createException(stream, code, request.getResponseMessage());
    }
    
    protected InputStream getStream(final HttpURLConnection request) throws IOException {
        if (request.getResponseCode() < 400) {
            return request.getInputStream();
        }
        final InputStream stream = request.getErrorStream();
        return (stream != null) ? stream : request.getInputStream();
    }
    
    public GitHubResponse get(final GitHubRequest request) throws IOException {
        final HttpURLConnection httpRequest = this.createGet(request.generateUri());
        final String accept = request.getResponseContentType();
        if (accept != null) {
            httpRequest.setRequestProperty("Accept", accept);
        }
        final int code = httpRequest.getResponseCode();
        this.updateRateLimits(httpRequest);
        if (this.isOk(code)) {
            return new GitHubResponse(httpRequest, this.getBody(request, this.getStream(httpRequest)));
        }
        if (this.isEmpty(code)) {
            return new GitHubResponse(httpRequest, (Object)null);
        }
        throw this.createException(this.getStream(httpRequest), code, httpRequest.getResponseMessage());
    }
    
    public <V> V post(final String uri, final Object params, final Type type) throws IOException {
        final HttpURLConnection request = this.createPost(uri);
        return this.sendJson(request, params, type);
    }
    
    public <V> V put(final String uri, final Object params, final Type type) throws IOException {
        final HttpURLConnection request = this.createPut(uri);
        return this.sendJson(request, params, type);
    }
    
    public void delete(final String uri, final Object params) throws IOException {
        final HttpURLConnection request = this.createDelete(uri);
        if (params != null) {
            this.sendParams(request, params);
        }
        final int code = request.getResponseCode();
        this.updateRateLimits(request);
        if (!this.isEmpty(code)) {
            throw new RequestException(this.parseError(this.getStream(request)), code);
        }
    }
    
    protected GitHubClient updateRateLimits(final HttpURLConnection request) {
        final String limit = request.getHeaderField("X-RateLimit-Limit");
        if (limit != null && limit.length() > 0) {
            try {
                this.requestLimit = Integer.parseInt(limit);
            }
            catch (NumberFormatException nfe) {
                this.requestLimit = -1;
            }
        }
        else {
            this.requestLimit = -1;
        }
        final String remaining = request.getHeaderField("X-RateLimit-Remaining");
        if (remaining != null && remaining.length() > 0) {
            try {
                this.remainingRequests = Integer.parseInt(remaining);
            }
            catch (NumberFormatException nfe2) {
                this.remainingRequests = -1;
            }
        }
        else {
            this.remainingRequests = -1;
        }
        return this;
    }
    
    public int getRemainingRequests() {
        return this.remainingRequests;
    }
    
    public int getRequestLimit() {
        return this.requestLimit;
    }
}
