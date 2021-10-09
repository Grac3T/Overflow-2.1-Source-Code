// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;

public class DownloadResource extends Download
{
    private static final long serialVersionUID = 4522843864589481490L;
    private boolean redirect;
    private Date expirationdate;
    private String accesskeyid;
    private String acl;
    private String mimeType;
    private String path;
    private String policy;
    private String prefix;
    private String s3Url;
    private String signature;
    
    public boolean isRedirect() {
        return this.redirect;
    }
    
    public DownloadResource setRedirect(final boolean redirect) {
        this.redirect = redirect;
        return this;
    }
    
    public Date getExpirationdate() {
        return DateUtils.clone(this.expirationdate);
    }
    
    public DownloadResource setExpirationdate(final Date expirationdate) {
        this.expirationdate = DateUtils.clone(expirationdate);
        return this;
    }
    
    public String getAccesskeyid() {
        return this.accesskeyid;
    }
    
    public DownloadResource setAccesskeyid(final String accesskeyid) {
        this.accesskeyid = accesskeyid;
        return this;
    }
    
    public String getAcl() {
        return this.acl;
    }
    
    public DownloadResource setAcl(final String acl) {
        this.acl = acl;
        return this;
    }
    
    public String getMimeType() {
        return this.mimeType;
    }
    
    public DownloadResource setMimeType(final String mimeType) {
        this.mimeType = mimeType;
        return this;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public DownloadResource setPath(final String path) {
        this.path = path;
        return this;
    }
    
    public String getPolicy() {
        return this.policy;
    }
    
    public DownloadResource setPolicy(final String policy) {
        this.policy = policy;
        return this;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public DownloadResource setPrefix(final String prefix) {
        this.prefix = prefix;
        return this;
    }
    
    public String getS3Url() {
        return this.s3Url;
    }
    
    public DownloadResource setS3Url(final String s3Url) {
        this.s3Url = s3Url;
        return this;
    }
    
    public String getSignature() {
        return this.signature;
    }
    
    public DownloadResource setSignature(final String signature) {
        this.signature = signature;
        return this;
    }
}
