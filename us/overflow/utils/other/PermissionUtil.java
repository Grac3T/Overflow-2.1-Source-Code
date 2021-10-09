// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

public class PermissionUtil
{
    private String alertsPermission;
    private String commandPermission;
    private String bypassPermission;
    
    public String getAlertsPermission() {
        return this.alertsPermission;
    }
    
    public String getCommandPermission() {
        return this.commandPermission;
    }
    
    public String getBypassPermission() {
        return this.bypassPermission;
    }
    
    public void setAlertsPermission(final String alertsPermission) {
        this.alertsPermission = alertsPermission;
    }
    
    public void setCommandPermission(final String commandPermission) {
        this.commandPermission = commandPermission;
    }
    
    public void setBypassPermission(final String bypassPermission) {
        this.bypassPermission = bypassPermission;
    }
}
