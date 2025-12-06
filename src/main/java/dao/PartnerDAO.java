package dao;

import dto.Partner;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerDAO {

    // ================================================================
    // 🔍 1. 검색 + 페이징 처리된 파트너 목록 가져오기
    //    - keyword: 검색어
    //    - offset: 몇 번째부터 가져올지 (페이징 시작 번호)
    //    - limit: 한 페이지에 몇 개 보여줄지
    // ================================================================
    public List<Partner> getPartners(String keyword, int offset, int limit) {
        List<Partner> list = new ArrayList<>();

        String sql =
                "SELECT * FROM partners " +
                "WHERE name LIKE ? OR native_lang LIKE ? OR learn_lang LIKE ? " +
                "ORDER BY id DESC LIMIT ?, ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 검색어 양쪽에 '%' 붙여서 부분 검색
            String search = "%" + keyword + "%";

            stmt.setString(1, search);
            stmt.setString(2, search);
            stmt.setString(3, search);
            stmt.setInt(4, offset); // 가져올 시작 번호
            stmt.setInt(5, limit);  // 몇 개 가져올지

            // SQL 실행 후 결과 반복해서 Partner 객체에 담기
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Partner p = new Partner();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setNativeLang(rs.getString("native_lang"));
                    p.setLearnLang(rs.getString("learn_lang"));
                    p.setIntro(rs.getString("intro"));
                    p.setImageUrl(rs.getString("image_url"));
                    p.setUserId(rs.getInt("user_id"));
                    list.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================================================================
    // 🆕 2. 메인 화면에 보여줄 “최근 등록된 파트너 3명” 가져오기
    // ================================================================
    public List<Partner> getRecentPartners() {
        List<Partner> list = new ArrayList<>();

        String sql = "SELECT * FROM partners ORDER BY id DESC LIMIT 3";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Partner p = new Partner();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setNativeLang(rs.getString("native_lang"));
                p.setLearnLang(rs.getString("learn_lang"));
                p.setIntro(rs.getString("intro"));
                p.setImageUrl(rs.getString("image_url"));
                p.setUserId(rs.getInt("user_id"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================================================================
    // 📊 3. 검색 결과 총 개수 가져오기
    //    → 페이지 수 계산할 때 사용 (totalPage)
    // ================================================================
    public int getPartnersCount(String keyword) {
        String sql =
                "SELECT COUNT(*) FROM partners " +
                "WHERE name LIKE ? OR native_lang LIKE ? OR learn_lang LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String search = "%" + keyword + "%";

            stmt.setString(1, search);
            stmt.setString(2, search);
            stmt.setString(3, search);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1); // COUNT(*) 값
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}

