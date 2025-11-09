package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    private Connection conn;

    public LanguageDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getLanguages() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT lang_name FROM Language";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("lang_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
