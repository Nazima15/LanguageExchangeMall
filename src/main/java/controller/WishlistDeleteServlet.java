package controller;

import dao.WishlistDAO;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/wishlist/delete")
public class WishlistDeleteServlet extends HttpServlet {

    private WishlistDAO wishlistDAO = new WishlistDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔐 기존 세션 가져오기 (로그인 여부 확인용) — 새로운 세션은 만들지 않음
        HttpSession session = request.getSession(false);

        // 🧑‍💼 세션에서 로그인된 사용자 정보 가져오기
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // ❌ 로그인 안 되어 있으면 로그인 페이지로 이동
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int id;
        try {
            // 🏷️ 삭제할 위시리스트 ID 파라미터 받기
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            // ❗ 잘못된 ID면 리스트 페이지로 다시 이동
            response.sendRedirect(request.getContextPath() + "/wishlist/list");
            return;
        }

        // 🗑️ DB에서 해당 ID의 위시리스트 삭제 실행
        wishlistDAO.deleteWishlist(id);

        // ↩️ 삭제 후 다시 위시리스트 목록으로 이동
        response.sendRedirect(request.getContextPath() + "/wishlist/list");
    }
}
