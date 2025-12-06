package dao;

import dto.Notice;
import util.DBUtil;
import java.sql.*;
import java.util.*;

public class NoticeDAO {

    // 📌 [1] 전체 공지사항 가져오기
    public List<Notice> getAllNotices() {
        List<Notice> list = new ArrayList<>();

        // 🔽 최신 순으로 정렬해서 가져오는 SQL
        String sql = "SELECT * FROM notice ORDER BY id DESC";

        // 🔌 DB 연결 후 바로 자동 close 가능(try-with-resources)
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // 🔁 결과 한 줄씩 Notice 객체로 변환 후 리스트에 저장
            while (rs.next()) {
                Notice n = new Notice();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setContent(rs.getString("content"));
                n.setCreatedAt(rs.getString("created_at"));
                list.add(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 📌 [2] 공지사항 ID로 상세 보기
    public Notice getNoticeById(int id) {
        Notice n = null;

        // 🔽 특정 id의 공지 1개 가져오기
        String sql = "SELECT * FROM notice WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);  // 🔑 조회할 notice ID 전달
            ResultSet rs = ps.executeQuery();

            // 🎯 데이터가 존재하면 Notice 객체에 값 저장
            if (rs.next()) {
                n = new Notice();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setContent(rs.getString("content"));
                n.setCreatedAt(rs.getString("created_at"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return n;  // 없으면 null 반환
    }
}

