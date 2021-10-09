// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.database.utils;

public class MySQLDBData
{
    private String IP;
    private String PORT;
    private String DB;
    private String USERNAME;
    private String PASSWORD;
    
    public String getIP() {
        return this.IP;
    }
    
    public String getPORT() {
        return this.PORT;
    }
    
    public String getDB() {
        return this.DB;
    }
    
    public String getUSERNAME() {
        return this.USERNAME;
    }
    
    public String getPASSWORD() {
        return this.PASSWORD;
    }
    
    public void setIP(final String IP) {
        this.IP = IP;
    }
    
    public void setPORT(final String PORT) {
        this.PORT = PORT;
    }
    
    public void setDB(final String DB) {
        this.DB = DB;
    }
    
    public void setUSERNAME(final String USERNAME) {
        this.USERNAME = USERNAME;
    }
    
    public void setPASSWORD(final String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
