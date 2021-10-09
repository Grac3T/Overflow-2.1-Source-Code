// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Download;
import java.io.Serializable;

public class DownloadPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 4246935370658381214L;
    private Download download;
    
    public Download getDownload() {
        return this.download;
    }
    
    public DownloadPayload setDownload(final Download download) {
        this.download = download;
        return this;
    }
}
