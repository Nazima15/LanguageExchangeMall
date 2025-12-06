package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String DB_TYPE = System.getProperty("dbType", "MariaDB"); // default MariaDB

    public static Connection getConnection() throws Exception {
        String url, user, password;

        if (DB_TYPE.equals("H2")) {
            Class.forName("org.h2.Driver");
            url = "jdbc:h2:tcp://localhost:9092/~/BP2401050;MODE=MySQL;DB_CLOSE_DELAY=-1";
            user = "sa";
            password = "";
        }
 else {
            Class.forName("org.mariadb.jdbc.Driver");
            url = "jdbc:mariadb://localhost:3306/BP2401050?characterEncoding=utf8&serverTimezone=Asia/Seoul";
            user = "root";
            password = "nazima1502";
        }

        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(true);
        return conn;
    }
}
