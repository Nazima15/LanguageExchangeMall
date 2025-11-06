package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(
                "jdbc:h2:~/2401050_nazima;MODE=MySQL;DB_CLOSE_ON_EXIT=FALSE", 
                "sa",
                ""
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}
