package controller;

import dao.UserDAO;
import dto.User;
import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ✅ 로그인 페이지로 이동
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // ✅ 비밀번호 SHA-256 해싱
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : md.digest()) sb.append(String.format("%02x", b));
            String hashedPassword = sb.toString();

            // ✅ 사용자 조회
            User user = userDAO.findByUsername(username);

            if (user != null && user.getPasswordHash().equals(hashedPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUsername());

                // 로그인 성공 → 메인으로 이동
                response.sendRedirect(request.getContextPath() + "/main");
            } else {
                request.setAttribute("errorMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}


