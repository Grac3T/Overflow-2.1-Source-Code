// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.database.sockets;

import java.sql.DriverManager;
import us.overflow.Overflow;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.sql.Connection;

public class MySQLSocket
{
    private Connection connection;
    private String IP;
    private String PASSWORD;
    private String PORT;
    private String DB;
    private String USERNAME;
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public boolean isConnected() {
        return this.connection != null;
    }
    
    public List<String> getAllData(final String UUID) throws SQLException {
        final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM OverflowLogs WHERE uuid=?");
        statement.setString(1, UUID);
        final ResultSet resultSet = statement.executeQuery();
        final List<String> temp = new CopyOnWriteArrayList<String>();
        while (resultSet.next()) {
            temp.add(resultSet.getString("uuid") + ":" + resultSet.getString("username") + ":" + resultSet.getString("checkName") + ":" + resultSet.getString("VL") + ":" + resultSet.getString("time"));
        }
        return temp;
    }
    
    public List<String> getObject(final String UUID, final String field) throws SQLException {
        final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM OverflowLogs WHERE uuid=?");
        statement.setString(1, UUID);
        final ResultSet resultSet = statement.executeQuery();
        final List<String> temp = new CopyOnWriteArrayList<String>();
        while (resultSet.next()) {
            temp.add(resultSet.getString(field));
        }
        return temp;
    }
    
    public void log(final String uuid, final String username, final String checkName, final String VL, final String time) {
        try {
            final PreparedStatement statement = this.connection.prepareStatement("INSERT INTO OverflowLogs (uuid, username,  checkName, VL, time) values (?, ?, ?, ?, ?)");
            statement.setString(1, uuid);
            statement.setString(2, username);
            statement.setString(3, checkName);
            statement.setString(4, VL);
            statement.setString(5, time);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getCheckName(final String UUID) throws SQLException {
        final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM OverflowLogs WHERE uuid=?");
        statement.setString(1, UUID);
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("checkName");
        }
        return null;
    }
    
    public void run(final String text) {
        Overflow.getInstance().getDatabaseExecutor().execute(this::lambda$run$0);
    }
    
    public void createTablesIfNotExist() {
        this.run("CREATE TABLE IF NOT EXISTS OverflowLogs (  id int(255) NOT NULL AUTO_INCREMENT,  uuid varchar(255) NOT NULL,  username varchar(255) NOT NULL,  checkName varchar(255) NOT NULL,  VL varchar(255) NOT NULL,  time varchar(255) NOT NULL,  PRIMARY KEY (id))");
    }
    
    public void connect() {
        if (!this.isConnected()) {
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.IP + ":" + this.PORT + "/" + this.DB, this.USERNAME, this.PASSWORD);
                Overflow.getInstance().getLogger().info("Connected to MYSQL server with IP " + this.IP + ":" + this.PORT);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.connection.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public String getIP() {
        return this.IP;
    }
    
    public String getPASSWORD() {
        return this.PASSWORD;
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
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
    
    public void setIP(final String IP) {
        this.IP = IP;
    }
    
    public void setPASSWORD(final String PASSWORD) {
        this.PASSWORD = PASSWORD;
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
}
