// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Label implements Serializable
{
    private static final long serialVersionUID = 859851442075061861L;
    private String color;
    private String name;
    private String url;
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Label)) {
            return false;
        }
        final String name = this.name;
        return name != null && name.equals(((Label)obj).name);
    }
    
    @Override
    public int hashCode() {
        final String name = this.name;
        return (name != null) ? name.hashCode() : super.hashCode();
    }
    
    @Override
    public String toString() {
        final String name = this.name;
        return (name != null) ? name : super.toString();
    }
    
    public String getColor() {
        return this.color;
    }
    
    public Label setColor(final String color) {
        this.color = color;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Label setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Label setUrl(final String url) {
        this.url = url;
        return this;
    }
}
