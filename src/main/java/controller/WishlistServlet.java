package controller;

import dao.WishlistDAO;
import dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/WishlistServlet")
public class WishlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dto.User loginUser = (dto.User) request.getSession().getAttribute("user");

        if (loginUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int partnerId = Integer.parseInt(request.getParameter("partnerId"));
        int userId = loginUser.getUserId();

        dao.WishlistDAO dao = new dao.WishlistDAO();
        dao.addWishlist(userId, partnerId);

        // JSON 응답
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\":\"관심 목록에 추가되었습니다 🙂\"}");
    }
}
