// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

public class PageLinks
{
    private static final String DELIM_LINKS = ",";
    private static final String DELIM_LINK_PARAM = ";";
    private String first;
    private String last;
    private String next;
    private String prev;
    
    public PageLinks(final GitHubResponse response) {
        final String linkHeader = response.getHeader("Link");
        if (linkHeader != null) {
            final String[] split;
            final String[] links = split = linkHeader.split(",");
            for (final String link : split) {
                final String[] segments = link.split(";");
                if (segments.length >= 2) {
                    String linkPart = segments[0].trim();
                    if (linkPart.startsWith("<")) {
                        if (linkPart.endsWith(">")) {
                            linkPart = linkPart.substring(1, linkPart.length() - 1);
                            for (int i = 1; i < segments.length; ++i) {
                                final String[] rel = segments[i].trim().split("=");
                                if (rel.length >= 2) {
                                    if ("rel".equals(rel[0])) {
                                        String relValue = rel[1];
                                        if (relValue.startsWith("\"") && relValue.endsWith("\"")) {
                                            relValue = relValue.substring(1, relValue.length() - 1);
                                        }
                                        if ("first".equals(relValue)) {
                                            this.first = linkPart;
                                        }
                                        else if ("last".equals(relValue)) {
                                            this.last = linkPart;
                                        }
                                        else if ("next".equals(relValue)) {
                                            this.next = linkPart;
                                        }
                                        else if ("prev".equals(relValue)) {
                                            this.prev = linkPart;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            this.next = response.getHeader("X-Next");
            this.last = response.getHeader("X-Last");
        }
    }
    
    public String getFirst() {
        return this.first;
    }
    
    public String getLast() {
        return this.last;
    }
    
    public String getNext() {
        return this.next;
    }
    
    public String getPrev() {
        return this.prev;
    }
}
