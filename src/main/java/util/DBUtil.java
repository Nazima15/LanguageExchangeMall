package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String URL = "jdbc:mariadb://localhost:3306/BP2401050?characterEncoding=utf8&serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "nazima1502"; // 여기 변경

    public static Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
