package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {
    private static final String username = "root";
    private static final String password = "root";
    private static final String hostName = "localhost:3306";
    private static final String databasename = "javaproject7";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + hostName + "/" + databasename, username, password);


        return connection;
    }
}
