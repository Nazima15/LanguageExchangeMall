package dao;

import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dto.User;

public class WishlistDAO {

    // ⭐ 이미 관심 목록에 있는지 확인하는 메서드
    public boolean exists(int userId, int partnerId) {
        String sql = "SELECT COUNT(*) FROM wishlist WHERE user_id = ? AND partner_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, partnerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ⭐ 관심 목록 저장 (중복 방지 적용됨)
    public void addWishlist(int userId, int partnerId) {
        if (exists(userId, partnerId)) return;

        String sql = "INSERT INTO wishlist (user_id, partner_id) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, partnerId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ⭐ 관심 목록 삭제
    public boolean removeWishlist(int userId, int partnerId) {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND partner_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, partnerId);
            int rows = stmt.executeUpdate();
            return rows > 0; // 삭제 성공 시 true 반환

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ⭐ 관심 목록 전체 파트너 정보 가져오기 (wishlist.jsp 등에서 사용)
    public List<User> getWishlist(int userId) {
        List<User> list = new ArrayList<>();

        String sql =
                "SELECT u.user_id, u.nickname, u.native_lang_id, u.learn_lang_id " +
                "FROM wishlist w " +
                "JOIN users u ON w.partner_id = u.user_id " +
                "WHERE w.user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setNickname(rs.getString("nickname"));
                u.setNativeLang(rs.getString("native_lang_id"));
                u.setLearnLang(rs.getString("learn_lang_id"));
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ⭐ JSP에서 “이미 담긴 partnerId 목록” 확인할 때 사용
    public List<Integer> getWishlistIds(int userId) {
        List<Integer> ids = new ArrayList<>();

        String sql = "SELECT partner_id FROM wishlist WHERE user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt("partner_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }
}

