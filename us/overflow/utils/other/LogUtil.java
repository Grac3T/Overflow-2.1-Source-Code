// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

import us.overflow.Overflow;
import java.util.List;
import java.util.LinkedList;

public class LogUtil
{
    private LinkedList<String> logs;
    
    public LogUtil() {
        this.logs = new LinkedList<String>();
    }
    
    public List<String> getLogs() {
        return this.logs;
    }
    
    public void clear() {
        this.logs.clear();
    }
    
    public void setLogs(final LinkedList<String> logs) {
        this.logs = logs;
    }
    
    public int size() {
        return this.logs.size();
    }
    
    public void addLog(final String playerName, final String check, final int violation, final boolean lag, final boolean exp) {
        this.logs.add(Overflow.getInstance().currentDate + " " + playerName + " failed: " + check + " " + violation + " lagging: " + lag + (exp ? " (EXPERIMENTAL)" : ""));
    }
}
