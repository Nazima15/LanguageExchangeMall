package controller;

import dao.WishlistDAO;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/wishlist/add")
public class WishlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WishlistDAO wishlistDAO = new WishlistDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 🔐 기존 세션 가져오기 (로그인 상태 확인용)
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // ❌ 로그인 안 되어 있으면 로그인 페이지로 이동
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 👤 로그인된 사용자 ID
        int userId = user.getUserId();
        // 🤝 추가하려는 파트너 ID (폼에서 전달됨)
        int partnerId = Integer.parseInt(request.getParameter("partnerId"));

        try {
            // ⭐ 이미 위시리스트에 존재하는 경우
            if (wishlistDAO.exists(userId, partnerId)) {
                session.setAttribute("wishlistErrorPartnerId", partnerId);  // ❗ 중복 알림
            }
            // ⭐ 새로 추가 성공한 경우
            else if (wishlistDAO.addWishlist(userId, partnerId)) {
                session.setAttribute("wishlistSuccessId", partnerId);       // ✅ 성공 알림
            }
            // ⭐ 추가 실패한 경우
            else {
                session.setAttribute("wishlistErrorPartnerId", partnerId);  // ❗ 실패 알림
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("wishlistErrorPartnerId", partnerId);      // ❗ 예외 발생 → 실패 처리
        }

        // ↩️ 처리 후 다시 파트너 목록 페이지로 이동
        response.sendRedirect(request.getContextPath() + "/partners");
    }
}
