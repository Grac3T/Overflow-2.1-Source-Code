// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.database;

import us.overflow.database.utils.SocketHelper;
import us.overflow.database.utils.MySQLDBData;

public class DatabaseHelper
{
    private MySQLDBData mySQLDBData;
    private SocketHelper socketHelper;
    private Database database;
    
    public DatabaseHelper() {
        this.mySQLDBData = new MySQLDBData();
        this.socketHelper = new SocketHelper();
    }
    
    public void setup() {
    }
    
    public MySQLDBData getMySQLDBData() {
        return this.mySQLDBData;
    }
    
    public SocketHelper getSocketHelper() {
        return this.socketHelper;
    }
    
    public Database getDatabase() {
        return this.database;
    }
    
    public void setMySQLDBData(final MySQLDBData mySQLDBData) {
        this.mySQLDBData = mySQLDBData;
    }
    
    public void setSocketHelper(final SocketHelper socketHelper) {
        this.socketHelper = socketHelper;
    }
    
    public void setDatabase(final Database database) {
        this.database = database;
    }
    
    public enum Database
    {
        public static final Database MYSQL;
        
        public static Database valueOf(final String name) {
            return Enum.valueOf(Database.class, name);
        }
        
        static {
            Database.MYSQL = new Database("MYSQL", 0);
            Database.$VALUES = new Database[] { Database.MYSQL };
        }
    }
}
