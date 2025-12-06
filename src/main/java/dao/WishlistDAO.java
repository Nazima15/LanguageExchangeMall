package dao;

import dto.Wishlist;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {

    /**
     * 관심(위시리스트) 추가
     */
    public boolean addWishlist(int userId, int partnerId) {
        String sql = "INSERT INTO wishlist (user_id, partner_id) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, partnerId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {   
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 동일 userId + partnerId가 이미 존재하는지 체크
     */
    public boolean exists(int userId, int partnerId) {
        String sql = "SELECT 1 FROM wishlist WHERE user_id = ? AND partner_id = ? LIMIT 1";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, partnerId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {   // SQLException → Exception 변경
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 관심 목록 조회 + partner JOIN
     */
    public List<Wishlist> getWishlistByUser(int userId) {
        List<Wishlist> list = new ArrayList<>();

        String sql =
            "SELECT " +
            "   w.id AS wishlistId, " +
            "   w.user_id, " +
            "   w.partner_id, " +
            "   w.created_at, " +
            "   p.image_url, " +
            "   p.name AS name, " +
            "   p.native_lang, " +
            "   p.learn_lang, " +
            "   p.intro " +
            "FROM wishlist w " +
            "JOIN partners p ON w.partner_id = p.id " +   // partners 테이블
            "WHERE w.user_id = ? " +
            "ORDER BY w.created_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Wishlist w = new Wishlist();

                    w.setWishlistId(rs.getInt("wishlistId"));
                    w.setUserId(rs.getInt("user_id"));
                    w.setPartnerId(rs.getInt("partner_id"));
                    w.setCreatedAt(rs.getString("created_at"));

                    w.setImageUrl(rs.getString("image_url"));
                    w.setName(rs.getString("name"));
                    w.setNativeLang(rs.getString("native_lang"));
                    w.setLearnLang(rs.getString("learn_lang"));
                    w.setIntro(rs.getString("intro"));

                    list.add(w);
                }
            }

        } catch (Exception e) {   // SQLException → Exception 변경
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 관심 항목 삭제
     */
    public boolean deleteWishlist(int wishlistId) {
        String sql = "DELETE FROM wishlist WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, wishlistId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {   // SQLException → Exception 변경
            e.printStackTrace();
        }
        return false;
    }
}

