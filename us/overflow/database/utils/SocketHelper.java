// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.database.utils;

import us.overflow.database.sockets.MySQLSocket;

public class SocketHelper
{
    private MySQLSocket mySQLSocket;
    
    public SocketHelper() {
        this.mySQLSocket = new MySQLSocket();
    }
    
    public MySQLSocket getMySQLSocket() {
        return this.mySQLSocket;
    }
}
