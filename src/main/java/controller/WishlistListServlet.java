package controller;

import dao.WishlistDAO;
import dto.User;
import dto.Wishlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/wishlist/list")
public class WishlistListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private WishlistDAO wishlistDAO = new WishlistDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 세션에서 로그인 사용자 정보 가져오기
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 로그인된 사용자 ID
        int userId = user.getUserId();

        // DB에서 관심 목록 전체 조회
        List<Wishlist> list = wishlistDAO.getWishlistByUser(userId);

        // JSP에 전달
        request.setAttribute("wishlist", list);

        // JSP로 연결
        request.getRequestDispatcher("/WEB-INF/views/wishlist/list.jsp").forward(request, response);
    }
}
