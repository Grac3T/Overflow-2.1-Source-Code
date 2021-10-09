// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DataHeleper
{
    private ArrayList<String> objectedUUIDs;
    private List<String> alertUUIDS;
    private HashMap<Long, String> mysqlLogs;
    
    public DataHeleper() {
        this.objectedUUIDs = new ArrayList();
        this.alertUUIDS = new CopyOnWriteArrayList();
        this.mysqlLogs = new HashMap();
    }
    
    public ArrayList<String> getObjectedUUIDs() {
        return (ArrayList<String>)this.objectedUUIDs;
    }
    
    public List<String> getAlertUUIDS() {
        return (List<String>)this.alertUUIDS;
    }
    
    public HashMap<Long, String> getMysqlLogs() {
        return (HashMap<Long, String>)this.mysqlLogs;
    }
    
    public void setObjectedUUIDs(final ArrayList<String> objectedUUIDs) {
        this.objectedUUIDs = objectedUUIDs;
    }
    
    public void setAlertUUIDS(final List<String> alertUUIDS) {
        this.alertUUIDS = alertUUIDS;
    }
    
    public void setMysqlLogs(final HashMap<Long, String> mysqlLogs) {
        this.mysqlLogs = mysqlLogs;
    }
}
