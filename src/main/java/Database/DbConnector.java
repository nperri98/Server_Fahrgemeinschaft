package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {
    private final String serverURL;

    private final String username;

    private final String password;

    private final String dbName;

    private Connection connection;

    public DbConnector(String serverURL, String username, String password, String dbName){

        this.serverURL = serverURL;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://"+serverURL+"/"+dbName+"?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT"
                ,username,password);
    }

    public void executeUpdate(String command) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(connection.nativeSQL(command));
    }
    public ResultSet executeQuery(String command) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(command);
    }

    public void close() throws SQLException{
        connection.close();
    }
}
