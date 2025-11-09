package controller;

import dao.WishlistDAO;
import dto.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RemoveWishlistServlet")
public class RemoveWishlistServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null) {
            out.print("{\"message\":\"로그인이 필요합니다.\"}");
            return;
        }

        try {
            int partnerId = Integer.parseInt(request.getParameter("partnerId"));
            WishlistDAO dao = new WishlistDAO();
            boolean success = dao.removeWishlist(loginUser.getUserId(), partnerId);

            if (success) {
                out.print("{\"message\":\"관심 목록에서 제거되었습니다 😢\"}");
            } else {
                out.print("{\"message\":\"삭제 실패 😢\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"message\":\"삭제 중 오류가 발생했습니다 😭\"}");
        }
        out.flush();
    }
}

