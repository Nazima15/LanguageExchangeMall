package dao;

import dto.User;
import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ===============================
    // 로그인 / 회원가입용
    // ===============================
    public User findByUsername(String username) throws Exception {
        String sql = "SELECT u.user_id, u.username, u.password_hash, u.nickname, u.email, u.level, u.profile_img, u.role, " +
                     "ln.name AS native_lang, ll.name AS learn_lang, " +
                     "u.native_lang_id, u.learn_lang_id " +
                     "FROM users u " +
                     "LEFT JOIN languages ln ON u.native_lang_id = ln.id " +
                     "LEFT JOIN languages ll ON u.learn_lang_id = ll.id " +
                     "WHERE u.username=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                user.setLevel(rs.getString("level"));
                user.setProfileImg(rs.getString("profile_img") != null ? rs.getString("profile_img") : "/images/default_profile.png");
                user.setRole(rs.getString("role") != null ? rs.getString("role") : "USER");
                user.setNativeLang(rs.getString("native_lang") != null ? rs.getString("native_lang") : "없음");
                user.setLearnLang(rs.getString("learn_lang") != null ? rs.getString("learn_lang") : "없음");
                user.setNativeLangId((Integer) rs.getObject("native_lang_id"));
                user.setLearnLangId((Integer) rs.getObject("learn_lang_id"));
                return user;
            }
        }
        return null;
    }

    public void insertUser(User user) throws Exception {
        String sql = "INSERT INTO users (username, password_hash, nickname, profile_img, native_lang_id, learn_lang_id, role) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getProfileImg() != null ? user.getProfileImg() : "/images/default_profile.png");
            ps.setObject(5, user.getNativeLangId());
            ps.setObject(6, user.getLearnLangId());
            ps.setString(7, user.getRole() != null ? user.getRole() : "USER");
            ps.executeUpdate();
        }
    }

    // ===============================
    // 파트너 목록 조회용
    // ===============================
    public List<User> getAllPartners() throws Exception {
        List<User> list = new ArrayList<>();
        String sql = "SELECT u.user_id, u.username, u.nickname, u.profile_img, " +
                     "ln.name AS native_lang, ll.name AS learn_lang " +
                     "FROM users u " +
                     "LEFT JOIN languages ln ON u.native_lang_id = ln.id " +
                     "LEFT JOIN languages ll ON u.learn_lang_id = ll.id";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setNickname(rs.getString("nickname"));
                u.setProfileImg(rs.getString("profile_img") != null ? rs.getString("profile_img") : "/images/default_profile.png");
                u.setNativeLang(rs.getString("native_lang")); // null이면 그대로 null
                u.setLearnLang(rs.getString("learn_lang"));   // null이면 그대로 null
                list.add(u);
            }
        }
        return list;
    }

}

