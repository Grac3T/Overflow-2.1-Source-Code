// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.user.global;

import us.overflow.base.Check;
import java.util.LinkedHashMap;
import us.overflow.utils.other.LogUtil;

public class GlobalObject
{
    private boolean isRiding;
    private boolean hasAlerts;
    private String uuid;
    private LogUtil logUtil;
    private LinkedHashMap<Check, Integer> flaggedChecks;
    
    public GlobalObject(final String uuid) {
        this.hasAlerts = true;
        this.logUtil = new LogUtil();
        this.flaggedChecks = new LinkedHashMap();
        this.uuid = uuid;
    }
    
    public boolean isRiding() {
        return this.isRiding;
    }
    
    public boolean isHasAlerts() {
        return this.hasAlerts;
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public LogUtil getLogUtil() {
        return this.logUtil;
    }
    
    public LinkedHashMap<Check, Integer> getFlaggedChecks() {
        return (LinkedHashMap<Check, Integer>)this.flaggedChecks;
    }
    
    public void setRiding(final boolean isRiding) {
        this.isRiding = isRiding;
    }
    
    public void setHasAlerts(final boolean hasAlerts) {
        this.hasAlerts = hasAlerts;
    }
    
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
    
    public void setLogUtil(final LogUtil logUtil) {
        this.logUtil = logUtil;
    }
    
    public void setFlaggedChecks(final LinkedHashMap<Check, Integer> flaggedChecks) {
        this.flaggedChecks = flaggedChecks;
    }
}
