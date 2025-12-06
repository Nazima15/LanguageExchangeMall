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
        String sql = "SELECT u.user_id, u.username, u.password_hash, u.nickname, u.email, u.level, u.profile_img " +
                     "FROM users u WHERE u.username=?";
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
                user.setProfileImg(
                    rs.getString("profile_img") != null ? rs.getString("profile_img") : "/images/default_profile.png"
                );
                return user;
            }
        }
        return null;
    }

    // ===============================
    // 회원가입
    // ===============================
    public void insertUser(User user) throws Exception {
        String sql = "INSERT INTO users (username, password_hash, nickname, email, level, profile_img, created_at) " +
                     "VALUES (?,?,?,?,?,?,NOW())";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getLevel() != null ? user.getLevel() : "초급");
            ps.setString(6, user.getProfileImg() != null ? user.getProfileImg() : "/images/default_profile.png");
            ps.executeUpdate();
        }
    }

    // ===============================
    // 전체 파트너 목록
    // ===============================
    public List<User> getAllPartners() throws Exception {
        List<User> list = new ArrayList<>();
        String sql = "SELECT user_id, username, nickname, profile_img, level FROM users";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setNickname(rs.getString("nickname"));
                u.setLevel(rs.getString("level"));
                u.setProfileImg(
                    rs.getString("profile_img") != null ? rs.getString("profile_img") : "/images/default_profile.png"
                );
                list.add(u);
            }
        }
        return list;
    }

    // ===============================
    // ✅ 최신 등록 사용자 3명
    // ===============================
    public List<User> getRecentUsers() throws Exception {
        List<User> list = new ArrayList<>();
        String sql = "SELECT user_id, username, nickname, profile_img, level " +
                     "FROM users ORDER BY created_at DESC LIMIT 3";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setNickname(rs.getString("nickname"));
                u.setLevel(rs.getString("level"));
                u.setProfileImg(
                    rs.getString("profile_img") != null ? rs.getString("profile_img") : "/images/default_profile.png"
                );
                list.add(u);
            }
        }
        return list;
    }
}

